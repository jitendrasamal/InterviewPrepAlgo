package personal.practice.educativeio.customdatastructures;


// https://leetcode.com/problems/range-sum-query-immutable/
// https://www.educative.io/courses/grokking-coding-interview/range-sum-query-immutable

class NumArray {
    //   -2, 0, 3, -5, 2, -1
    // 0 -2 -2  1  -4 -2  -3
    long[] prefixSum;

    public NumArray(int[] nums) {
        prefixSum = new long[nums.length + 1];
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            prefixSum[i + 1] = sum;
        }
    }

    public int sumRange(int left, int right) {
        return (int) (prefixSum[right + 1] - prefixSum[left]);
    }
}
