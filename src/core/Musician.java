
package core;

public class Musician {
   
    private int freelancerID;
    private int Commission;
    private int Specialization;
    private String name;
    private int rank;
    private String type;

    public Musician(int freelancerID, String type, int Commission, String name, int rank) {
        this.freelancerID = freelancerID;
        this.Commission = Commission;
        this.name = name;
        this.rank = rank;
        this.type = type;
    }

    public int getCommission() {
        return Commission;
    }

    public int getSpecialization() {
        return Specialization;
    }

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }

    public int getFreelancerID() {
        return freelancerID;
    }

    public String getType() {
        return type;
    }
    
}
