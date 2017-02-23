
package entity;

public class SoundMan {
    
    private String freelancerID;
    private int TotalPayment;
    private int AdvancePay;
    private boolean Producer;
    private boolean MixTech;
    private boolean MasterTech;

    public SoundMan(String freelancerID, int AdvancePay, int TotalPayment, boolean MasterTech, boolean MixTech, boolean Producer) {
        this.freelancerID = freelancerID;
        this.TotalPayment = TotalPayment;
        this.AdvancePay = AdvancePay;
        this.Producer = Producer;
        this.MixTech = MixTech;
        this.MasterTech = MasterTech;
    }

    public String getFreelancerID() {
        return freelancerID;
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
