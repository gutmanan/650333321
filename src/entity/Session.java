
package entity;

import java.sql.*;

public class Session {
    
    private int SessionID;
    private Timestamp SessionDate;
    private int StartTime;
    private int EndTime;
    private String ArtistAlphaCode;

    public Session(int SessionID, Timestamp SessionDate, int StartTime, int EndTime) {
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

    public Timestamp getSessionDate() {
        return SessionDate;
    }

    public int getStartTime() {
        return StartTime;
    }

    public int getEndTime() {
        return EndTime;
    }
    
    
    
}
