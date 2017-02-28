/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import entity.E_STATUS;
import entity.Record;
import entity.Room;
import entity.Session;
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
public abstract class AddRecordingControl {
    public static ArrayList<Session> getSessions(String artistID) {
        ResultSet rs = SessionsInTheRoom.getDB().query("SELECT tblSession.*\n" +
                                                "FROM tblSession\n" +
                                                "WHERE tblSession.artistID=\""+artistID+"\"");
        ArrayList<Session> sessions = new ArrayList<>();
        try {
            while (rs.next()) {
                sessions.add(new Session(rs.getInt(1), rs.getTimestamp(2), rs.getTimestamp(3).getHours(), rs.getTimestamp(4).getHours()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateSessionControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sessions;
    }
    public static ArrayList<Record> getSessionRecords(String artist, int session) {
        
        ResultSet rs = SessionsInTheRoom.getDB().query("SELECT tblRecording.RecordingID, tblRecording.title, tblRecording.status, tblRecording.length, tblRecording.prevRecording\n" +
"FROM tblSession INNER JOIN tblRecording ON tblSession.SessionNumber = tblRecording.SessionNumber\n" +
"WHERE (((tblSession.SessionNumber)="+session+") AND ((tblSession.artistID)=\""+artist+"\"))");
        ArrayList<Record> recordings = new ArrayList<>();
        try {
            while (rs.next()) {
                recordings.add(new Record(rs.getString(1), rs.getString(2), E_STATUS.valueOf(rs.getString(3)), rs.getInt(4), rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateSessionControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recordings;
    }
    public static boolean inseretNewRecording(String recoID, String title, String status, String lyric, String length, String youtube, String file, String prevrec, String session) { 
        if (!(ValidatorManager.onlyContainsNumbers(session))){
            JOptionPane.showMessageDialog(null, "You must select a session in order to add a recording.");
            return false;
        }
        
        if (title.isEmpty()){
            JOptionPane.showMessageDialog(null, "The title field cannot be empty.");
            return false;
        }
        
        if (status.equals("Select Status")){
            JOptionPane.showMessageDialog(null, "The status field cannot be empty.");
            return false;
        }
        
        if (!(ValidatorManager.onlyContainsNumbers(length))){
            JOptionPane.showMessageDialog(null, "The length field must be a number.");
            return false;
        }
        
        if (!(ValidatorManager.onlyContainsNumbers(length))){
            JOptionPane.showMessageDialog(null, "The length field must be a number.");
            return false;
        }
        
        if (!(ValidatorManager.isValidURL(lyric))){
            JOptionPane.showMessageDialog(null, "The lyrics link field must be a valid URL.");
            return false;
        }
        
        if (!(ValidatorManager.isValidURL(youtube))){
            JOptionPane.showMessageDialog(null, "The youtube link field must be a valid URL.");
            return false;
        }
        
        String noPrev = prevrec;
        if (noPrev.equals("Select Recording")) {
            noPrev = recoID;
        }        
        
        String qry = "INSERT INTO tblRecording(RecordingID, title, status, lyricsLink, length, youtubeLink, zipFile, prevRecording, SessionNumber)"
                   + " VALUES('"+recoID+"','"+title+"','"+status+"','"+lyric+"','"+length+"','"+youtube+"','"+file+"','"+noPrev+"','"+session+"')";
        if (DBManager.insert(qry) == -2) {
            JOptionPane.showMessageDialog(null, "The recording has been added successfully!");
            return true;
        }
        return false;
    }
}
