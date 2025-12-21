package VehicleFactoryPattern.ConcreteVehicles;

import VehicleFactoryPattern.Vehicle;
import FareStrategyPattern.ParkingFeeStrategy;

public class CarVehicle extends Vehicle {

    public CarVehicle(String licensePlate, String vehicleType, ParkingFeeStrategy feeStrategy) {
        super(licensePlate, vehicleType, feeStrategy);
    }
}
