package personal.practice.atlassian.ratelimiter.fixedwindow;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowRateLimiterWithCarryoverMultiUser {
    private final int maxRequests;
    private final long windowSizeInMillis;
    private final ConcurrentHashMap<String, UserBucket> userBuckets = new ConcurrentHashMap<>();

    public FixedWindowRateLimiterWithCarryoverMultiUser(int maxRequests, long windowSizeInSeconds) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInSeconds * 1000;
    }

    public static void main(String[] args) throws InterruptedException {
        FixedWindowRateLimiterWithCarryoverMultiUser limiter = new FixedWindowRateLimiterWithCarryoverMultiUser(5, 10);

        String user1 = "userA";
        String user2 = "userB";

        for (int i = 0; i < 7; i++) {
            System.out.println("UserA Request " + (i + 1) + " allowed: " + limiter.allowRequest(user1));
            System.out.println("UserB Request " + (i + 1) + " allowed: " + limiter.allowRequest(user2));
            Thread.sleep(1000); // Simulating requests
        }

        Thread.sleep(11000); // Simulating time gap to check carryover
        System.out.println("UserA Request after time gap: " + limiter.allowRequest(user1));
    }

    public synchronized boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();

        UserBucket bucket = userBuckets.computeIfAbsent(userId, x -> new UserBucket(currentTime, new AtomicInteger(0),
                0));

        long elapsedTime = currentTime - bucket.lastWindowStart;

        if (elapsedTime >= windowSizeInMillis) {
            // Calculate proportion of unused limit that can be carried over
            double proportionOfCarry = 1.0 - ((double) elapsedTime / windowSizeInMillis);
            int carriedOver = (int) (bucket.previousUnused * proportionOfCarry);

            bucket.lastWindowStart = currentTime;
            bucket.previousUnused = Math.max(0, maxRequests - bucket.count.get());
            bucket.count.set(carriedOver);
        }

        if (bucket.count.get() < maxRequests) {
            bucket.count.incrementAndGet();
            return true;
        }
        return false;
    }

    private static class UserBucket {
        long lastWindowStart;
        AtomicInteger count;
        int previousUnused;

        UserBucket(long lastWindowStart, AtomicInteger count, int previousUnused) {
            this.lastWindowStart = lastWindowStart;
            this.count = count;
            this.previousUnused = previousUnused;
        }
    }
}

