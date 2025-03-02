package personal.practice.educativeio.greedytechniques;

import java.util.Arrays;

//https://www.educative.io/courses/grokking-coding-interview/boats-to-save-people
//https://leetcode.com/problems/boats-to-save-people/description/
public class BoatsToSavePeople {
    public static void main(String[] args) {
        int[][] people = {{1, 2}, {3, 2, 2, 1}, {3, 5, 3, 4}, {5, 5, 5, 5}, {1, 2, 3, 4}, {1, 2, 3}, {3, 4, 5}};
        int[] limit = {3, 3, 5, 5, 5, 3, 5};

        for (int i = 0; i < people.length; i++) {
            System.out.println((i + 1) + "\tWeights = " + Arrays.toString(people[i]));
            System.out.println("\tWeight Limit = " + limit[i]);
            System.out.println("\tThe minimum number of boats required to save people are "
                    + rescueBoats(people[i], limit[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    public static int rescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int start = 0;
        int end = people.length - 1;
        int numberOfBoat = 0;
        while (start <= end) {
            if (people[start] + people[end] <= limit) {
                ++start;
            }
            --end;
            ++numberOfBoat;
        }
        return numberOfBoat;
    }
}
