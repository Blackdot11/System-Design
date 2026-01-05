import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ShowService {
    private final Map<Integer, Show> shows = new HashMap<>();
    private final AtomicInteger counter = new AtomicInteger();

    public Show createShow(Movie movie, Screen screen, Date start, int duration) {
        int id = counter.incrementAndGet();
        Show show = new Show(id, movie, screen, start, duration);
        shows.put(id, show);
        return show;
    }

    public Show getShow(int id) throws Exception {
        if (!shows.containsKey(id))
            throw new Exception("Show not found");
        return shows.get(id);
    }
}