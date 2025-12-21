import vehicle.Vehicle;

public class WeeklyPricingStrategy implements PricingStrategy {
    private static final double WEEKLY_DISCOUNT = 0.8;

    @Override
    public double calculateRentalPrice(Vehicle vehicle, int days) {
        int weeks = days / 7;
        int rem = days % 7;

        double weeklyCost = vehicle.getBaseRentalPrice() * 7 * WEEKLY_DISCOUNT * weeks;
        double remCost = vehicle.getBaseRentalPrice() * rem;
        return weeklyCost + remCost;
    }
}
