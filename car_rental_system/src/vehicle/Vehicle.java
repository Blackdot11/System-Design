import java.util.*;
import enums.VehicleType;
import enums.VehicleStatus;
import utils.DateRange;

public abstract class Vehicle {
    private String registrationNumber;
    private String model;
    private VehicleType type;
    private VehicleStatus status;
    private double baseRentalPrice;
    private List<DateRange> reservedSlots;

    public Vehicle(String registrationNumber, String model, VehicleType type, double baseRentalPrice) {
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.type = type;
        this.baseRentalPrice = baseRentalPrice;
        this.status = VehicleStatus.AVAILABLE;
        this.reservedSlots = new ArrayList<>();
    }

    public abstract double calculateRentalFee(int days);

    public boolean isAvailable(Date start, Date end) {
        for (DateRange r : reservedSlots) {
            if (r.overlaps(start, end)) return false;
        }
        return true;
    }

    public void blockSlot(Date start, Date end) {
        reservedSlots.add(new DateRange(start, end));
        this.status = VehicleStatus.RESERVED;
    }

    public void releaseSlot(Date start, Date end) {
        reservedSlots.removeIf(r -> r.matches(start, end));
        this.status = VehicleStatus.AVAILABLE;
    }

    public String getRegistrationNumber() { return registrationNumber; }
    public VehicleType getType() { return type; }
    public double getBaseRentalPrice() { return baseRentalPrice; }
    public VehicleStatus getStatus() { return status; }
}
