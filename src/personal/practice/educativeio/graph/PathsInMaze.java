package personal.practice.educativeio.graph;

import java.util.*;

public class PathsInMaze {

    public static int numberOfPaths(int n, int[][] corridors) {

        Map<Integer, Set<Integer>> neighbours = new HashMap<>();
        int numberOfPathsLength3 = 0;
        for (int[] corridor : corridors) {
            int room1 = corridor[0];
            int room2 = corridor[1];
            Set<Integer> neighbour1 = neighbours.computeIfAbsent(room1, x -> new HashSet<>());
            Set<Integer> neighbour2 = neighbours.computeIfAbsent(room2, x -> new HashSet<>());
            neighbour1.add(room2);
            neighbour2.add(room1);

            int common = findCommon(neighbour1, neighbour2);
            numberOfPathsLength3 += common;
        }

        return numberOfPathsLength3;
    }

    private static int findCommon(Set<Integer> neighbour1, Set<Integer> neighbour2) {
        int count = 0;

        for (Integer integer : neighbour1) {
            if (neighbour2.contains(integer)) {
                ++count;
            }
        }

        return count;
    }

}
