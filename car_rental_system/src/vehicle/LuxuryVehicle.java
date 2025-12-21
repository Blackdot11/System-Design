import enums.VehicleType;

public class LuxuryVehicle extends Vehicle {
    private static final double RATE_MULTIPLIER = 2.5;

    public LuxuryVehicle(String registrationNumber, String model, VehicleType type, double baseRentalPrice) {
        super(registrationNumber, model, type, baseRentalPrice);
    }

    @Override
    public double calculateRentalFee(int days) {
        return getBaseRentalPrice() * RATE_MULTIPLIER * days;
    }
}
