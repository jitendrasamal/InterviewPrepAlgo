package personal.practice.educativeio.greedytechniques;

//https://www.educative.io/courses/grokking-coding-interview/gas-station
//https://leetcode.com/problems/gas-station/description/
//https://www.youtube.com/watch?v=PX7wYSqDIMQ&ab_channel=HelloWorld
public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalDiff = 0; // sum(gas) - sum(cost)
        int currentGas = 0;
        int index = 0;
        for (int i = 0; i < gas.length; i++) {
            int diff = gas[i] - cost[i];
            totalDiff += diff;
            currentGas += diff;
            if (currentGas < 0) {
                // it means 0 to ith index we can't start
                // so potential answer will be after i
                // so reset the index and gas
                currentGas = 0;
                index = i + 1;
            }
        }

        // check if  sum(gas) - sum(cost) is negative then return -1
        return totalDiff < 0 ? -1 : index;
    }
}
