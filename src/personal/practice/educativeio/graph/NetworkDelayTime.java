package personal.practice.educativeio.graph;

import java.util.*;

class NetworkDelayTime {
    public static void main(String[] args) {
        int[][][] times = {
                {{2, 1, 1}, {3, 2, 1}, {3, 4, 2}},
                {{2, 1, 1}, {1, 3, 1}, {3, 4, 2}, {5, 4, 2}},
                {{1, 2, 1}, {2, 3, 1}, {3, 4, 1}},
                {{1, 2, 1}, {2, 3, 1}, {3, 5, 2}},
                {{1, 2, 2}}
        };

        int[] n = {4, 5, 4, 5, 2};
        int[] k = {3, 1, 1, 1, 2};

        for (int i = 0; i < times.length; i++) {
            System.out.println((i + 1) + ".\t times = " + Arrays.deepToString(times[i]));
            System.out.println("\t number of nodes 'n' = " + n[i]);
            System.out.println("\t starting node 'k' = " + k[i] + "\n");
            System.out.println("\t Minimum amount of time required = " + networkDelayTime(times[i], n[i], k[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    public static int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<Edge>> adjacency = new HashMap<>();
        for (int[] time : times) {
            int source = time[0];
            int destination = time[1];
            int travelTime = time[2];
            List<Edge> edges = adjacency.computeIfAbsent(source, key -> new ArrayList<>());
            edges.add(new Edge(destination, travelTime));
        }

        PriorityQueue<Edge> minHeap = new PriorityQueue<>((x, y) -> {
            return x.travelTime - y.travelTime;
        });
        minHeap.offer(new Edge(k, 0));
        Set<Integer> visited = new HashSet<>();
        int delay = 0;
        while (!minHeap.isEmpty()) {
            Edge edge = minHeap.poll();
            if (visited.contains(edge.destinationVertex))
                continue;

            visited.add(edge.destinationVertex);
            delay = Math.max(delay, edge.travelTime);

            List<Edge> adjacencyEdges = adjacency.getOrDefault(edge.destinationVertex, List.of());
            for (Edge adjacencyEdge : adjacencyEdges) {
                minHeap.offer(new Edge(adjacencyEdge.destinationVertex, delay + adjacencyEdge.travelTime));
            }
        }

        if (visited.size() == n)
            return delay;

        return -1;
    }

    private static class Edge {
        @Deprecated
        int sourceVertex;
        int destinationVertex;
        int travelTime;

        public Edge(int destinationVertex, int travelTime) {
            this.destinationVertex = destinationVertex;
            this.travelTime = travelTime;
        }
    }
}