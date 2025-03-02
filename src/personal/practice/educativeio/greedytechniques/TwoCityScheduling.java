package personal.practice.educativeio.greedytechniques;

import java.util.Arrays;

//https://www.educative.io/courses/grokking-coding-interview/two-city-scheduling
//https://leetcode.com/problems/two-city-scheduling/description/
public class TwoCityScheduling {
    /*
        1     2      3      4
        10:20 400:50 30:100 30:20
        -10    350   -70     10

       30  10 20 50
        3  1 4 2
        1  1 2  2



     */

    public int twoCitySchedCost(int[][] costs) {
        int cost = 0;
        Arrays.sort(costs, (a, b) -> {
            int diff1 = a[0] - a[1];
            int diff2 = b[0] - b[1];
            return diff1 - diff2;
        });
        int mid = costs.length / 2;
        int lastIndex = costs.length - 1;
        for (int i = 0; i < mid; i++) {
            cost += costs[i][0];
            cost += costs[lastIndex - i][1];
        }

        return cost;
    }
}
