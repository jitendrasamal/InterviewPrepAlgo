package personal.practice.educativeio.slidingwindow;

import java.util.List;

//https://www.educative.io/courses/grokking-coding-interview/diet-plan-performance
//https://leetcode.com/problems/diet-plan-performance/description/
public class DietPlanPerformance {

    public static int dietPlanPerformance(List<Integer> calories, int k, int lower, int upper) {
        if (calories.size() < k || k <= 0 || lower > upper)
            return -1;

        int index = 0;
        int sum = 0;
        int point = 0;
        for (; index < k; ++index) {
            sum += calories.get(index);
        }
        point += calculatePoint(sum, lower, upper);
        for (; index < calories.size(); ++index) {
            sum -= calories.get(index - k);
            sum += calories.get(index);
            point += calculatePoint(sum, lower, upper);
        }

        return point;
    }

    public static int calculatePoint(int sum, int lower, int upper) {
        if (sum < lower)
            return -1;
        if (sum > upper)
            return 1;
        return 0;
    }
}
