package personal.practice.atlassian.ratelimiter.fixedwindow.pratice;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowRateLimiterWithCarryoverMultiUser {

    private final ConcurrentHashMap<String, UserBucket> userBuckets;
    long windowSizeInMilliSecond;
    int maximumRequestAllowed;

    public FixedWindowRateLimiterWithCarryoverMultiUser(long windowSizeInMilliSecond, int maximumRequestAllowed) {
        this.windowSizeInMilliSecond = windowSizeInMilliSecond;
        this.maximumRequestAllowed = maximumRequestAllowed;
        userBuckets = new ConcurrentHashMap<>();
    }

    public synchronized boolean isAllowed(String token) {
        long timeNow = System.currentTimeMillis();
        UserBucket userBucket = userBuckets.computeIfAbsent(token, x -> new UserBucket(timeNow));

        long timLapse = timeNow - userBucket.windowStartTime;
        if (timLapse > windowSizeInMilliSecond) {
            double caryRatio = 1.0 - ((double) timLapse / windowSizeInMilliSecond);
            int carry = (int) caryRatio * userBucket.previousUnused;

            userBucket.windowStartTime = timeNow;
            userBucket.previousUnused = Math.max(0, (maximumRequestAllowed - userBucket.numberOfRequest.get()));
            userBucket.numberOfRequest.set(carry);
        }

        if (userBucket.numberOfRequest.get() < maximumRequestAllowed) {
            userBucket.numberOfRequest.incrementAndGet();
            return true;
        }

        return false;
    }

    private static class UserBucket {
        long windowStartTime;
        AtomicInteger numberOfRequest;
        int previousUnused;

        public UserBucket(long windowStartTime) {
            this.windowStartTime = windowStartTime;
            this.numberOfRequest = new AtomicInteger(0);
            this.previousUnused = 0;
        }
    }
}
