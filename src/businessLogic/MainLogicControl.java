/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import boundary.MainLogin;
import entity.Artist;
import entity.Freelancer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author Shai Gutman
 */
public abstract class MainLogicControl {
    public static void openMain(String user, String password, boolean flag) {
        if (user.equals("")) {
            JOptionPane.showMessageDialog(null,
                "You must enter username & password",
                "Login error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (user.equals("Admin") && password.equals("Admin")) {
            WindowManager.setUser(2, null);
            WindowManager.closeLogin();
            return;
        } else {
            ResultSet artists = SessionsInTheRoom.getDB().query("SELECT tblArtist.*\n" +
                                                               "FROM tblArtist\n" +
                                                               "WHERE tblArtist.eMail=\""+user+"\"");
            ResultSet freelancers = SessionsInTheRoom.getDB().query("SELECT tblFreelancer.*\n" +
                                                               "FROM tblFreelancer\n" +
                                                               "WHERE tblFreelancer.eMail=\""+user+"\"");
            try {
                while (artists.next()) {
                    if (artists.getString(4).equals(password)) {
                        WindowManager.setUser(1, new Artist(artists.getString(1), artists.getString(2), artists.getString(3), 
                                                           artists.getString(4)));
                        WindowManager.closeLogin();
                        return;
                    } 
                }
                while (freelancers.next()) {
                    if (freelancers.getString(8).equals(password)) {
                        WindowManager.setUser(3, new Freelancer(freelancers.getString(1), freelancers.getString(2), freelancers.getString(3), 
                                                        freelancers.getDate(4), freelancers.getString(5), freelancers.getString(6), freelancers.getString(8)));
                        WindowManager.closeLogin();
                        return;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!flag) {
            JOptionPane.showMessageDialog(null,
                "Incorrect username or password",
                "Login error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    public static boolean newFreelancer(String username, String nickname, String firstname,String lastname, String email,String password, Timestamp ts, Icon ii) {
        if (password.isEmpty() || username.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields first!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!ValidatorManager.isAlpha(firstname)) {
            JOptionPane.showMessageDialog(null, "First name must be letters only!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!ValidatorManager.isAlpha(lastname)) {
            JOptionPane.showMessageDialog(null, "Last name must be letters only!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!ValidatorManager.isValidEmailAddress(email)) {
            JOptionPane.showMessageDialog(null, "Please provide a valid email!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String qry = "INSERT INTO tblFreelancer (FreelancerID, firstName, lastName, birthDate, eMail, stageName, Photo, password)"
        + "VALUES('"+username+"','"+firstname+"','"+lastname+"','"+ts+"',\""+email+"\",'"+nickname+"',\""+ii+"\",'"+password+"')";
        if (DBManager.insert(qry) == -2) {
            JOptionPane.showMessageDialog(null,
                "Congratulations your account was created successfully!",
                "Account was created",
                JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
        return false;
    }
    public static boolean newArtist(String username, String nickname, String email,String password, boolean  flag) {
        if (password.isEmpty() || nickname.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields first!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (flag) {
            String qry = "UPDATE tblArtist SET tblArtist.password =\""+password+"\"\n" +
                         "WHERE (((tblArtist.eMail)=\""+email+"\") AND ((tblArtist.ArtistID)=\""+username+"\"))";
            if (DBManager.insert(qry) == -2) {
                JOptionPane.showMessageDialog(null,
                    "Password was updated successfully!",
                    "Login setup",
                    JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }
        if (!ValidatorManager.isValidEmailAddress(email)) {
            JOptionPane.showMessageDialog(null, "Please provide a valid email!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String qry = "INSERT INTO tblArtist (ArtistID, stageName, eMail, password, isActive)"
        + "VALUES('"+username+"','"+nickname+"','"+email+"','"+password+"','"+true+"')";
        if (DBManager.insert(qry) == -2) {
            JOptionPane.showMessageDialog(null,
                "Congratulations your account was created successfully!",
                "Account was created",
                JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
        return false;
    }
}
