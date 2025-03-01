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
        int maxFruit = 0;
        int maxTypeOfFruit = 2;
        if (fruits.length == 0)
            return maxFruit;
        HashMap<Integer, Integer> fruitTypeToCount = new HashMap<>();
        int start = 0;
        for (int end = 0; end < fruits.length; ++end) {
            int type = fruits[end];
            fruitTypeToCount.put(type, fruitTypeToCount.getOrDefault(type, 0) + 1);
            while (fruitTypeToCount.size() > maxTypeOfFruit) {
                type = fruits[start];
                int count = fruitTypeToCount.get(type) - 1;
                if (count > 0) {
                    fruitTypeToCount.put(type, count);
                } else {
                    fruitTypeToCount.remove(type);
                }
                ++start;
            }
            maxFruit = Math.max(maxFruit, end - start + 1);
        }
        return maxFruit;
    }
}
