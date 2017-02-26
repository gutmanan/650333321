
package entity;

public class Artist {
    
    private String AlphaCode;
    private String StageName;
    private String Email;
    private String password;

    public Artist(String AlphaCode, String StageName, String Email, String password) {
        this.AlphaCode = AlphaCode;
        this.StageName = StageName;
        this.Email = Email;
        this.password = password;
    }
    
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

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return password;
    }
    
}
