import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MovieService {
    private final Map<Integer, Movie> movies = new HashMap<>();
    private final AtomicInteger counter = new AtomicInteger();

    public Movie createMovie(String name, int duration) {
        int id = counter.incrementAndGet();
        Movie movie = new Movie(id, name, duration);
        movies.put(id, movie);
        return movie;
    }

    public Movie getMovie(int id) throws Exception {
        if (!movies.containsKey(id))
            throw new Exception("Movie not found");
        return movies.get(id);
    }
}