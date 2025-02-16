package personal.practice.ratelimitor;

/*
import redis.clients.jedis.Jedis;
import java.util.concurrent.TimeUnit;
*/
public class RateLimiter_Redis {
    /*
    private final long windowSizeInMillis;
    private final int maxRequests;
    private final Jedis jedis;

    public RateLimiter_Redis(long windowSizeInMillis, int maxRequests, String redisHost, int redisPort) {
        this.windowSizeInMillis = windowSizeInMillis;
        this.maxRequests = maxRequests;
        this.jedis = new Jedis(redisHost, redisPort);
    }

    public boolean allowRequest(String clientId) {
        long now = System.currentTimeMillis();
        String key = "rate_limiter:" + clientId;

        jedis.zremrangeByScore(key, 0, now - windowSizeInMillis);
        long currentCount = jedis.zcard(key);

        if (currentCount < maxRequests) {
            jedis.zadd(key, now, String.valueOf(now));
            jedis.expire(key, (int) TimeUnit.MILLISECONDS.toSeconds(windowSizeInMillis));
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimiter_Redis rateLimiterRedis = new RateLimiter_Redis(1000, 5, "localhost", 6379); // 1-second window, max 5 requests
        String clientId = "user1";

        for (int i = 0; i < 10; i++) {
            System.out.println("Request " + (i + 1) + ": " + rateLimiterRedis.allowRequest(clientId));
            Thread.sleep(200);
        }
    }
 
     */
}
