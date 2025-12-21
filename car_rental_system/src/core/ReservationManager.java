import java.util.*;
import pricing.PricingStrategy;
import vehicle.Vehicle;

public class ReservationManager {
    private Map<Integer, Reservation> reservations = new HashMap<>();
    private int nextId = 1;

    public Reservation createReservation(User user, Vehicle v,
        RentalStore pick, RentalStore ret, Date s, Date e, PricingStrategy pricing) {

        if (!v.isAvailable(s, e))
            throw new RuntimeException("Vehicle not available");

        long diff = e.getTime() - s.getTime();
        int days = (int)(diff / (1000*60*60*24)) + 1;

        double amount = pricing.calculateRentalPrice(v, days);

        Reservation r = new Reservation(nextId++, user, v, pick, ret, s, e, pricing, amount);
        reservations.put(r.getId(), r);
        user.addReservation(r);
        return r;
    }

    public Reservation getReservation(int id) {
        return reservations.get(id);
    }
}
