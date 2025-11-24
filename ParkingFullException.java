import java.lang.Exception;

public class ParkingFullException extends Exception {
    public ParkingFullException(String message) {
        super(message);
    }
}