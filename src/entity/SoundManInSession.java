
package entity;

public class SoundManInSession {
    
    private int SessionID;
    private String SoundManID;
    private boolean Producer;
    private boolean MixTech;
    private boolean MasterTech;

    public SoundManInSession(String SoundManID, int SessionID, boolean Producer, boolean MixTech, boolean MasterTech) {
        this.SessionID = SessionID;
        this.SoundManID = SoundManID;
        this.Producer = Producer;
        this.MixTech = MixTech;
        this.MasterTech = MasterTech;
    }

    public int getSessionID() {
        return SessionID;
    }

    public String getSoundManID() {
        return SoundManID;
    }

    public boolean isProducer() {
        return Producer;
    }

    public boolean isMixTech() {
        return MixTech;
    }

    public boolean isMasterTech() {
        return MasterTech;
    }
    
}
