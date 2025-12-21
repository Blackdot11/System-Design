import java.util.*;

public class User {
    private int id;
    private String name;
    private String email;
    private List<Reservation> reservations;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.reservations = new ArrayList<>();
    }

    public void addReservation(Reservation r) {
        reservations.add(r);
    }

    public int getId() { return id; }
}
