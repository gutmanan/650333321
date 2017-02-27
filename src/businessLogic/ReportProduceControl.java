/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import entity.Musician;
import entity.SoundMan;
import entity.SoundManInSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shai Gutman
 */
public abstract class ReportProduceControl {
    public static ArrayList<String> getArtists() {
        ArrayList<String> arts = new ArrayList<>();
        ResultSet rs = DBManager.query("SELECT tblArtist.*\n"
                                     + "FROM tblArtist\n");
        try {
            while (rs.next()) {
                arts.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportProduceControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arts;
    }
    public static HashMap<Integer, ArrayList<Object>> getRecordings(String artistID) {
        ResultSet rs = DBManager.query("SELECT tblRecording.title, tblRecording.status,tblSession.SessionNumber, tblSession.SessionDate\n" +
"FROM tblSession INNER JOIN tblRecording ON tblSession.SessionNumber = tblRecording.SessionNumber\n" +
"WHERE ((DateDiff(\"m\",[SessionDate],Now())<=6) AND ((tblSession.artistID)=\""+artistID+"\"))");
        HashMap<Integer, ArrayList<Object>> recs = new HashMap<>();
        int index = 0;
        try {
            while (rs.next()) {
                recs.put(++index, new ArrayList<>());
                for (int i = 1; i < 5; i++) {
                    recs.get(index).add(rs.getString(i));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportProduceControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recs;
    }
    
    public static ArrayList<SoundManInSession> getSoundmans(Integer sessioID) {
        ResultSet rs = DBManager.query("SELECT tblSoundManWorkAs.*\n" +
                                       "FROM tblSoundManWorkAs\n" +
                                       "WHERE tblSoundManWorkAs.SessionNumber="+sessioID+"");
        ArrayList<SoundManInSession> recs = new ArrayList<>();
        try {
            while (rs.next()) {
                recs.add(new SoundManInSession(rs.getString(1), rs.getInt(2), rs.getBoolean(3), rs.getBoolean(4), rs.getBoolean(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportProduceControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recs;
    }
    
    public static ArrayList<Musician> getMusicians(Integer sessioID) {
        ResultSet rs = DBManager.query("SELECT tblMusician.*\n" +
"FROM tblMusician INNER JOIN tblMusicianInSession ON tblMusician.MusicianID = tblMusicianInSession.MusicianID\n" +
"WHERE (((tblMusicianInSession.SessionNum)="+sessioID+"));");
        ArrayList<Musician> recs = new ArrayList<>();
        try {
            while (rs.next()) {
                recs.add(new Musician(rs.getString(1), rs.getInt(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportProduceControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recs;
    }
}
