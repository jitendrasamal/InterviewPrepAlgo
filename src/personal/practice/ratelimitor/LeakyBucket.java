package personal.practice.ratelimitor;

class LeakyBucket {
    private final int bucketCapacity; // Maximum burst size
    private final double leakRate; // Requests per second
    private int tokens;
    private long lastRequestTime;

    public LeakyBucket(int bucketCapacity, double leakRate) {
        this.bucketCapacity = bucketCapacity;
        this.leakRate = leakRate;
        this.tokens = bucketCapacity;
        this.lastRequestTime = System.currentTimeMillis();
    }

    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();
        double elapsedSeconds = (now - lastRequestTime) / 1000.0;
        int newTokens = (int) (elapsedSeconds * leakRate); // Tokens leaked over time

        tokens = Math.min(bucketCapacity, tokens + newTokens);
        lastRequestTime = now;

        if (tokens > 0) {
            tokens--;
            return true;
        }
        return false;
    }
}