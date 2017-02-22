/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import entity.Musician;
import entity.Session;
import entity.Room;
import entity.SoundMan;
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
public class CreateSessionControl {
    
    public HashMap<Integer, ArrayList<Integer>> getAvailableStudioRooms(Date selectedDate, java.util.Date start, java.util.Date end) {
        ResultSet rs = SessionsInTheRoom.getDB().query("SELECT tblRoom.*\n" +
                                                       "FROM tblRoom;");
        ResultSet rs3 = SessionsInTheRoom.getDB().query("SELECT tblSessionLocation.StudioNumber, tblSessionLocation.roomNumber, tblSession.SessionDate, tblSession.startTime, tblSession.endTime, tblSessionLocation.SessionNumber\n" +
                                                "FROM [tblSession] INNER JOIN tblSessionLocation ON tblSession.SessionNumber = tblSessionLocation.SessionNumber\n" +
                                                "WHERE (((tblSession.SessionDate)=#"+selectedDate+"#));");
        ArrayList<Session> sessions = new ArrayList<>();
        ArrayList<Room> rooms = new ArrayList();
        HashMap<Integer, ArrayList<Integer>> availableStudioRooms = new HashMap<>();
        try {
            while (rs3.next()) {
                sessions.add(new Session(rs3.getInt(1),rs3.getInt(2), rs3.getInt(6),rs3.getDate(3), rs3.getTime(4), rs3.getTime(5)));
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
            Logger.getLogger(CreateSessionControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return availableStudioRooms;
    }
    
    public Room getRoom(int studioID, int roomID) {
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
    
    public HashMap<Integer,SoundMan> getAvailableSoundmans(Date selectedDate, java.util.Date start, java.util.Date end, int studioID) {
        HashMap<Integer,SoundMan> availableSoundmans = new HashMap<Integer, SoundMan>();
        try {
            ResultSet rs = SessionsInTheRoom.getDB().query("SELECT tblSoundMan.SoundManID, tblSoundMan.producer, tblSoundMan.mixTech, tblSoundMan.masterTech, tblFreelancerWorkWith.rank, tblFreelancer.firstName, tblFreelancer.lastName, tblSoundMan.TotalPayment, tblSoundMan.AdvencedPay\n" +
                                                   "FROM (tblSoundMan INNER JOIN tblFreelancer ON tblSoundMan.SoundManID = tblFreelancer.FreelancerID) INNER JOIN tblFreelancerWorkWith ON tblFreelancer.FreelancerID = tblFreelancerWorkWith.freelancerID\n" +
                                                   "WHERE (((tblFreelancerWorkWith.studioNumber)="+studioID+"));");
            while (rs.next()) {
                availableSoundmans.put(rs.getInt(1), new SoundMan(rs.getInt(1), rs.getBoolean(2), rs.getBoolean(3), rs.getBoolean(4),
                                                                  rs.getString(6)+" "+rs.getString(7), rs.getInt(8), rs.getInt(9)));
            }
            ResultSet rs2 = SessionsInTheRoom.getDB().query("SELECT tblSoundManWorkAs.SoundManId, tblSession.SessionDate, tblSession.startTime, tblSession.endTime\n" +
                                                    "FROM [tblSession] INNER JOIN tblSoundManWorkAs ON tblSession.SessionNumber = tblSoundManWorkAs.SessionNumber\n" +
                                                    "WHERE (((tblSession.SessionDate)=#"+selectedDate+"#));");
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
            Logger.getLogger(CreateSessionControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return availableSoundmans;
    }
     public HashMap<Integer, Musician> getAvailableMusicition(Date selectedDate, java.util.Date start, java.util.Date end, int studioID) {
         ResultSet rs = SessionsInTheRoom.getDB().query("SELECT tblFreelancer.FreelancerID, tblSpecialization.SpecializationType, tblFreelancerWorkWith.rank, tblMusician.costPerHour, tblFreelancer.stageName\n" +
                                                "FROM (tblSpecialization INNER JOIN (tblMusician INNER JOIN tblFreelancer ON tblMusician.MusicianID = tblFreelancer.FreelancerID) ON tblSpecialization.SpecializationNumber = tblMusician.specialityNumber) INNER JOIN tblFreelancerWorkWith ON tblFreelancer.FreelancerID = tblFreelancerWorkWith.freelancerID\n" +
                                                "WHERE (((tblFreelancerWorkWith.studioNumber)="+studioID+"));");
          ResultSet rs2 = SessionsInTheRoom.getDB().query("SELECT tblMusicianInSession.MusicianID, tblSession.SessionDate, tblSession.startTime, tblSession.endTime\n" +
                                                 "FROM [tblSession] INNER JOIN (tblSessionLocation INNER JOIN tblMusicianInSession ON (tblSessionLocation.roomNumber = tblMusicianInSession.RoomNumber) AND (tblSessionLocation.StudioNumber = tblMusicianInSession.studioNumber) AND (tblSessionLocation.SessionNumber = tblMusicianInSession.SessionNum)) ON tblSession.SessionNumber = tblSessionLocation.SessionNumber\n" +
                                                 "WHERE (((tblSession.SessionDate)=#"+selectedDate+"#));");
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
            Logger.getLogger(CreateSessionControl.class.getName()).log(Level.SEVERE, null, ex);
        }
         return availableMusicition;
     }
     
}
