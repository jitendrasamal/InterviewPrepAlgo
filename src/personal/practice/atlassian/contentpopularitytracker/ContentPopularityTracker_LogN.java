package personal.practice.atlassian.contentpopularitytracker;

import java.util.*;

public class ContentPopularityTracker_LogN {

    SortedSet<Node> sortedByPopularity = new TreeSet<>((n1, n2) -> {
        if (n1.popularityCount == n2.popularityCount)
            return n1.contentId - n2.contentId;
        return n1.popularityCount - n2.popularityCount;
    });
    Map<Integer, Integer> contentIdToCountHashMap = new HashMap<>();


    public static void main(String[] args) {
        ContentPopularityTracker_LogN tracker = new ContentPopularityTracker_LogN();

        // Basic Test Cases
        tracker.increasePopularity(1);
        tracker.increasePopularity(2);
        tracker.increasePopularity(2);
        tracker.decreasePopularity(1);
        System.out.println("Most Popular Content ID: " + tracker.mostPopular()); // Expected Output: 2

        // Edge Case: Multiple increases and decreases
        tracker.increasePopularity(3);
        tracker.increasePopularity(3);
        tracker.increasePopularity(3);
        //tracker.print();
        tracker.decreasePopularity(2);
        //tracker.print();
        System.out.println("Most Popular Content ID: " + tracker.mostPopular()); // Expected Output: 3

        // Edge Case: Content getting removed
        tracker.decreasePopularity(3);
        tracker.decreasePopularity(3);
        tracker.decreasePopularity(3);
        //tracker.print();
        System.out.println("Most Popular Content ID: " + tracker.mostPopular()); // Expected Output: 2

        // Edge Case: No content
        tracker.decreasePopularity(2);
        System.out.println("Most Popular Content ID: " + tracker.mostPopular()); // Expected Output: -1
    }

    public void increasePopularity(int contentId) {
        int count = contentIdToCountHashMap.getOrDefault(contentId, 0);
        if (count > 0) {
            sortedByPopularity.remove(new Node(contentId, count));
        }
        contentIdToCountHashMap.put(contentId, count + 1);
        sortedByPopularity.add(new Node(contentId, count + 1));
    }

    public void decreasePopularity(int contentId) {
        if (!contentIdToCountHashMap.containsKey(contentId))
            return;
        int count = contentIdToCountHashMap.get(contentId);
        contentIdToCountHashMap.remove(contentId);
        sortedByPopularity.remove(new Node(contentId, count));
        if (count > 1) {
            contentIdToCountHashMap.put(contentId, count - 1);
            sortedByPopularity.add(new Node(contentId, count - 1));
        }

    }

    public int mostPopular() {
        return sortedByPopularity.isEmpty() ? -1 : sortedByPopularity.last().contentId;
    }

    private static class Node {
        int popularityCount;
        int contentId;

        public Node(int contentId, int popularityCount) {
            this.contentId = contentId;
            this.popularityCount = popularityCount;
        }
    }
}
