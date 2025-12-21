package VehicleFactoryPattern.ConcreteVehicles;

import VehicleFactoryPattern.Vehicle;
import FareStrategyPattern.ParkingFeeStrategy;

public class OtherVehicle extends Vehicle {

    public OtherVehicle(String licensePlate, String vehicleType, ParkingFeeStrategy feeStrategy) {
        super(licensePlate, vehicleType, feeStrategy);
    }
}
