import enums.VehicleType;

public class BikeVehicle extends Vehicle {
    private static final double RATE_MULTIPLIER = 0.5;

    public BikeVehicle(String registrationNumber, String model, VehicleType type, double baseRentalPrice) {
        super(registrationNumber, model, type, baseRentalPrice);
    }

    @Override
    public double calculateRentalFee(int days) {
        return getBaseRentalPrice() * RATE_MULTIPLIER * days;
    }
}
