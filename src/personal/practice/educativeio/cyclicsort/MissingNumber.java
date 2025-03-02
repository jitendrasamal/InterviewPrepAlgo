package personal.practice.educativeio.cyclicsort;

//https://leetcode.com/problems/missing-number/description/
//https://www.educative.io/courses/grokking-coding-interview/missing-number
public class MissingNumber {
    public int missingNumber(int[] arr) {
        int index = 0;
        int n = arr.length;
        while (index < n) {
            int num = arr[index];
            if (num == index || num >= n) {
                ++index;
            } else {
                swap(arr, index, num);
            }
        }

        for (index = 0; index < n; ++index) {
            if (index != arr[index])
                return index;
        }

        return n;
    }

    public void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }
}
