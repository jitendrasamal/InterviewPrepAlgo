package personal.practice.educativeio.customdatastructures;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/two-sum-iii-data-structure-design/submissions/1548795252/
// https://www.educative.io/courses/grokking-coding-interview/two-sum-iii-data-structure-design

public class TwoSum {
    HashMap<Integer, Integer> numberFreq;

    public TwoSum() {
        numberFreq = new HashMap<>();
    }

    public void add(int number) {
        numberFreq.put(number, numberFreq.getOrDefault(number, 0) + 1);
    }

    public boolean find(int value) {
        for (Map.Entry<Integer, Integer> entry : numberFreq.entrySet()) {
            int complementNumber = value - entry.getKey();
            if (complementNumber != entry.getKey()) {
                if (numberFreq.containsKey(complementNumber)) {
                    return true;
                }
            } else if (entry.getValue() > 1) {
                return true;
            }
        }
        return false;
    }
}
