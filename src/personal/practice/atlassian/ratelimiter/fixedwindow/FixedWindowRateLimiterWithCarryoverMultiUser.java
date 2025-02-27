package personal.practice.atlassian.ratelimiter.fixedwindow;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowRateLimiterWithCarryoverMultiUser {
    private final int maxRequests;
    private final long windowSizeInMillis;
    private final int maxCarryover;
    private final ConcurrentHashMap<String, UserBucket> userBuckets = new ConcurrentHashMap<>();

    public FixedWindowRateLimiterWithCarryoverMultiUser(int maxRequests, int maxCarryover, long windowSizeInSeconds) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInSeconds * 1000;
        this.maxCarryover = maxCarryover;
    }

    public static void main(String[] args) throws InterruptedException {
        FixedWindowRateLimiterWithCarryoverMultiUser limiter = new FixedWindowRateLimiterWithCarryoverMultiUser(5, 100
                , 1);

        String user1 = "userA";
        String user2 = "userB";

        for (int i = 0; i < 7; i++) {
            System.out.println("UserA Request " + (i + 1) + " allowed: " + limiter.allowRequest(user1));
            System.out.println("UserB Request " + (i + 1) + " allowed: " + limiter.allowRequest(user2));
            Thread.sleep(100); // Simulating requests
        }

        Thread.sleep(2300); // Simulating time gap to check carryover
        for (int i = 0; i < 30; i++) {
            System.out.println("UserA Request after time gap: "+ i + " : " + limiter.allowRequest(user1));
        }
    }

    public synchronized boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();

        UserBucket bucket = userBuckets.computeIfAbsent(userId, x -> new UserBucket(currentTime));

        long elapsedTime = currentTime - bucket.lastWindowStart;

        if (elapsedTime >= windowSizeInMillis) {

            /*
               10 5
               0-5 -> 5

            30
             */
            int maxRequestsAllowedTillNow =
                    (int) ((currentTime - bucket.lastWindowStart) / windowSizeInMillis * maxRequests);
            int previousUnused = Math.max(0, maxRequestsAllowedTillNow - bucket.count.get());
            previousUnused = Math.min(previousUnused, maxCarryover);


            bucket.lastWindowStart = currentTime;
            bucket.count.set(-previousUnused);
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

        UserBucket(long lastWindowStart) {
            this.lastWindowStart = lastWindowStart;
            this.count = new AtomicInteger(0);
        }
    }
}

