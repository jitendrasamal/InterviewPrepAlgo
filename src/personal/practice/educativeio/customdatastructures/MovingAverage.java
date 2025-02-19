package personal.practice.educativeio.customdatastructures;

//https://leetcode.com/problems/moving-average-from-data-stream/
//https://www.educative.io/courses/grokking-coding-interview/moving-average-from-data-stream

import java.util.LinkedList;
import java.util.Queue;

public class MovingAverage {

    Queue<Integer> queue = new LinkedList<>();
    int size;
    double sum = 0;

    public MovingAverage(int size) {
        this.size = size;
    }

    public double next(int val) {
        queue.offer(val);
        sum += val;
        if (queue.size() > size) {
            sum -= queue.poll();
        }
        return sum / Math.min(size, queue.size());
    }
}
