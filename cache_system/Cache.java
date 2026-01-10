import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class Cache<K, V> {

    private final CacheStorage<K, V> cache;
    private final DBStorage<K, V> db;
    private final WritePolicy<K, V> writePolicy;
    private final EvictionAlgorithm<K> eviction;
    private final KeyBasedExecutor executor;

    public Cache(CacheStorage<K, V> cache, DBStorage<K, V> db,
                 WritePolicy<K, V> writePolicy,
                 EvictionAlgorithm<K> eviction,
                 int executors) {
        this.cache = cache;
        this.db = db;
        this.writePolicy = writePolicy;
        this.eviction = eviction;
        this.executor = new KeyBasedExecutor(executors);
    }

    public CompletableFuture<V> accessData(K key) {
        return executor.submitTask(key, () -> {
            try {
                eviction.keyAccessed(key);
                return cache.get(key);
            } catch (Exception e) {
                throw new CompletionException(e);
            }
        });
    }

    public CompletableFuture<Void> updateData(K key, V value) {
        return executor.submitTask(key, () -> {
            try {
                if (!cache.containsKey(key) && cache.size() >= cache.getCapacity()) {
                    K evicted = eviction.evictKey();
                    if (evicted != null) cache.remove(evicted);
                }
                writePolicy.write(key, value, cache, db);
                eviction.keyAccessed(key);
                return null;
            } catch (Exception e) {
                throw new CompletionException(e);
            }
        });
    }

    public void shutdown() {
        executor.shutdown();
    }
}