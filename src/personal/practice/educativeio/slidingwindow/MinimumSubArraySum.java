package personal.practice.educativeio.slidingwindow;

//https://www.educative.io/courses/grokking-coding-interview/minimum-size-subarray-sum
//https://leetcode.com/problems/minimum-size-subarray-sum/description/
public class MinimumSubArraySum {
    public static void main(String[] args) {
        System.out.println(minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }

    public static int minSubArrayLen(int target, int[] nums) {
        int start = 0;
        int sum = 0;
        int result = -1;
        for (int end = 0; end < nums.length; ++end) {
            sum += nums[end];
            while (sum >= target) {
                if (result == -1 || result > (end - start + 1)) {
                    result = end - start + 1;
                }
                sum -= nums[start];
                ++start;
            }
        }
        return result;
    }
}
