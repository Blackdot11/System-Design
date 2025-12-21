package VehicleFactoryPattern;

import FareStrategyPattern.ParkingFeeStrategy;
import VehicleFactoryPattern.ConcreteVehicles.*;

public class VehicleFactory {

    public static Vehicle createVehicle(String vehicleType, String licensePlate, ParkingFeeStrategy feeStrategy) {

        if (vehicleType.equalsIgnoreCase("car")) {
            return new CarVehicle(licensePlate, vehicleType, feeStrategy);
        } else if (vehicleType.equalsIgnoreCase("bike")) {
            return new BikeVehicle(licensePlate, vehicleType, feeStrategy);
        }

        return new OtherVehicle(licensePlate, vehicleType, feeStrategy);
    }
}
