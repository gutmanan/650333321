/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package init;

import java.io.File;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.ucanaccess.jdbc.UcanaccessDriver;

/**
 *
 * @author Shai Gutman
 */
public class DBManager {

    private static Connection conn = null;
    private static String dbFile;
    
    public DBManager(){
        try {
            System.out.println("//===========================================================");
            System.out.println("\tTrying to load DB...");
            dbFile = (new File("sources/SessionDataBase.accdb")).getAbsolutePath();
            conn = DriverManager.getConnection("jdbc:ucanaccess://"+dbFile+";COLUMNORDER=DISPLAY");
            System.out.println("\tDB was loaded!");
        } catch (SQLException ex) {
            try{
                dbFile = (new File("src/sources/SessionDataBase.accdb")).getAbsolutePath();
                conn = DriverManager.getConnection("jdbc:ucanaccess://"+dbFile+";COLUMNORDER=DISPLAY");
                System.out.println("\tDB was loaded!");
            } catch (SQLException ex1) {
                System.out.println("\tError loading DB!");
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    public static ResultSet query(String qry) {
        ResultSet rs = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(qry);
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
      return rs;
    }
    /*
    public static void insert() {
        try {          
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT tblAgent" + "(ID, firstName, lastName, phoneNumber, email)"
                                 + "VALUES(" + "jj66" + ", '" + "two" + "', " + "three" + "0526548732" + "fds@gmail.com" + ")");
            stmt.close();
         }
        catch(Exception e) {
            e.printStackTrace();
        }
    }*/
}
