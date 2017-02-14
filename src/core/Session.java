
package core;

import java.sql.*;

public class Session {
    
    private int SessionID;
    private Date SessionDate;
    private Time StartTime;
    private Time EndTime;
    private String ArtistAlphaCode;
    private int studioID;
    private int roomID;

    public Session(int studioID,int roomID, int SessionID, Date SessionDate, Time StartTime, Time EndTime) {
        this.studioID = studioID;
        this.roomID = roomID;
        this.SessionID = SessionID;
        this.SessionDate = SessionDate;
        this.StartTime = StartTime;
        this.EndTime = EndTime;
    }

    public int getSessionID() {
        return SessionID;
    }

    public String getArtistAlphaCode() {
        return ArtistAlphaCode;
    }

    public int getStudioID() {
        return studioID;
    }

    public int getRoomID() {
        return roomID;
    }

    public Time getStartTime() {
        return StartTime;
    }

    public Time getEndTime() {
        return EndTime;
    }

    public Date getSessionDate() {
        return SessionDate;
    }
    
}
