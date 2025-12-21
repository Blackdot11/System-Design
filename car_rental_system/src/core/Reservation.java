import java.util.*;
import enums.ReservationStatus;
import vehicle.Vehicle;
import pricing.PricingStrategy;

public class Reservation {
    private int id;
    private User user;
    private Vehicle vehicle;
    private RentalStore pickup;
    private RentalStore drop;
    private Date start;
    private Date end;
    private double totalAmount;
    private ReservationStatus status;

    public Reservation(int id, User user, Vehicle vehicle,
        RentalStore pickup, RentalStore drop,
        Date start, Date end,
        PricingStrategy pricing, double totalAmount) {

        this.id = id;
        this.user = user;
        this.vehicle = vehicle;
        this.pickup = pickup;
        this.drop = drop;
        this.start = start;
        this.end = end;
        this.totalAmount = totalAmount;
        this.status = ReservationStatus.PENDING;
    }

    public void confirm() {
        vehicle.blockSlot(start, end);
        this.status = ReservationStatus.CONFIRMED;
    }

    public int getId() { return id; }
    public double getTotalAmount() { return totalAmount; }
}
