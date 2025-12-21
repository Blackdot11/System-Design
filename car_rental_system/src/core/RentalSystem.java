import java.util.*;
import pricing.PricingStrategy;
import payment.*;
import vehicle.Vehicle;

public class RentalSystem {

    private static RentalSystem instance;
    private List<RentalStore> stores;
    private Map<Integer, User> users;
    private ReservationManager reservationManager;
    private PaymentProcessor paymentProcessor;

    private RentalSystem() {
        stores = new ArrayList<>();
        users = new HashMap<>();
        reservationManager = new ReservationManager();
        paymentProcessor = new PaymentProcessor();
    }

    public static synchronized RentalSystem get() {
        if (instance == null)
            instance = new RentalSystem();
        return instance;
    }

    public void addStore(RentalStore s) { stores.add(s); }
    public void registerUser(User u) { users.put(u.getId(), u); }

    public RentalStore getStore(int id) {
        return stores.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    public Reservation createReservation(int userId, String reg, int pickId, int retId,
        Date s, Date e, PricingStrategy strategy) {

        User user = users.get(userId);
        RentalStore pick = getStore(pickId);
        RentalStore ret = getStore(retId);

        Vehicle v = pick.getVehicle(reg);

        Reservation r = reservationManager.createReservation(user, v, pick, ret, s, e, strategy);
        r.confirm();
        return r;
    }

    public boolean processPayment(int reservationId, PaymentStrategy strategy) {
        Reservation r = reservationManager.getReservation(reservationId);
        return paymentProcessor.processPayment(r.getTotalAmount(), strategy);
    }
}
