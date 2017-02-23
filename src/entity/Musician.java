
package entity;

public class Musician {
   
    private String freelancerID;
    private int Commission;
    private String type;

    public Musician(String freelancerID, int Commission, String type) {
        this.freelancerID = freelancerID;
        this.Commission = Commission;
        this.type = type;
    }

    public int getCommission() {
        return Commission;
    }

    public String getFreelancerID() {
        return freelancerID;
    }

    public String getType() {
        return type;
    }
    
}
