import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCacheStorage<K, V> implements CacheStorage<K, V> {

    private final Map<K, V> cache = new ConcurrentHashMap<>();
    private final int capacity;

    public InMemoryCacheStorage(int capacity) {
        this.capacity = capacity;
    }

    public void put(K key, V value) {
        cache.put(key, value);
    }

    public V get(K key) throws Exception {
        if (!cache.containsKey(key)) {
            throw new Exception("Key not in cache");
        }
        return cache.get(key);
    }

    public void remove(K key) throws Exception {
        if (!cache.containsKey(key)) {
            throw new Exception("Key not in cache");
        }
        cache.remove(key);
    }

    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }

    public int size() {
        return cache.size();
    }

    public int getCapacity() {
        return capacity;
    }
}