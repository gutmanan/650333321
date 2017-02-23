/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hsqldb.result.ResultMetaData;

/**
 *
 * @author Shai Gutman
 */
public abstract class AddWorkWithStudioControl {
        public AddWorkWithStudioControl() {}
        
        public static String getDescription(int stdID) {
            ResultSet rs = DBManager.query("SELECT tblStudio.description\n"
                                         + "FROM tblStudio\n"
                                         + "WHERE tblStudio.studioNumber="+stdID+"");
            try {
                while (rs.next()) {
                    return rs.getString(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(AddWorkWithStudioControl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "";
        }
        
        public static boolean alreadyWork(int stdID) {
        ResultSet rs = DBManager.query("SELECT tblFreelancerWorkWith.studioNumber\n" +
                                       "FROM tblFreelancerWorkWith\n" +
                                       "WHERE (((tblFreelancerWorkWith.studioNumber)="+stdID+") AND ((tblFreelancerWorkWith.freelancerID)=\""+WindowManager.getTmpFreelancer().getFreelancerID()+"\"));");
            try {
                while (rs.next()) {
                    if (rs.getInt(1) == stdID) {
                        return true;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(AddWorkWithStudioControl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
        
        public static boolean deleteWork(int stdID) {
            String qry = "DELETE FROM tblFreelancerWorkWith\n" +
                         "WHERE (((tblFreelancerWorkWith.studioNumber)="+stdID+") AND ((tblFreelancerWorkWith.freelancerID)=\""+WindowManager.getTmpFreelancer().getFreelancerID()+"\"))";
            if (DBManager.insert(qry) == -2) {
                return true;
            }
            return false;
        }
        
        public static boolean insertWork(int stdID) {
            String qry = "INSERT INTO tblFreelancerWorkWith (freelancerID, studioNumber, rank)\n"
                       + "VALUES(\""+WindowManager.getTmpFreelancer().getFreelancerID()+"\",'"+stdID+"','"+0+"')";
            if (DBManager.insert(qry) == -2) {
                return true;
            }
            return false;
        }
}
