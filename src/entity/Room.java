
package entity;

public class Room {
    
    private int RoomNum;
    private int StudioID;
    private int Price;
    private int Capacity;
    private boolean RecordingCell;

    public Room(int RoomNum, int StudioID, boolean RecordingCell, int capacity, int price) {
        this.RoomNum = RoomNum;
        this.StudioID = StudioID;
        this.RecordingCell = RecordingCell;
        this.Capacity = capacity;
        this.Price = price;
    }
    
    public Room(int StudioID, int RoomNum, boolean RecordingCell) {
        this.RoomNum = RoomNum;
        this.StudioID = StudioID;
        this.RecordingCell = RecordingCell;
    }

    public int getRoomNum() {
        return RoomNum;
    }

    public int getStudioID() {
        return StudioID;
    }

    public int getPrice() {
        return Price;
    }

    public int getCapacity() {
        return Capacity;
    }

    public boolean isRecordingCell() {
        return RecordingCell;
    }

    @Override
    public String toString() {
        return "Room{" + "RoomNum=" + RoomNum + ", StudioID=" + StudioID + ", RecordingCell=" + RecordingCell + '}';
    }
    
    
}
