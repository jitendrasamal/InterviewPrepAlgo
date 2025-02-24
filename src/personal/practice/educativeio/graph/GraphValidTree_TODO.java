package personal.practice.educativeio.graph;

import java.util.*;

//https://www.educative.io/courses/grokking-coding-interview/graph-valid-tree
//https://leetcode.com/problems/graph-valid-tree/description/
public class GraphValidTree_TODO {
    public static boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) return false;
        Map<Integer, List<Integer>> adjLists = new HashMap<>();
        for (int[] edge : edges) {
            adjLists.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(edge[1]);
            adjLists.computeIfAbsent(edge[1], x -> new ArrayList<>()).add(edge[0]);
        }
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        stack.add(0);
        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            visited.add(vertex);
            for (int edge : adjLists.getOrDefault(vertex, List.of())) {
                if (!(visited.contains(edge))) {
                    stack.push(edge);
                }
            }
        }
        return visited.size() == n;
    }
}
