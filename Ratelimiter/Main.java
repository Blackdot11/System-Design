import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        demonstrateRateLimiting();
    }

    private static void demonstrateRateLimiting() {
        Map<String, Object> config = new HashMap<>();
        config.put("capacity", 5);
        config.put("refreshRate", 1);

        ExecutorService executor = Executors.newFixedThreadPool(10);
        RateLimiterController controller = new RateLimiterController(RateLimiterType.TOKEN_BUCKET, config, executor);

        System.out.println("=== EXAMPLE 1: Global rate limiting – Burst of requests ===");
        sendBurstRequests(controller, 10, null);

        System.out.println("\n=== EXAMPLE 2: After waiting for tokens refill ===");
        System.out.println("Waiting 5 seconds for tokens to refill...");
        sleep(5000);
        sendBurstRequests(controller, 10, null);

        System.out.println("\n=== EXAMPLE 3: Per-user rate limiting ===");
        String[] users = {"user1", "user2", "user3"};
        for (String user : users) {
            System.out.println("\nRequests for " + user + ":");
            sendBurstRequests(controller, 7, user);
        }

        System.out.println("\n=== EXAMPLE 4: High concurrency scenario ===");
        List<CompletableFuture<Boolean>> futures = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            futures.add(controller.processRequest(null));
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        long allowed = futures.stream().filter(CompletableFuture::join).count();
        System.out.printf("High concurrency results: %d allowed, %d blocked%n", allowed, 20 - allowed);

        controller.shutdown();
    }

    private static void sendBurstRequests(RateLimiterController controller, int count, String rateLimitKey) {
        List<CompletableFuture<Boolean>> futures = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            futures.add(controller.processRequest(rateLimitKey));
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        long allowed = futures.stream().filter(CompletableFuture::join).count();
        System.out.printf("Results: %d allowed, %d blocked (total: %d)%n", allowed, count - allowed, count);
    }

    private static void sleep(long millis) {
        try { Thread.sleep(millis); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}