/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import entity.Freelancer;
import entity.Musician;
import entity.Session;
import entity.Room;
import entity.SessionsRooms;
import entity.SoundMan;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Shai Gutman
 */
public abstract class CreateSessionControl {
    
    public static HashMap<Integer, ArrayList<Integer>> getAvailableStudioRooms(Date selectedDate, String start, String end) {
        ResultSet rs = SessionsInTheRoom.getDB().query("SELECT tblRoom.*\n" +
                                                       "FROM tblRoom;");
        ResultSet rs3 = SessionsInTheRoom.getDB().query("SELECT tblSessionLocation.StudioNumber, tblSessionLocation.roomNumber, tblSession.SessionDate, tblSession.startTime, tblSession.endTime, tblSessionLocation.SessionNumber\n" +
                                                "FROM [tblSession] INNER JOIN tblSessionLocation ON tblSession.SessionNumber = tblSessionLocation.SessionNumber\n" +
                                                "WHERE (((tblSession.SessionDate)=#"+selectedDate+"#))");
        ArrayList<Session> sessions = new ArrayList<>();
        ArrayList<SessionsRooms> sessionsRooms = new ArrayList<>();
        ArrayList<Room> rooms = new ArrayList();
        HashMap<Integer, ArrayList<Integer>> availableStudioRooms = new HashMap<>();
        try {
            while (rs3.next()) {
                sessionsRooms.add(new SessionsRooms(rs3.getInt(1),rs3.getInt(2), rs3.getInt(6)));
                sessions.add(new Session(rs3.getInt(6), rs3.getTimestamp(3), rs3.getTimestamp(4).getHours(), rs3.getTimestamp(5).getHours()));
            }
            while (rs.next()) {
                rooms.add(new Room(rs.getInt(1), rs.getInt(2), rs.getBoolean(3), rs.getInt(4), rs.getInt(5)));
            }
            for (Room r : rooms) {
                if (!availableStudioRooms.containsKey(r.getStudioID())) {
                    availableStudioRooms.put(r.getStudioID(), new ArrayList<Integer>());
                }
            }
            for (Map.Entry<Integer, ArrayList<Integer>> entries : availableStudioRooms.entrySet()) {
                Integer key = entries.getKey();
                ArrayList<Integer> value =  entries.getValue();
                for (Room r : rooms) {
                    if (key == r.getStudioID()) {
                        value.add(r.getRoomNum());
                    }
                }
            }
           for (Session s : sessions) {
                if ((s.getStartTime() < Integer.valueOf(end) && s.getStartTime() > Integer.valueOf(start))
                 || (s.getEndTime() < Integer.valueOf(end) && s.getEndTime() > Integer.valueOf(start))
                 || (s.getStartTime() <= Integer.valueOf(start) && s.getEndTime() >= Integer.valueOf(end))){
                    for (SessionsRooms sr : sessionsRooms) {
                        if (s.getSessionID() == sr.getSessionID()) {
                            if (availableStudioRooms.containsKey(sr.getStudioID())) {
                                for (Iterator<Integer> iterator = availableStudioRooms.get(sr.getStudioID()).iterator(); iterator.hasNext(); ) {
                                    Integer value = iterator.next();
                                    if (value == sr.getRoomNum()) {
                                        iterator.remove();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for(Iterator<Map.Entry<Integer, ArrayList<Integer>>> it = availableStudioRooms.entrySet().iterator() ; it.hasNext();){
                Map.Entry<Integer, ArrayList<Integer>> entry = it.next();
                if (entry.getValue().isEmpty()) {
                     it.remove();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateSessionControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return availableStudioRooms;
    }
    public static Room getRoom(int studioID, int roomID) {
        ResultSet rs = SessionsInTheRoom.getDB().query("SELECT tblRoom.*\n" +
                                                "FROM tblRoom\n" +
                                                "WHERE (((tblRoom.studioNumber)="+studioID+") AND ((tblRoom.RoomNumber)="+roomID+"));");
        Room tmp = null;
        try {
            while (rs.next()) {
                tmp = new Room(rs.getInt(1), rs.getInt(2), rs.getBoolean(3), rs.getInt(4), rs.getInt(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateSessionControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmp;
    }
    public static HashMap<String,SoundMan> getAvailableSoundmans(Date selectedDate, String start, String end, String studioID) {
        HashMap<String,SoundMan> availableSoundmans = new HashMap<String, SoundMan>();
        try {
            ResultSet rs = SessionsInTheRoom.getDB().query("SELECT tblSoundMan.*\n" +
                                                   "FROM (tblSoundMan INNER JOIN tblFreelancer ON tblSoundMan.SoundManID = tblFreelancer.FreelancerID) INNER JOIN tblFreelancerWorkWith ON tblFreelancer.FreelancerID = tblFreelancerWorkWith.freelancerID\n" +
                                                   "WHERE (((tblFreelancerWorkWith.studioNumber)="+studioID+"))");
            while (rs.next()) {
                availableSoundmans.put(rs.getString(1), new SoundMan(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getBoolean(4), rs.getBoolean(5), rs.getBoolean(6)));
            }
            ResultSet rs2 = SessionsInTheRoom.getDB().query("SELECT tblSoundManWorkAs.SoundManId, tblSession.SessionDate, tblSession.startTime, tblSession.endTime\n" +
                                                    "FROM [tblSession] INNER JOIN tblSoundManWorkAs ON tblSession.SessionNumber = tblSoundManWorkAs.SessionNumber\n" +
                                                    "WHERE (((tblSession.SessionDate)=#"+selectedDate+"#))");
            while (rs2.next()) {
                Integer hs = rs2.getTime(3).getHours();
                Integer he = rs2.getTime(4).getHours();
                if ((hs < Integer.valueOf(end) && hs > Integer.valueOf(start))
                 || (he < Integer.valueOf(end) && he > Integer.valueOf(start))
                 || (hs <= Integer.valueOf(start) && he >= Integer.valueOf(end))) {
                    if (availableSoundmans.containsKey(rs2.getString(1))) {
                        availableSoundmans.remove(rs2.getString(1));
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateSessionControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return availableSoundmans;
    }
    public static HashMap<String, Musician> getAvailableMusicition(Date selectedDate, String start, String end, int studioID) {
         ResultSet rs = SessionsInTheRoom.getDB().query("SELECT tblMusician.*\n" +
                                                "FROM (tblSpecialization INNER JOIN (tblMusician INNER JOIN tblFreelancer ON tblMusician.MusicianID = tblFreelancer.FreelancerID) ON tblSpecialization.Type = tblMusician.specialityType) INNER JOIN tblFreelancerWorkWith ON tblFreelancer.FreelancerID = tblFreelancerWorkWith.freelancerID\n" +
                                                "WHERE (((tblFreelancerWorkWith.studioNumber)="+studioID+"));");
          ResultSet rs2 = SessionsInTheRoom.getDB().query("SELECT tblMusicianInSession.MusicianID, tblSession.SessionDate, tblSession.startTime, tblSession.endTime\n" +
                                                 "FROM [tblSession] INNER JOIN (tblSessionLocation INNER JOIN tblMusicianInSession ON (tblSessionLocation.roomNumber = tblMusicianInSession.RoomNumber) AND (tblSessionLocation.StudioNumber = tblMusicianInSession.studioNumber) AND (tblSessionLocation.SessionNumber = tblMusicianInSession.SessionNum)) ON tblSession.SessionNumber = tblSessionLocation.SessionNumber\n" +
                                                 "WHERE (((tblSession.SessionDate)=#"+selectedDate+"#));");
         HashMap<String, Musician> availableMusicition = new HashMap<>();
         try {
            while (rs.next()) {
                availableMusicition.put(rs.getString(1), new Musician(rs.getString(1), rs.getInt(2),rs.getString(3)));
            }
            while (rs2.next()) {                
                Integer hs = rs2.getTime(3).getHours();
                Integer he = rs2.getTime(4).getHours();
                if ((hs < Integer.valueOf(end) && hs > Integer.valueOf(start))
                 || (he < Integer.valueOf(end) && he > Integer.valueOf(start))
                 || (hs <= Integer.valueOf(start) && he >= Integer.valueOf(end))){
                    if (availableMusicition.containsKey(rs2.getInt(1))) {
                        availableMusicition.remove(rs2.getInt(1));
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateSessionControl.class.getName()).log(Level.SEVERE, null, ex);
        }
         return availableMusicition;
     }
    public static Freelancer getFreelancer(String freelancerID)  {
        ResultSet rs = SessionsInTheRoom.getDB().query("SELECT tblFreelancer.*\n" +
                                                       "FROM tblFreelancer\n" +
                                                       "WHERE tblFreelancer.FreelancerID=\""+freelancerID+"\"");
        try {
            while (rs.next()) {
                return new Freelancer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4),
                    rs.getString(5), rs.getString(6), rs.getString(8));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudioRatesControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static int newSession(Timestamp sessionDate, String start, String end, String artistID) {
        if (!(ValidatorManager.onlyContainsNumbers(start))){
            JOptionPane.showMessageDialog(null, "The until field must be a number.");
            return -1;
        }
        
        if (!(ValidatorManager.onlyContainsNumbers(end))){
            JOptionPane.showMessageDialog(null, "The start field must be a number.");
            return -1;
        }
        Timestamp tstart = new Timestamp(sessionDate.getTime());
        tstart.setHours(Integer.valueOf(start));
        Timestamp tend = new Timestamp(sessionDate.getTime());
        tend.setHours(Integer.valueOf(end));
        String qry = "INSERT INTO tblSession (SessionDate, startTime, endTime, artistID)\n"
                   + "VALUES('"+sessionDate+"','"+tstart+"','"+tend+"','"+artistID+"')";
        return DBManager.insert(qry);
    }
    public static boolean newSessionLocation(int session, int studio, int room) {
        String qry = "INSERT INTO tblSessionLocation (SessionNumber, StudioNumber, roomNumber)\n"
                   + "VALUES('"+session+"','"+studio+"','"+room+"')";
        if (DBManager.insert(qry) == -2) {
            return true;
        }
        return false;
    }
    public static boolean newSoundmanInSession(String soundman, int session, boolean producer, boolean mix, boolean master) {
        String qry = "INSERT INTO tblSoundManWorkAs (SoundManID, SessionNumber, Producer, MixTech, MasterTech)\n"
                   + "VALUES('"+soundman+"','"+session+"','"+producer+"','"+mix+"','"+master+"')";
        if (DBManager.insert(qry) == -2) {
            return true;
        }
        return false;
    }
}
