
package entity;

public class SessionsRooms {
    
    private int StudioID;
    private int RoomNum;
    private int SessionID;

    public SessionsRooms(int StudioID, int RoomNum, int SessionID) {
        this.StudioID = StudioID;
        this.RoomNum = RoomNum;
        this.SessionID = SessionID;
    }

    public int getStudioID() {
        return StudioID;
    }

    public int getRoomNum() {
        return RoomNum;
    }

    public int getSessionID() {
        return SessionID;
    }
    
}
