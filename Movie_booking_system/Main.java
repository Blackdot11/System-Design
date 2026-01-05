import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        MovieService movieService = new MovieService();
        TheatreService theatreService = new TheatreService();
        ShowService showService = new ShowService();

        ISeatLockProvider lockProvider = new SeatLockProvider(600);
        BookingService bookingService = new BookingService(lockProvider);
        SeatAvailabilityService availabilityService =
                new SeatAvailabilityService(bookingService, lockProvider);

        Movie movie = movieService.createMovie("Inception", 150);
        Theatre theatre = theatreService.createTheatre("PVR");
        Screen screen = theatreService.createScreenInTheatre("Screen 1", theatre);

        theatreService.createSeatInScreen(1, SeatCategory.GOLD, screen);

        Show show = showService.createShow(movie, screen, new Date(), 150);
        User user = new User("John", "john@gmail.com");

        Booking booking = bookingService.createBooking(
                user, show, screen.getSeats());

        PaymentService paymentService =
                new PaymentService(new DebitCardStrategy(), bookingService);

        paymentService.processPayment(booking.getId(), user);

        System.out.println("Booking confirmed: " + booking.isConfirmed());
    }
}