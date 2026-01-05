import java.util.*;

public class Screen {
    private final int id;
    private final String name;
    private final Theatre theatre;
    private final List<Seat> seats = new ArrayList<>();

    public Screen(int id, String name, Theatre theatre) {
        this.id = id;
        this.name = name;
        this.theatre = theatre;
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
    }

    public int getScreenId() { return id; }
    public List<Seat> getSeats() { return seats; }
    public Theatre getTheatre() { return theatre; }
}