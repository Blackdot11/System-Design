import vehicle.Vehicle;

public interface PricingStrategy {
    double calculateRentalPrice(Vehicle vehicle, int rentalPeriod);
}
