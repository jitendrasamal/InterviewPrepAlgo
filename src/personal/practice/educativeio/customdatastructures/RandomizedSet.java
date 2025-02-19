package personal.practice.educativeio.customdatastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

//https://leetcode.com/problems/insert-delete-getrandom-o1/
//https://www.educative.io/courses/grokking-coding-interview/insert-delete-getrandom-o1
public class RandomizedSet {

    HashMap<Integer, Integer> valToArrayIndexMap;
    List<Integer> values;
    Random random;

    public RandomizedSet() {
        valToArrayIndexMap = new HashMap<>();
        values = new ArrayList<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (valToArrayIndexMap.containsKey(val)) return false;
        values.add(val);
        valToArrayIndexMap.put(val, values.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (!valToArrayIndexMap.containsKey(val)) return false;
        int index = valToArrayIndexMap.get(val);
        swap(index, values.size() - 1);
        values.remove(values.size() - 1);
        valToArrayIndexMap.remove(val);
        return true;
    }

    private void swap(int index1, int index2) {
        if (index1 == index2) return;

        int val1 = values.get(index1);
        int val2 = values.get(index2);

        //swap index mapping
        valToArrayIndexMap.put(val2, index1);
        valToArrayIndexMap.put(val1, index2);

        //swap value
        values.set(index1, val2);
        values.set(index2, val1);

    }

    public int getRandom() {
        if (values.isEmpty()) return -1;
        return values.get(random.nextInt(values.size()));
    }
}
