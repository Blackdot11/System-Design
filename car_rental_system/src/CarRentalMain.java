import java.text.SimpleDateFormat;
import pricing.*;
import payment.*;
import vehicle.*;
import enums.VehicleType;

public class CarRentalMain {
    public static void main(String[] args) throws Exception {

        RentalSystem rental = RentalSystem.get();

        RentalStore s1 = new RentalStore(1, "Downtown Rentals",
            new Location("123 Main St", "NYC", "NY", "10001"));
        rental.addStore(s1);

        Vehicle v1 = VehicleFactory.createVehicle(VehicleType.ECONOMY, "EC001", "Toyota", 50);
        s1.addVehicle(v1);

        User u1 = new User(1, "John Doe", "john@mail.com");
        rental.registerUser(u1);

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        Reservation r = rental.createReservation(
            u1.getId(), "EC001", 1, 1,
            sdf.parse("03/01/2025"), sdf.parse("03/05/2025"),
            new DailyPricingStrategy()
        );

        rental.processPayment(r.getId(), new CreditCardPayment());

        System.out.println("Reservation " + r.getId() + " completed!");
    }
}
