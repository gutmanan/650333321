
package entity;

public class SoundManInSession {
    
    private int SessionID;
    private String SoundManID;
    private E_ROLE Role;

    public SoundManInSession(String SoundManID, int SessionID, E_ROLE Role) {
        this.SessionID = SessionID;
        this.SoundManID = SoundManID;
        this.Role = Role;
    }

    public int getSessionID() {
        return SessionID;
    }

    public String getSoundManID() {
        return SoundManID;
    }

    public E_ROLE getRole() {
        return Role;
    }
    
}
