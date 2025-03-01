package personal.practice.educativeio.slidingwindow;

import java.util.HashMap;

//https://www.educative.io/courses/grokking-coding-interview/fruit-into-baskets
//https://leetcode.com/problems/fruit-into-baskets/
public class FruitIntoBaskets {
    /*
    [2,2,2,3,4,2,2]
     */
    public static void main(String[] args) {
        FruitIntoBaskets fruitIntoBaskets = new FruitIntoBaskets();
        System.out.println(fruitIntoBaskets.totalFruit(new int[]{0, 1, 2, 2}));
    }

    public int totalFruit(int[] fruits) {
        int collected = 0;
        int basketSize = 2;
        if (fruits.length == 0)
            return collected;
        HashMap<Integer, Integer> baskets = new HashMap<>();
        int start = 0;
        for (int end = 0; end < fruits.length; ++end) {
            int type = fruits[end];
            baskets.put(type, baskets.getOrDefault(type, 0) + 1);
            while (baskets.size() > basketSize) {
                type = fruits[start];
                int count = baskets.get(type) - 1;
                if (count > 0) {
                    baskets.put(type, count);
                } else {
                    baskets.remove(type);
                }
                ++start;
            }
            collected = Math.max(collected, end - start + 1);
        }
        return collected;
    }
}
