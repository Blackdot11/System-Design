import java.util.*;
import vehicle.Vehicle;

public class RentalStore {
    private int id;
    private String name;
    private Location location;
    private Map<String, Vehicle> vehicles;

    public RentalStore(int id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.vehicles = new HashMap<>();
    }

    public void addVehicle(Vehicle v) {
        vehicles.put(v.getRegistrationNumber(), v);
    }

    public Vehicle getVehicle(String reg) {
        return vehicles.get(reg);
    }

    public int getId() { return id; }
}
