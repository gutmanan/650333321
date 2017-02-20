
package entity;

import java.sql.Date;

public class Freelancer {
    
    private String FreelancerID;
    private String FreelancerName;
    private Date BirthDate;
    private String Email;
    private String StageName;
    private String password;

    public Freelancer(String FreelancerID, String firstName, String lastName, Date BirthDate, String Email, String StageName, String password) {
        this.FreelancerID = FreelancerID;
        this.FreelancerName = firstName +" "+lastName;
        this.BirthDate = BirthDate;
        this.Email = Email;
        this.StageName = StageName;
        this.password = password;
    }

    public String getFreelancerName() {
        return FreelancerName;
    }
}
