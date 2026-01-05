import java.util.*;

public class SeatAvailabilityService {

    private final BookingService bookingService;
    private final ISeatLockProvider seatLockProvider;

    public SeatAvailabilityService(
            BookingService bookingService,
            ISeatLockProvider seatLockProvider) {
        this.bookingService = bookingService;
        this.seatLockProvider = seatLockProvider;
    }

    public List<Seat> getAvailableSeats(Show show) {
        List<Seat> available = new ArrayList<>(show.getScreen().getSeats());
        available.removeAll(bookingService.getBookedSeats(show));
        available.removeAll(seatLockProvider.getLockedSeats(show));
        return available;
    }
}