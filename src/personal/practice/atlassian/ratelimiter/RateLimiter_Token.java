package personal.practice.atlassian.ratelimiter;

import java.util.concurrent.ConcurrentHashMap;

public class RateLimiter_Token {
    private final long maxToken;
    private  final long refillRatePerSecond;
    private final ConcurrentHashMap<String, TokenBucket> buckets = new ConcurrentHashMap<>();

    public RateLimiter_Token(long maxToken, long refillRatePerSecond){
        this.maxToken = maxToken;
        this.refillRatePerSecond = refillRatePerSecond;
    }
    public boolean allowRequest(String clientId){
        TokenBucket tokenBucket = buckets.computeIfAbsent(clientId, k-> new TokenBucket(maxToken, refillRatePerSecond));
        return tokenBucket.tryConsume();
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimiter_Token rateLimiter = new RateLimiter_Token(5, 2); // 5 tokens max, 2 tokens per second
        String clientId = "user1";

        for (int i = 0; i < 100; i++) {
            System.out.println("Request " + (i + 1) + ": " + rateLimiter.allowRequest(clientId));
            Thread.sleep(100);
        }
    }
}
