package FareStrategyPattern;

import FareStrategyPattern.DurationType;

public interface ParkingFeeStrategy {
    double calculateFee(String vehicleType, int duration, DurationType durationType);
}
