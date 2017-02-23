/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import entity.Room;
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
public abstract class AddRoomControl {
    
    public AddRoomControl() {}
    public static ArrayList<Room> getStudioRooms(int stdId) {
        ResultSet rs = SessionsInTheRoom.getDB().query("SELECT tblRoom.*\n" +
                                                       "FROM tblRoom\n"
                                                     + "WHERE tblRoom.studioNumber ="+stdId+"");
        ArrayList<Room> rooms = new ArrayList<>();
        try {
            while (rs.next()) {
                rooms.add(new Room(rs.getInt(1), rs.getInt(2), rs.getBoolean(3), rs.getInt(4), rs.getInt(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddRoomControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rooms;
    }
    
    public static boolean insertNewRoom(int roomID, int studioID, boolean recCell, String capacity, String price) {
        if (!(ValidatorManager.onlyContainsNumbers(capacity))){
            JOptionPane.showMessageDialog(null, "The Capacity field must be a number.");
            return false;
        }

        if (!(ValidatorManager.onlyContainsNumbers(price))){
            JOptionPane.showMessageDialog(null, "The Price field must be a number.");
            return false;
        }
        String qry = "INSERT INTO tblRoom (RoomNumber, studioNumber, RecordingCell, Capacity, Price)\n"
                   + "VALUES('"+roomID+"','"+studioID+"','"+recCell+"','"+capacity+"','"+price+"')";
        if (DBManager.insert(qry) == -2) {
            JOptionPane.showMessageDialog(null,
                "The room was added successfully!",
                "Room was added",
                JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
        return false;
    } 
}
