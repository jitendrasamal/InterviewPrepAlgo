package personal.practice.educativeio.greedytechniques;

//https://www.educative.io/courses/grokking-coding-interview/jump-game
//https://leetcode.com/problems/jump-game/description/
public class JumpGame {
    public boolean canJump(int[] nums) {
        int targetIndex = nums.length - 1;
        for (int i = nums.length - 1; i >= 0 && targetIndex > 0; i--) {
            if (i + nums[i] >= targetIndex) {
                targetIndex = i;
            }
        }
        return targetIndex == 0;
    }
}
