import java.util.*;

public class Theatre {
    private final int id;
    private final String name;
    private final List<Screen> screens = new ArrayList<>();

    public Theatre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addScreen(Screen screen) {
        screens.add(screen);
    }

    public int getTheatreId() { return id; }
    public List<Screen> getScreen() { return screens; }
}