import java.util.UUID;

public abstract class Vehicle {
    
    private final String licensePlate;
    private final UUID vehicleId;
    
    // Abstract method: forces fee calculation in subclasses (Polymorphism)
    public abstract double calculateHourlyRate(); 
    
    public Vehicle(String licensePlate) {
        this.licensePlate = licensePlate;
        this.vehicleId = UUID.randomUUID();
    }

    public String getLicensePlate() { return licensePlate; }
    public UUID getVehicleId() { return vehicleId; }
    
    // Abstract method: forces required spot type in subclasses
    public abstract ParkingSpot.SpotType getRequiredSpotType();
}