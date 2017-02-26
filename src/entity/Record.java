
package entity;

import java.sql.Date;

public class Record {
    private String AlphaCode;
    private String Title;
    private E_STATUS Status;
    private String LyricsLink;
    private int Length;
    private String YoutubeLink;
    private int SessionID;
    private String PrevRecord;

    public Record(String AlphaCode, String Title, E_STATUS Status, int Length, String PrevRecord) {
        this.AlphaCode = AlphaCode;
        this.Title = Title;
        this.Status = Status;
        this.Length = Length;
        this.PrevRecord = PrevRecord;
    }

    public String getAlphaCode() {
        return AlphaCode;
    }

    public String getTitle() {
        return Title;
    }

    public E_STATUS getStatus() {
        return Status;
    }

    public int getLength() {
        return Length;
    }

    public String getPrevRecord() {
        return PrevRecord;
    }
    
    
}
