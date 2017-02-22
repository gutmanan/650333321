
package entity;

public class StudiosFreelancers {
    
    private int StudioID;
    private String FreelancerID;
    private int Rating;

    public StudiosFreelancers(int StudioID, String FreelancerID, int Rating) {
        this.StudioID = StudioID;
        this.FreelancerID = FreelancerID;
        this.Rating = Rating;
    }

    public int getStudioID() {
        return StudioID;
    }

    public String getFreelancerID() {
        return FreelancerID;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int Rating) {
        this.Rating = Rating;
    }
    
    
}
