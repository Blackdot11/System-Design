public class PaymentService {

    private final PaymentStrategy paymentStrategy;
    private final BookingService bookingService;

    public PaymentService(
            PaymentStrategy paymentStrategy,
            BookingService bookingService) {
        this.paymentStrategy = paymentStrategy;
        this.bookingService = bookingService;
    }

    public void processPayment(String bookingId, User user) throws Exception {
        if (paymentStrategy.processPayment()) {
            bookingService.confirmBooking(
                bookingService.getBooking(bookingId), user);
        }
    }
}