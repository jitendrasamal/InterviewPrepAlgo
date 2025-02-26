package personal.practice.atlassian.ratelimiter.fixedwindow;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowRateLimiterMultiUser {
    private final int maxRequests;
    private final long windowSizeInMillis;
    private final ConcurrentHashMap<String, UserBucket> userBuckets = new ConcurrentHashMap<>();

    public FixedWindowRateLimiterMultiUser(int maxRequests, long windowSizeInSeconds) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInSeconds * 1000;
    }

    public static void main(String[] args) throws InterruptedException {
        FixedWindowRateLimiterMultiUser limiter = new FixedWindowRateLimiterMultiUser(5, 10); // 5 requests per 10 sec

        String user1 = "userA";
        String user2 = "userB";

        for (int i = 0; i < 7; i++) {
            System.out.println("UserA Request " + (i + 1) + " allowed: " + limiter.allowRequest(user1));
            System.out.println("UserB Request " + (i + 1) + " allowed: " + limiter.allowRequest(user2));
            Thread.sleep(1000); // Simulate requests every second
        }
    }

    public synchronized boolean allowRequest(String userId) {
        long currentWindow = System.currentTimeMillis() / windowSizeInMillis;
        UserBucket bucket = userBuckets.computeIfAbsent(userId, x -> new UserBucket(currentWindow,
                new AtomicInteger(0)));

        // Reset bucket if it's a new window
        if (bucket.window != currentWindow) {
            bucket.window = currentWindow;
            bucket.count.set(0);
        }

        if (bucket.count.get() < maxRequests) {
            bucket.count.incrementAndGet();
            return true;
        }
        return false;
    }

    private static class UserBucket {
        long window;
        AtomicInteger count;

        UserBucket(long window, AtomicInteger count) {
            this.window = window;
            this.count = count;
        }
    }
}
