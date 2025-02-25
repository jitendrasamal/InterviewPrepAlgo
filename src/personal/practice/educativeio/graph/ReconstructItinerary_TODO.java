package personal.practice.educativeio.graph;

import java.util.*;
//https://leetcode.com/problems/reconstruct-itinerary/description/
//https://www.educative.io/courses/grokking-coding-interview/reconstruct-itinerary
public class ReconstructItinerary_TODO {

    public static List<String> findItinerary(List<List<String>> tickets) {
        List<String> result = new ArrayList<>();
        Map<String, List<String>> flightMap = new HashMap<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            List<String> toAirports = flightMap.computeIfAbsent(from, x -> new ArrayList<>());
            toAirports.add(to);
        }
        for (List<String> toAirports : flightMap.values()) {
            Collections.sort(toAirports);
        }

        dfs("JFK", flightMap, result);

        return result;
    }

    private static void dfs(String airport, Map<String, List<String>> flightMap, List<String> result) {

    }
}
