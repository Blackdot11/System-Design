import vehicle.Vehicle;

public class HourlyPricingStrategy implements PricingStrategy {
    private static final double HOURLY_MULTIPLIER = 0.2;

    @Override
    public double calculateRentalPrice(Vehicle vehicle, int hours) {
        return vehicle.getBaseRentalPrice() * HOURLY_MULTIPLIER * hours;
    }
}
