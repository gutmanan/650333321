/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import entity.Freelancer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shai Gutman
 */
public abstract class StudioRatesControl {
    
    public StudioRatesControl() {};
    
    public static ArrayList<Freelancer> getFreelancersWorkingWith(int studioID)  {
        ResultSet rs = SessionsInTheRoom.getDB().query("SELECT tblFreelancer.*\n" +
                    "FROM tblFreelancer INNER JOIN tblFreelancerWorkWith ON tblFreelancer.FreelancerID = tblFreelancerWorkWith.freelancerID\n" +
                    "WHERE (((tblFreelancerWorkWith.studioNumber)="+studioID+"))");
        ArrayList<Freelancer> freelancers = new ArrayList<>();
        try {
            while (rs.next()) {
                freelancers.add(new Freelancer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4),
                    rs.getString(5), rs.getString(6), rs.getString(8)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudioRatesControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return freelancers;
    }
    
    public static int getRankOf(int studio, String freelancer) {
        ResultSet rs = SessionsInTheRoom.getDB().query("SELECT tblFreelancerWorkWith.rank\n" +
        "FROM tblFreelancerWorkWith\n" +
        "WHERE (((tblFreelancerWorkWith.studioNumber)="+studio+") AND ((tblFreelancerWorkWith.freelancerID)=\""+freelancer+"\"))");
        int rank = 0;
        try {
            while (rs.next()) {
                rank = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudioRatesControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return --rank;
    }
    public static boolean updateRates(int studio, String freelancer, int rank) {
        String qry = "UPDATE tblFreelancerWorkWith SET tblFreelancerWorkWith.rank ="+rank+"\n" +
                         "WHERE (((tblFreelancerWorkWith.studioNumber)="+studio+") AND ((tblFreelancerWorkWith.freelancerID)=\""+freelancer+"\"))";
        if (DBManager.insert(qry) == -2) {
            return true;
        }
        return false;
    }
}