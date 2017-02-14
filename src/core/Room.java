
package core;

public class Room {
    
    private int RoomNum;
    private int StudioID;
    private int Price;
    private int Capacity;
    private boolean RecordingCell;

    public Room(int RoomNum, int StudioID, boolean RecordingCell) {
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
    
    
}
