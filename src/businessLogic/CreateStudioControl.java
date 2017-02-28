/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import com.sun.org.apache.xerces.internal.impl.validation.ValidationManager;
import entity.Studio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Shai Gutman
 */
public abstract class CreateStudioControl {
    
    public CreateStudioControl() {}
    public static boolean insertNewStudio(String name, String description, String email, String phone, String street, String house, String city) {
        if (!(ValidatorManager.isAlpha(name)) || name.isEmpty()){
            JOptionPane.showMessageDialog(null, "The name field must contain only letters.");
            return false;
        }
        
        if (!(ValidatorManager.isValidEmailAddress(email))){
            JOptionPane.showMessageDialog(null, "The Email field is incorrect. \n Example : abc@def.com");
            return false;
        }
        if (!(ValidatorManager.onlyContainsNumbers(phone))){
            JOptionPane.showMessageDialog(null, "The phone field must contain numbers.");
            return false;
        }
        
        String address;
        if (city.equals("Select City") || street.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please provide a valid address.");
            return false;
        } else address = street+" "+house+", "+city;
        
        String qry = "INSERT INTO tblStudio (studioName, description, email, phone, address)\n"
                   + "VALUES('"+name+"','"+description+"','"+email+"','"+phone+"','"+address+"')";
        if (DBManager.insert(qry) > -1) {
            JOptionPane.showMessageDialog(null,
                "The studio was created successfully!",
                "Studio was created",
                JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
        return false;
    } 
    
    public static ArrayList<Studio> getStudios() {
        ResultSet rs = DBManager.query("SELECT tblStudio.*" + "FROM tblStudio");
        ArrayList<Studio> studios = new ArrayList<>();
        try {
            while (rs.next()) {
                studios.add(new Studio(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateStudioControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return studios;
    } 
}