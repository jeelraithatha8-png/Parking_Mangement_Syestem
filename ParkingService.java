import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ParkingService {

    // Simulates the physical parking structure
    private final Map<UUID, ParkingSpot> spotMap = new HashMap<>();
    // Tracks active tickets
    private final Map<UUID, ParkingTicket> activeTickets = new HashMap<>();

    public ParkingService() {
        // Initialize a set of spots (CRUD/Management)
        spotMap.put(UUID.randomUUID(), new ParkingSpot(ParkingSpot.SpotType.COMPACT));
        spotMap.put(UUID.randomUUID(), new ParkingSpot(ParkingSpot.SpotType.COMPACT));
        spotMap.put(UUID.randomUUID(), new ParkingSpot(ParkingSpot.SpotType.MOTORCYCLE));
    }

    /**
     * Handles vehicle entry and ticket issuance (Module A: Entry)
     * Synchronized method for thread safety (Reliability NFR).
     */
    public synchronized ParkingTicket enterVehicle(Vehicle vehicle) throws ParkingFullException {
        
        ParkingSpot allocatedSpot = findAvailableSpot(vehicle.getRequiredSpotType());

        if (allocatedSpot == null) {
            throw new ParkingFullException("Lot is full for vehicle type: " + vehicle.getRequiredSpotType());
        }

        allocatedSpot.occupy(); 
        ParkingTicket ticket = new ParkingTicket(vehicle, allocatedSpot);
        
        activeTickets.put(ticket.getTicketId(), ticket);

        System.out.println("✅ Vehicle " + vehicle.getLicensePlate() + " entered. Spot: " 
                         + allocatedSpot.getSpotId() + ". Ticket ID: " + ticket.getTicketId());
        
        return ticket;
    }

    /**
     * Finds the first available spot of the required type.
     */
    private ParkingSpot findAvailableSpot(ParkingSpot.SpotType requiredType) {
        for (ParkingSpot spot : spotMap.values()) {
            if (!spot.isOccupied() && spot.getType() == requiredType) {
                return spot;
            }
        }
        return null;
    }

    /**
     * Handles vehicle exit and fee calculation (Module C: Exit/Payment)
     * Synchronized method for thread safety.
     */
    public synchronized double exitVehicle(UUID ticketId) throws InvalidTicketException {
        
        ParkingTicket ticket = activeTickets.get(ticketId);
        if (ticket == null) {
            throw new InvalidTicketException("Invalid or already processed ticket ID: " + ticketId);
        }

        // Calculate parking duration
        LocalDateTime exitTime = LocalDateTime.now();
        LocalDateTime entryTime = ticket.getEntryTime();
        long hours = ChronoUnit.HOURS.between(entryTime, exitTime);
        if (ChronoUnit.MINUTES.between(entryTime.plusHours(hours), exitTime) > 0) {
            hours++; // Charge for any part of an hour
        }
        
        hours = Math.max(1, hours); // Minimum 1 hour charge

        // Calculate total fee (Polymorphism)
        double hourlyRate = ticket.getVehicle().calculateHourlyRate();
        double totalFee = hours * hourlyRate;

        // Free up the spot and remove the ticket
        ticket.getSpot().free();
        activeTickets.remove(ticketId);

        System.out.println("💵 Vehicle " + ticket.getVehicle().getLicensePlate() + " exited.");
        System.out.println("    Duration: " + hours + " hours. Rate: $" + hourlyRate);
        System.out.println("    Total Fee: $" + String.format("%.2f", totalFee));

        return totalFee;
    }
}