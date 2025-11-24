public class Car extends Vehicle { // Inheritance

    public Car(String licensePlate) {
        super(licensePlate);
    }

    @Override
    public double calculateHourlyRate() { // Polymorphism
        return 2.50; // Hourly rate for a car
    }

    @Override
    public ParkingSpot.SpotType getRequiredSpotType() {
        return ParkingSpot.SpotType.COMPACT;
    }
}