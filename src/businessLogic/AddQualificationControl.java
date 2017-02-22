/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

/**
 *
 * @author Shai Gutman
 */
public abstract class AddQualificationControl {
     public AddQualificationControl() {}
     
    public static boolean insertNewSoundman(String freelancerID, int advanced, int total, boolean producer, boolean master, boolean mix) {
        String qry = "INSERT INTO tblSoundMan (SoundManID, AdvencedPay, TotalPayment, masterTech, mixTech, producer)\n"
                   + "VALUES('"+freelancerID+"','"+advanced+"','"+total+"','"+master+"','"+mix+"','"+producer+"')";
        if (DBManager.insert(qry) == -2) {
            return true;
        }
        return false;
    } 
    
    public static boolean insertNewMusician(String freelancerID, int wage, String instrument) {
        String qry = "INSERT INTO tblSoundMan (SoundManID, AdvencedPay, TotalPayment, masterTech, mixTech, producer)\n"
                   + "VALUES('"+freelancerID+"','"+wage+"','"+instrument+"')";
        if (DBManager.insert(qry) == -2) {
            return true;
        }
        return false;
    } 
}
