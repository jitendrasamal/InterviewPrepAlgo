package personal.practice.atlassian.ratelimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RateLimiter_SlidingWindow {
    private final long windowSizeInMillis;
    private final long maxRequests;
    private final ConcurrentHashMap<String, SlidingWindow> slidingWindowsMap = new ConcurrentHashMap<>();

    public RateLimiter_SlidingWindow(long windowSizeInMillis, long maxRequests) {
        this.windowSizeInMillis = windowSizeInMillis;
        this.maxRequests = maxRequests;
    }

    /*
        public static void main(String[] args) throws InterruptedException {
            RateLimiter_SlidingWindow rateLimiterSlidingWindow = new RateLimiter_SlidingWindow(1_000, 2);
            String clientId = "user1";

            for (int i = 0; i < 100; i++) {
                System.out.println("Request " + (i + 1) + ": " + rateLimiterSlidingWindow.allowRequest(clientId));
                Thread.sleep(100);
            }
        }

     */
    public static void main(String[] args) throws InterruptedException {
        RateLimiter_SlidingWindow rateLimiterSlidingWindow = new RateLimiter_SlidingWindow(1_000, 2);
        final String[] clientIds = {"user1", "user2"};
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 1; i < 100; i++) {
            final int requestNumber = i;
            executorService.submit(() -> {
                String userId = clientIds[requestNumber % clientIds.length];
                System.out.format("ThreadId: %d, Request %d: userID: %s : %b \n", Thread.currentThread().getId(), requestNumber, userId, rateLimiterSlidingWindow.allowRequest(userId));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executorService.shutdown();
    }

    public boolean allowRequest(String clientId) {
        SlidingWindow slidingWindow = slidingWindowsMap.computeIfAbsent(clientId, k -> new SlidingWindow(windowSizeInMillis, maxRequests));
        return slidingWindow.allowRequest();
    }
}
