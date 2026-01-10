import java.util.concurrent.*;
import java.util.function.Supplier;

public class KeyBasedExecutor {

    private final ExecutorService[] executors;

    public KeyBasedExecutor(int n) {
        executors = new ExecutorService[n];
        for (int i = 0; i < n; i++) {
            executors[i] = Executors.newSingleThreadExecutor();
        }
    }

    public <T> CompletableFuture<T> submitTask(Object key, Supplier<T> task) {
        int index = Math.abs(key.hashCode() % executors.length);
        return CompletableFuture.supplyAsync(task, executors[index]);
    }

    public void shutdown() {
        for (ExecutorService e : executors) {
            e.shutdown();
        }
    }
}