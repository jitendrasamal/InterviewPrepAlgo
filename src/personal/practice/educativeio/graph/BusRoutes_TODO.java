package personal.practice.educativeio.graph;

import java.util.*;
import java.util.stream.Collectors;

//https://leetcode.com/problems/bus-routes/description/
//https://www.educative.io/courses/grokking-coding-interview/bus-routes
public class BusRoutes_TODO {
    public static int minimumBuses(int[][] busRoutes, int src, int dest) {
        if (src == dest)
            return 0;
        HashMap<Integer, Set<Integer>> busRoutesMap = new HashMap<>();
        for (int[] busRoute : busRoutes) {
           Set<Integer> stops = Arrays.stream(busRoute).boxed().collect(Collectors.toSet());
            for (int stop : stops) {
                Set<Integer> stopSet = busRoutesMap.computeIfAbsent(stop, x -> new HashSet<>());
                stopSet.addAll(stops);
            }
        }

        Deque<DestinationCost> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.offer(new DestinationCost(src, 0));
        while (!queue.isEmpty()) {
            DestinationCost ds = queue.poll();
            if (ds.destination == dest) return ds.cost;
            if(visited.contains(ds.destination))
                continue;
            visited.add(ds.destination);
            for (int station : busRoutesMap.getOrDefault(ds.destination, new HashSet<>())) {
                if (!visited.contains(station) && station != ds.destination) {
                    queue.offer(new DestinationCost(station, ds.cost + 1));
                }
            }
        }

        // Replace this placeholder return statement with your code
        return -1;
    }

    private static class DestinationCost {
        int destination;
        int cost;

        public DestinationCost(int destination, int cost) {
            this.destination = destination;
            this.cost = cost;
        }
    }
}
