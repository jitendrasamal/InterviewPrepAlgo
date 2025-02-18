package personal.practice.ratelimiter;

import java.util.concurrent.atomic.AtomicLong;

public class TokenBucket {
    private final long maxTokens;
    private final long refillRatesPerSecond;
    private final AtomicLong availableTokens;
    private volatile long lastRefillTimestamps;

    public TokenBucket(long maxTokens, long refillRatesPerSecond) {
        this.maxTokens = maxTokens;
        this.refillRatesPerSecond = refillRatesPerSecond;
        this.availableTokens = new AtomicLong();
        //optional below
        lastRefillTimestamps = System.nanoTime();
        availableTokens.set(maxTokens);
    }

    public  synchronized boolean tryConsume(){
        refillTokens();
        if(availableTokens.get() > 0){
            availableTokens.decrementAndGet();
            return true;
        }
        return false;
    }

    private void refillTokens() {
        long now = System.nanoTime();
        long lapsedTime =  now - lastRefillTimestamps;
        long tokenToAdd = (lapsedTime * refillRatesPerSecond) / 1_000_000_000;
        if(tokenToAdd > 0){
            availableTokens.set(Math.min(maxTokens, availableTokens.get() +  tokenToAdd));
            lastRefillTimestamps = now;
        }
    }

}
