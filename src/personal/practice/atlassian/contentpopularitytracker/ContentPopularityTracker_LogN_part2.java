package personal.practice.atlassian.contentpopularitytracker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ContentPopularityTracker_LogN_part2 {

    private final Map<Integer, Integer> contentIdToCount;
    private final TreeMap<Integer, Set<Integer>> countToContentId;
    public ContentPopularityTracker_LogN_part2() {
        this.contentIdToCount = new HashMap<>();
        this.countToContentId = new TreeMap<>();
    }

    public static void main(String[] args) {
        ContentPopularityTracker_LogN_part2 tracker = new ContentPopularityTracker_LogN_part2();

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

    public void increasePopularity(final Integer contentId) {
        impression(contentId, true);
    }

    public void decreasePopularity(final Integer contentId) {
        impression(contentId, false);
    }

    public int mostPopular() {
        if (countToContentId.isEmpty()) {
            return -1;
        }
        return countToContentId.lastEntry().getValue().stream().findFirst().get();
    }

    private void impression(final Integer contentId, boolean increase) {
        int val = increase ? 1 : -1;
        if (!contentIdToCount.containsKey(contentId)) {
            contentIdToCount.put(contentId, val);
            countToContentId.computeIfAbsent(val, k -> new HashSet<>()).add(contentId);
        } else {
            int count = contentIdToCount.get(contentId);
            contentIdToCount.merge(contentId, val, Integer::sum);
            countToContentId.get(count).remove(contentId);
            if (countToContentId.get(count).isEmpty()) {
                countToContentId.remove(count);
            }
            countToContentId.computeIfAbsent(count + val, k -> new HashSet<>()).add(contentId);
        }
    }

}
