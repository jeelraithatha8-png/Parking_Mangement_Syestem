import java.util.UUID;

public class ParkingSpot {
    
    public enum SpotType {
        MOTORCYCLE, COMPACT, LARGE
    }
    
    private final UUID spotId;
    private final SpotType type;
    private boolean isOccupied; // Encapsulated state
    
    public ParkingSpot(SpotType type) {
        this.spotId = UUID.randomUUID();
        this.type = type;
        this.isOccupied = false;
    }
    
    public boolean occupy() {
        if (!isOccupied) {
            this.isOccupied = true;
            return true;
        }
        return false;
    }
    
    public boolean free() {
        if (isOccupied) {
            this.isOccupied = false;
            return true;
        }
        return false;
    }

    public UUID getSpotId() { return spotId; }
    public SpotType getType() { return type; }
    public boolean isOccupied() { return isOccupied; }
}