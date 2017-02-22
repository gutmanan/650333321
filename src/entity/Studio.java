
package entity;

public class Studio {
    
    private int StudioID;
    private String StudioName;
    private String Description;
    private String Email;
    private String PhoneNum;
    private String Address;

    public Studio(int StudioID, String name, String Description, String Email, String PhoneNum, String Address) {
        this.StudioID = StudioID;
        this.StudioName = name;
        this.Description = Description;
        this.Email = Email;
        this.PhoneNum = PhoneNum;
        this.Address = Address;
    }

    public int getStudioID() {
        return StudioID;
    }

    public String getStudioName() {
        return StudioName;
    }

    public String getDescription() {
        return Description;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public String getAddress() {
        return Address;
    }

    @Override
    public String toString() {
        return String.valueOf(StudioID);
    }
}
