package personal.practice.ratelimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class RateLimiter_LeakyBucket {
    private final Map<String, LeakyBucket> clientBuckets;
    private final int bucketCapacity;
    private final double leakRate;

    public RateLimiter_LeakyBucket(int bucketCapacity, double leakRate) {
        this.bucketCapacity = bucketCapacity;
        this.leakRate = leakRate;
        this.clientBuckets = new ConcurrentHashMap<>();
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimiter_LeakyBucket rateLimiter = new RateLimiter_LeakyBucket(10, 2); // 10 burst, 2 requests/sec
        String[] clients = {"user1", "user2"};

        for (int i = 0; i < 12; i++) {
            for (String clientId : clients) {
                System.out.println("Client a " + clientId + " - Request " + (i + 1) + ": " + rateLimiter.allowRequest(clientId));
            }
            Thread.sleep(500);
        }
    }

    public boolean allowRequest(String clientId) {
        clientBuckets.putIfAbsent(clientId, new LeakyBucket(bucketCapacity, leakRate));
        return clientBuckets.get(clientId).allowRequest();
    }
}