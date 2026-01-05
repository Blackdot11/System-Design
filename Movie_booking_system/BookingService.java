import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BookingService {

    private final Map<String, Booking> bookings = new ConcurrentHashMap<>();
    private final ISeatLockProvider seatLockProvider;
    private int counter = 1;

    public BookingService(ISeatLockProvider seatLockProvider) {
        this.seatLockProvider = seatLockProvider;
    }

    public Booking createBooking(User user, Show show, List<Seat> seats) throws Exception {
        seatLockProvider.lockSeats(show, seats, user);
        Booking booking = new Booking(String.valueOf(counter++), show, user, seats);
        bookings.put(booking.getId(), booking);
        return booking;
    }

    public Booking getBooking(String id) throws Exception {
        if (!bookings.containsKey(id))
            throw new Exception("Booking not found");
        return bookings.get(id);
    }

    public void confirmBooking(Booking booking, User user) throws Exception {
        booking.confirmBooking();
        seatLockProvider.unlockSeats(booking.getShow(), booking.getSeatsBooked(), user);
    }

    public List<Seat> getBookedSeats(Show show) {
        List<Seat> result = new ArrayList<>();
        for (Booking b : bookings.values()) {
            if (b.getShow().equals(show) && b.isConfirmed()) {
                result.addAll(b.getSeatsBooked());
            }
        }
        return result;
    }
}