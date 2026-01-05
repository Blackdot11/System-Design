import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TheatreService {

    private final Map<Integer, Theatre> theatres = new HashMap<>();
    private final Map<Integer, Screen> screens = new HashMap<>();
    private final Map<Integer, Seat> seats = new HashMap<>();

    private final AtomicInteger theatreCounter = new AtomicInteger();
    private final AtomicInteger screenCounter = new AtomicInteger();
    private final AtomicInteger seatCounter = new AtomicInteger();

    public Theatre createTheatre(String name) {
        int id = theatreCounter.incrementAndGet();
        Theatre theatre = new Theatre(id, name);
        theatres.put(id, theatre);
        return theatre;
    }

    public Screen createScreenInTheatre(String name, Theatre theatre) {
        int id = screenCounter.incrementAndGet();
        Screen screen = new Screen(id, name, theatre);
        theatre.addScreen(screen);
        screens.put(id, screen);
        return screen;
    }

    public Seat createSeatInScreen(int row, SeatCategory category, Screen screen) {
        int id = seatCounter.incrementAndGet();
        Seat seat = new Seat(id, row, category);
        seats.put(id, seat);
        screen.addSeat(seat);
        return seat;
    }

    public Seat getSeat(int id) throws Exception {
        if (!seats.containsKey(id))
            throw new Exception("Seat not found");
        return seats.get(id);
    }

    public Screen getScreen(int id) throws Exception {
        if (!screens.containsKey(id))
            throw new Exception("Screen not found");
        return screens.get(id);
    }

    public Theatre getTheatre(int id) throws Exception {
        if (!theatres.containsKey(id))
            throw new Exception("Theatre not found");
        return theatres.get(id);
    }
}