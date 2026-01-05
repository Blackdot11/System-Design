import java.util.List;

public class Booking {
    private final String id;
    private final Show show;
    private final List<Seat> seatsBooked;
    private final User user;
    private BookingStatus bookingStatus = BookingStatus.CREATED;

    public Booking(String id, Show show, User user, List<Seat> seatsBooked) {
        this.id = id;
        this.show = show;
        this.user = user;
        this.seatsBooked = seatsBooked;
    }

    public boolean isConfirmed() {
        return bookingStatus == BookingStatus.CONFIRMED;
    }

    public void confirmBooking() {
        bookingStatus = BookingStatus.CONFIRMED;
    }

    public String getId() { return id; }
    public Show getShow() { return show; }
    public List<Seat> getSeatsBooked() { return seatsBooked; }
    public User getUser() { return user; }
}