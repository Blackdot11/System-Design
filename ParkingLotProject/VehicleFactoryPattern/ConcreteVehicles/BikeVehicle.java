package VehicleFactoryPattern.ConcreteVehicles;

import VehicleFactoryPattern.Vehicle;
import FareStrategyPattern.ParkingFeeStrategy;

public class BikeVehicle extends Vehicle {

    public BikeVehicle(String licensePlate, String vehicleType, ParkingFeeStrategy feeStrategy) {
        super(licensePlate, vehicleType, feeStrategy);
    }
}
