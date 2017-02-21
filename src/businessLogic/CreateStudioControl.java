/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

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
    public static boolean insertNewStudio(String name, String description, String email, String phone, String address) {
        String qry = "INSERT INTO tblStudio (studioName, description, email, phone, address)\n"
                   + "VALUES('"+name+"','"+description+"','"+email+"','"+phone+"','"+address+"')";
        if (DBManager.insert(qry) > -1) {
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