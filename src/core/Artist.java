
package core;

public class Artist {
    
    private String AlphaCode;
    private String StageName;
    private String Email;

    public Artist(String AlphaCode, String StageName, String Email) {
        this.AlphaCode = AlphaCode;
        this.StageName = StageName;
        this.Email = Email;
    }

    public String getAlphaCode() {
        return AlphaCode;
    }

    public String getStageName() {
        return StageName;
    }
}
