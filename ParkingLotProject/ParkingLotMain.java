import FareStrategyPattern.*;
import ParkingLotSystem.*;
import PaymentStrategyPattern.*;
import PaymentStrategyPattern.ConcretePaymentStrategies.*;
import VehicleFactoryPattern.*;

import java.util.Scanner;

public class ParkingLotMain {

    public static void main(String[] args) {

        ParkingLot parkingLot = new ParkingLotBuilder()
                .createFloor(1, 2, 2)
                .createFloor(2, 3, 1)
                .build();

        ParkingFeeStrategy basic = new BasicHourlyRateStrategy();
        ParkingFeeStrategy premium = new PremiumRateStrategy();

        Vehicle car = VehicleFactory.createVehicle("Car", "CAR123", basic);
        Vehicle bike = VehicleFactory.createVehicle("Bike", "BIKE123", premium);

        ParkingSpot carSpot = parkingLot.parkVehicle(car);
        ParkingSpot bikeSpot = parkingLot.parkVehicle(bike);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose payment method: 1) Card 2) Cash");
        int choice = scanner.nextInt();

        if (carSpot != null) {
            double fee = car.calculateFee(2, DurationType.HOURS);
            PaymentStrategy ps = (choice == 1)
                    ? new CreditCardPayment(fee)
                    : new CashPayment(fee);

            ps.processPayment(fee);
            parkingLot.vacateSpot(carSpot, car);
        }

        if (bikeSpot != null) {
            double fee = bike.calculateFee(3, DurationType.HOURS);
            PaymentStrategy ps = (choice == 1)
                    ? new CreditCardPayment(fee)
                    : new CashPayment(fee);

            ps.processPayment(fee);
            parkingLot.vacateSpot(bikeSpot, bike);
        }

        scanner.close();
    }
}
