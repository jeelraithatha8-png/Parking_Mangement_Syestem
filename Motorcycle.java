public class Motorcycle extends Vehicle { // Inheritance

    public Motorcycle(String licensePlate) {
        super(licensePlate);
    }

    @Override
    public double calculateHourlyRate() { // Polymorphism
        return 1.00; // Hourly rate for a motorcycle
    }

    @Override
    public ParkingSpot.SpotType getRequiredSpotType() {
        return ParkingSpot.SpotType.MOTORCYCLE;
    }
}