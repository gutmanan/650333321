/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package init;

import core.Musician;
import core.Session;
import core.Room;
import core.SoundMan;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shai Gutman
 */
public class CreateSessionControll {
    
    public HashMap<Integer, ArrayList<Integer>> getAvailableStudioRooms(Date selectedDate, java.util.Date start, java.util.Date end) {
        ResultSet rs = MainClass.getDB().query("SELECT Room.studioNumber, Room.RoomNumber, Room.RecordingCell\n" +
                                               "FROM Room;");
        ResultSet rs3 = MainClass.getDB().query("SELECT SessionLocation.StudioNumber, SessionLocation.roomNumber, Session.SessionDate, Session.startTime, Session.endTime, SessionLocation.SessionNumber\n" +
                                                "FROM [Session] INNER JOIN SessionLocation ON Session.SessionNumber = SessionLocation.SessionNumber\n" +
                                                "WHERE (((Session.SessionDate)=#"+selectedDate+"#));");
        ArrayList<Session> sessions = new ArrayList<>();
        ArrayList<Room> rooms = new ArrayList();
        HashMap<Integer, ArrayList<Integer>> availableStudioRooms = new HashMap<>();
        try {
            while (rs3.next()) {
                sessions.add(new Session(rs3.getInt(1),rs3.getInt(2), rs3.getInt(6),rs3.getDate(3), rs3.getTime(4), rs3.getTime(5)));
            }
            while (rs.next()) {
                rooms.add(new Room(rs.getInt(2), rs.getInt(1), rs.getBoolean(3)));
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
                if (s.getStartTime().before(end) && s.getStartTime().after(start)
                 || s.getEndTime().before(end) && s.getEndTime().after(start)
                 || s.getStartTime().before(start) && s.getEndTime().after(end)){
                    if (availableStudioRooms.containsKey(s.getStudioID())) {
                        availableStudioRooms.get(s.getStudioID()).remove(s.getRoomID());
                    }
                }
            }
            for (Map.Entry<Integer, ArrayList<Integer>> entries : availableStudioRooms.entrySet()) {
                Integer key = entries.getKey();
                ArrayList<Integer> value =  entries.getValue();
                if (value.size() == 0) {
                    availableStudioRooms.remove(key);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateSessionControll.class.getName()).log(Level.SEVERE, null, ex);
        }
        return availableStudioRooms;
    }
    
    public Room getRoom(int studioID, int roomID) {
        ResultSet rs = MainClass.getDB().query("SELECT Room.studioNumber, Room.RoomNumber, Room.RecordingCell\n" +
                                                "FROM Room\n" +
                                                "WHERE (((Room.studioNumber)="+studioID+") AND ((Room.RoomNumber)="+roomID+"));");
        Room tmp = null;
        try {
            while (rs.next()) {
                tmp = new Room(rs.getInt(2), rs.getInt(1), rs.getBoolean(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateSessionControll.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmp;
    }
    
    public HashMap<Integer,SoundMan> getAvailableSoundmans(Date selectedDate, java.util.Date start, java.util.Date end, int studioID) {
        HashMap<Integer,SoundMan> availableSoundmans = new HashMap<Integer, SoundMan>();
        try {
            ResultSet rs = MainClass.getDB().query("SELECT SoundMan.SoundManID, SoundMan.producer, SoundMan.mixTech, SoundMan.masterTech, FreelancerWorkWith.rank, Freelancer.firstName, Freelancer.lastName, SoundMan.TotalPayment, SoundMan.AdvencedPay\n" +
                                                   "FROM (SoundMan INNER JOIN Freelancer ON SoundMan.SoundManID = Freelancer.FreelancerID) INNER JOIN FreelancerWorkWith ON Freelancer.FreelancerID = FreelancerWorkWith.freelancerID\n" +
                                                   "WHERE (((FreelancerWorkWith.studioNumber)="+studioID+"));");
            while (rs.next()) {
                availableSoundmans.put(rs.getInt(1), new SoundMan(rs.getInt(1), rs.getBoolean(2), rs.getBoolean(3), rs.getBoolean(4),
                                                                  rs.getString(6)+" "+rs.getString(7), rs.getInt(8), rs.getInt(9)));
            }
            ResultSet rs2 = MainClass.getDB().query("SELECT SoundManWorkAs.SoundManId, Session.SessionDate, Session.startTime, Session.endTime\n" +
                                                    "FROM [Session] INNER JOIN SoundManWorkAs ON Session.SessionNumber = SoundManWorkAs.SessionNumber\n" +
                                                    "WHERE (((Session.SessionDate)=#"+selectedDate+"#));");
            while (rs2.next()) {                
                if (rs2.getTime(3).before(end) && rs2.getTime(3).after(start)
                 || rs2.getTime(4).before(end) && rs2.getTime(4).after(start)
                 || rs2.getTime(3).before(start) && rs2.getTime(4).after(end)){
                    if (availableSoundmans.containsKey(rs2.getInt(1))) {
                        availableSoundmans.remove(rs2.getInt(1));
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateSessionControll.class.getName()).log(Level.SEVERE, null, ex);
        }
        return availableSoundmans;
    }
     public HashMap<Integer, Musician> getAvailableMusicition(Date selectedDate, java.util.Date start, java.util.Date end, int studioID) {
         ResultSet rs = MainClass.getDB().query("SELECT Freelancer.FreelancerID, Specialization.SpecializationType, FreelancerWorkWith.rank, Musician.costPerHour, Freelancer.stageName\n" +
                                                "FROM (Specialization INNER JOIN (Musician INNER JOIN Freelancer ON Musician.MusicianID = Freelancer.FreelancerID) ON Specialization.SpecializationNumber = Musician.specialityNumber) INNER JOIN FreelancerWorkWith ON Freelancer.FreelancerID = FreelancerWorkWith.freelancerID\n" +
                                                "WHERE (((FreelancerWorkWith.studioNumber)="+studioID+"));");
          ResultSet rs2 = MainClass.getDB().query("SELECT MusicianInSession.MusicianID, Session.SessionDate, Session.startTime, Session.endTime\n" +
                                                 "FROM [Session] INNER JOIN (SessionLocation INNER JOIN MusicianInSession ON (SessionLocation.roomNumber = MusicianInSession.RoomNumber) AND (SessionLocation.StudioNumber = MusicianInSession.studioNumber) AND (SessionLocation.SessionNumber = MusicianInSession.SessionNum)) ON Session.SessionNumber = SessionLocation.SessionNumber\n" +
                                                 "WHERE (((Session.SessionDate)=#"+selectedDate+"#));");
         HashMap<Integer, Musician> availableMusicition = new HashMap<>();
         try {
            while (rs.next()) {
                availableMusicition.put(rs.getInt(1), new Musician(rs.getInt(1), rs.getString(2),rs.getInt(4),
                                                                    rs.getString(5),rs.getInt(3)));
            }
            while (rs2.next()) {                
                if (rs2.getTime(3).before(end) && rs2.getTime(3).after(start)
                 || rs2.getTime(4).before(end) && rs2.getTime(4).after(start)
                 || rs2.getTime(3).before(start) && rs2.getTime(4).after(end)){
                    if (availableMusicition.containsKey(rs2.getInt(1))) {
                        availableMusicition.remove(rs2.getInt(1));
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateSessionControll.class.getName()).log(Level.SEVERE, null, ex);
        }
         return availableMusicition;
     }
     
}
