/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import entity.Musician;
import entity.SoundMan;
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
public abstract class AddQualificationControl {
     public AddQualificationControl() {}
     
    public static boolean insertNewSoundman(String freelancerID, String advanced, String total, boolean producer, boolean master, boolean mix) {
        if (!(ValidatorManager.onlyContainsNumbers(total))){
            JOptionPane.showMessageDialog(null, "The Total payment field must be a number.");
            return false;
        }
        
        if (!(ValidatorManager.onlyContainsNumbers(advanced))){
            JOptionPane.showMessageDialog(null, "The Advanced payment field must be a number.");
            return false;
        }
        
        if (!mix && !master && !producer) {
            JOptionPane.showMessageDialog(null, "Soundman must have at least one role.");
            return false;
        }
        
        String qry = "INSERT INTO tblSoundMan (SoundManID, AdvencedPay, TotalPayment, masterTech, mixTech, producer)\n"
                   + "VALUES('"+freelancerID+"','"+advanced+"','"+total+"','"+master+"','"+mix+"','"+producer+"')";
        if (DBManager.insert(qry) == -2) {
            JOptionPane.showMessageDialog(null,
                "Congratulations! you have been registered as a soundman.",
                "Registration complete",
                JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
        return false;
    } 
    
    public static boolean insertNewMusician(String freelancerID, String wage, String instrument, boolean isNew) {
        if (!(ValidatorManager.onlyContainsNumbers(wage))){
            JOptionPane.showMessageDialog(null, "The Hourly wage field must be a number.");
            return false;
        }
        
        if (!(ValidatorManager.isAlpha(instrument))){
            JOptionPane.showMessageDialog(null, "The Instrument field is incorrect.");
            return false;
        }
        
        String qry = "INSERT INTO tblMusician (MusicianID, costPerHour, specialityType)\n"
                   + "VALUES('"+freelancerID+"','"+wage+"','"+instrument+"')";
        if (isNew && insertInstrument(instrument)) {
            if (DBManager.insert(qry) == -2) {
                JOptionPane.showMessageDialog(null,
                    "Congratulations! you have been registered as a musician.",
                    "Registration complete",
                    JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        } else 
            if (DBManager.insert(qry) == -2) {
                JOptionPane.showMessageDialog(null,
                    "Congratulations! you have been registered as a musician.",
                    "Registration complete",
                    JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        return false;
    } 
    
    public static ArrayList<String> getAllInstruments() {
        ResultSet rs = DBManager.query("SELECT tblSpecialization.*\n"
                                     + "FROM tblSpecialization\n");
        ArrayList<String> ins = new ArrayList<>();
         try {
             while (rs.next()) {
                 ins.add(rs.getString(1));
             }} catch (SQLException ex) {
             Logger.getLogger(AddQualificationControl.class.getName()).log(Level.SEVERE, null, ex);
         }
         return ins;
    }
    
    public static boolean insertInstrument(String instrument) {
        String qry = "INSERT INTO tblSpecialization (Type)\n"
                   + "VALUES('"+instrument+"')";
        if (DBManager.insert(qry) == -2) {
            return true;
        }
        return false;
    } 
    
    public static SoundMan alreadySoundman(String freelancerID) {
        ResultSet rs = DBManager.query("SELECT tblSoundMan.*\n"
                                     + "FROM tblSoundMan\n"
                                     + "WHERE tblSoundMan.SoundManID=\""+freelancerID+"\"");
        SoundMan tmp = null;
         try {
             if (!rs.isBeforeFirst()) {    
                return tmp; 
            } 
             while (rs.next()) {
                 tmp = new SoundMan(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getBoolean(4), rs.getBoolean(5), rs.getBoolean(6));
             }} catch (SQLException ex) {
             Logger.getLogger(AddQualificationControl.class.getName()).log(Level.SEVERE, null, ex);
         }
         return tmp;
    }
    
    public static Musician alreadyMusician(String freelancerID) {
        ResultSet rs = DBManager.query("SELECT tblMusician.*\n"
                                     + "FROM tblMusician\n"
                                     + "WHERE tblMusician.MusicianID=\""+freelancerID+"\"");
        Musician tmp = null;
         try {
             if (!rs.isBeforeFirst() ) {    
                return tmp; 
            } 
             while (rs.next()) {
                 tmp = new Musician(rs.getString(1), rs.getInt(2), rs.getString(3));
             }} catch (SQLException ex) {
             Logger.getLogger(AddQualificationControl.class.getName()).log(Level.SEVERE, null, ex);
         }
         return tmp;
    }
}
