import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class TokenBucketStrategy implements IRateLimiter {

    private final int bucketCapacity;
    private volatile int refreshRate;
    private final Bucket globalBucket;
    private final ConcurrentHashMap<String, Bucket> userBuckets;
    private final ScheduledExecutorService scheduler;
    private final long refillIntervalMillis;

    private class Bucket {
        private int tokens;
        private final ReentrantLock lock = new ReentrantLock();

        public Bucket(int initialTokens) {
            this.tokens = initialTokens;
        }

        public boolean tryConsume() {
            lock.lock();
            try {
                if (tokens > 0) {
                    tokens--;
                    return true;
                }
                return false;
            } finally {
                lock.unlock();
            }
        }

        public void refill() {
            lock.lock();
            try {
                tokens = Math.min(bucketCapacity, tokens + refreshRate);
            } finally {
                lock.unlock();
            }
        }
    }

    public TokenBucketStrategy(int bucketCapacity, int refreshRate) {
        this.bucketCapacity = bucketCapacity;
        this.refreshRate = refreshRate;
        this.refillIntervalMillis = 1000;
        this.globalBucket = new Bucket(bucketCapacity);
        this.userBuckets = new ConcurrentHashMap<>();
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        startRefillTask();
    }

    private void startRefillTask() {
        scheduler.scheduleAtFixedRate(() -> {
            globalBucket.refill();
            for (Bucket bucket : userBuckets.values()) {
                bucket.refill();
            }
        }, 0, refillIntervalMillis, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean giveAccess(String rateLimitKey) {
        if (rateLimitKey != null && !rateLimitKey.isEmpty()) {
            Bucket bucket = userBuckets.computeIfAbsent(rateLimitKey, key -> new Bucket(bucketCapacity));
            return bucket.tryConsume();
        } else {
            return globalBucket.tryConsume();
        }
    }

    @Override
    public void updateConfiguration(Map<String, Object> config) {
        if (config.containsKey("refreshRate")) {
            this.refreshRate = (int) config.get("refreshRate");
        }
    }

    @Override
    public void shutdown() {
        scheduler.shutdownNow();
    }
}