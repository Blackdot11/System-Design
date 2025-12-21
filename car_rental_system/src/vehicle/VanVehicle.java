import enums.VehicleType;

public class VanVehicle extends Vehicle {
    private static final double RATE_MULTIPLIER = 1.8;

    public VanVehicle(String registrationNumber, String model, VehicleType type, double baseRentalPrice) {
        super(registrationNumber, model, type, baseRentalPrice);
    }

    @Override
    public double calculateRentalFee(int days) {
        return getBaseRentalPrice() * RATE_MULTIPLIER * days;
    }
}
