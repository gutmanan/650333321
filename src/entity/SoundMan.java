
package entity;

public class SoundMan {
    
    private int freelancerID;
    String freelancerName;
    private int TotalPayment;
    private int AdvancePay;
    private boolean Producer;
    private boolean MixTech;
    private boolean MasterTech;

    public SoundMan(int freelancerID, boolean Producer, boolean MixTech, boolean MasterTech, String freelancerName, int TotalPayment, int AdvancePay) {
        this.freelancerID = freelancerID;
        this.freelancerName = freelancerName;
        this.TotalPayment = TotalPayment;
        this.AdvancePay = AdvancePay;
        this.Producer = Producer;
        this.MixTech = MixTech;
        this.MasterTech = MasterTech;
    }

    public int getFreelancerID() {
        return freelancerID;
    }

    public String getFreelancerName() {
        return freelancerName;
    }

    public int getTotalPayment() {
        return TotalPayment;
    }

    public int getAdvancePay() {
        return AdvancePay;
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
