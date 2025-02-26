package personal.practice.educativeio.fastslowpointers;

import java.util.HashSet;
import java.util.Set;

public class CircularArrayLoop {
    //https://leetcode.com/problems/circular-array-loop/description/
    //https://www.educative.io/courses/grokking-coding-interview/circular-array-loop
    /*
    0  1 2 3 4
    2,-1,1,2,2

    0->2->3->0
     */

    public static void main(String[] args) {
        CircularArrayLoop loop = new CircularArrayLoop();
        System.out.println(loop.circularArrayLoop(new int[]{-2, 1, -1, -2, -2}));
        // System.out.println(loop.circularArrayLoop(new int[]{}));
        // System.out.println(loop.circularArrayLoop(new int[]{}));
        // System.out.println(loop.circularArrayLoop(new int[]{}));
        // System.out.println(loop.circularArrayLoop(new int[]{}));

    }


    public boolean circularArrayLoop(int[] nums) {
        Set<Integer> visitedIndices = new HashSet<>();
        int arraySize = nums.length;
        for (int index = 0; index < arraySize; ++index) {
            if (visitedIndices.contains(index))
                continue;
            int sp = index;
            int fp = index;
            while (true) {
                visitedIndices.add(sp);
                boolean isForward = nums[sp] >= 0;
                int newSP = getNextIndex(nums, sp);
                if (isNotCycle(nums, isForward, sp, newSP))
                    break;
                sp = newSP;
                int newFP = getNextIndex(nums, fp);
                if (isNotCycle(nums, isForward, fp, newFP))
                    break;
                fp = newFP;
                newFP = getNextIndex(nums, fp);
                if (isNotCycle(nums, isForward, fp, newFP))
                    break;
                fp = newFP;
                if (fp == sp)
                    return true;
            }
        }

        return false;
    }

    public static int getNextIndex(int nums[], int currentIndex) {
        int newIndex = (currentIndex + nums[currentIndex]) % nums.length;
        if (newIndex < 0) {
            newIndex += nums.length;
        }
        return newIndex;
    }

    public static boolean isNotCycle(int nums[], boolean isPrevForward, int prevIndex, int newIndex) {
        boolean isForward = nums[newIndex] >= 0;
        return isPrevForward != isForward || prevIndex == newIndex;
    }
}
