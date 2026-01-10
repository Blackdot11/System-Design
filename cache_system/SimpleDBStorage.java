import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleDBStorage<K, V> implements DBStorage<K, V> {

    private final Map<K, V> database = new ConcurrentHashMap<>();

    public void write(K key, V value) {
        database.put(key, value);
    }

    public V read(K key) throws Exception {
        if (!database.containsKey(key)) {
            throw new Exception("Key not found in DB");
        }
        return database.get(key);
    }

    public void delete(K key) throws Exception {
        if (!database.containsKey(key)) {
            throw new Exception("Key not found in DB");
        }
        database.remove(key);
    }
}