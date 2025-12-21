import vehicle.Vehicle;

public class DailyPricingStrategy implements PricingStrategy {

    @Override
    public double calculateRentalPrice(Vehicle vehicle, int days) {
        return vehicle.getBaseRentalPrice() * days;
    }
}
