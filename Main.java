import java.util.concurrent.TimeUnit;

public class Main {
    
    public static void main(String[] args) {
        
        System.out.println("--- Starting Smart Parking Lot System Simulation ---");
        
        ParkingService parkingService = new ParkingService();

        // 1. Vehicle Entry
        Vehicle car1 = new Car("KA01AA1234");
        Vehicle bike1 = new Motorcycle("MH02BB9876");
        ParkingTicket carTicket = null;
        ParkingTicket bikeTicket = null;

        try {
            // Car 1 enters
            carTicket = parkingService.enterVehicle(car1);
            
            // Wait to simulate passage of time
            TimeUnit.SECONDS.sleep(1); 

            // Bike 1 enters
            bikeTicket = parkingService.enterVehicle(bike1);

        } catch (ParkingFullException e) {
            System.err.println("🚫 Error: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("\n--- Simulating 4 seconds of Parking Time ---");
        try {
            // Wait to ensure minimum charge calculation
            TimeUnit.SECONDS.sleep(4); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 2. Vehicle Exit
        System.out.println("\n--- Exiting Vehicles ---");
        
        // Bike 1 exits
        if (bikeTicket != null) {
            try {
                parkingService.exitVehicle(bikeTicket.getTicketId());
            } catch (InvalidTicketException e) {
                System.err.println("🚫 Exit Error: " + e.getMessage());
            }
        }
        
        // Car 1 exits
        if (carTicket != null) {
            try {
                parkingService.exitVehicle(carTicket.getTicketId());
            } catch (InvalidTicketException e) {
                System.err.println("🚫 Exit Error: " + e.getMessage());
            }
        }
        
        // 3. Testing Error Handling
        System.out.println("\n--- Testing Error Handling ---");
        if (carTicket != null) {
             try {
                // Try to exit the car using the same ticket again
                parkingService.exitVehicle(carTicket.getTicketId()); 
            } catch (InvalidTicketException e) {
                System.err.println("✅ Error Handling Successful: " + e.getMessage());
            }
        }
       
        System.out.println("\n--- Simulation Complete ---");
    }
}