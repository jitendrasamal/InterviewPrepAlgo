package personal.practice.atlassian.ratelimiter;

import java.util.concurrent.ConcurrentLinkedDeque;

public class SlidingWindow {
    private final long windowSizeInMillis;
    private final long maxRequest;
    private final ConcurrentLinkedDeque<Long> timeStamps = new ConcurrentLinkedDeque<>();

    public SlidingWindow(long windowSizeInMillis, long maxRequest) {
        this.windowSizeInMillis = windowSizeInMillis;
        this.maxRequest = maxRequest;
    }
    public synchronized boolean allowRequest(){
        long now = System.currentTimeMillis();
        while (!timeStamps.isEmpty() && timeStamps.peek() < now - windowSizeInMillis){
            timeStamps.poll();
        }
        if(timeStamps.size() < maxRequest){
            timeStamps.add(now);
            return true;
        }
        return false;
    }
}
