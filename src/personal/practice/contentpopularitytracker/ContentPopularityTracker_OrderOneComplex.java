package personal.practice.contentpopularitytracker;

import java.util.*;

public class ContentPopularityTracker_OrderOneComplex {

    Map<Integer, Node> contentIdToNodeMapping;
    Node dummyHead;
    Node dummyTail;

    public ContentPopularityTracker_OrderOneComplex() {
        contentIdToNodeMapping = new HashMap<>();
        dummyHead = new Node(-1);
        dummyTail = new Node(-1);
        dummyTail.prev = dummyHead;
        dummyHead.next = dummyTail;
    }

    public static void main(String[] args) {
        ContentPopularityTracker_OrderOneComplex tracker = new ContentPopularityTracker_OrderOneComplex();

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
        if (!contentIdToNodeMapping.containsKey(contentId)) {
            // add in the beginning
            insertOrUpdateNextNode(dummyHead, 1, contentId);
        } else {
            Node node = contentIdToNodeMapping.get(contentId);
            int newFrequency = node.frequency + 1;
            node.contentIds.remove(contentId);
            insertOrUpdateNextNode(node, newFrequency, contentId);
            if (node.contentIds.isEmpty()) {
                removeNode(node);
            }
        }
    }

    public void decreasePopularity(int contentId) {
        if (!contentIdToNodeMapping.containsKey(contentId))
            return;
        Node node = contentIdToNodeMapping.get(contentId);
        int newFrequency = node.frequency - 1;
        node.contentIds.remove(contentId);
        if (newFrequency > 0) {
            insertOrUpdatePrevNode(node, newFrequency, contentId);
        }
        if (node.contentIds.isEmpty()) {
            removeNode(node);
        }
    }

    public int mostPopular() {
        if (dummyTail.prev == dummyHead || dummyTail.prev.contentIds.isEmpty()) {
            return -1;
        }
        return dummyTail.prev.contentIds.iterator().next();
    }

    private void insertOrUpdateNextNode(Node prev, int frequency, int contentId) {
        if (prev.next == dummyTail || prev.next.frequency != frequency) {
            Node node = addNode(prev, prev.next, frequency);
            node.contentIds.add(contentId);
            contentIdToNodeMapping.put(contentId, node);
        } else {
            prev.next.contentIds.add(contentId);
            contentIdToNodeMapping.put(contentId, prev.next);
        }
    }

    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    private void insertOrUpdatePrevNode(Node next, int frequency, int contentId) {
        if (next.prev == dummyHead || next.prev.frequency != frequency) {
            Node node = addNode(next.prev, next, frequency);
            contentIdToNodeMapping.put(contentId, node);
            node.contentIds.add(contentId);
        } else {
            next.prev.contentIds.add(contentId);
            contentIdToNodeMapping.put(contentId, next.prev);
        }
    }

    private Node addNode(Node prev, Node next, int frequency) {
        Node node = new Node(frequency);
        prev.next = node;
        next.prev = node;
        node.prev = prev;
        node.next = next;
        return node;
    }

    public static void main_1(String[] args) {
        ContentPopularityTracker_OrderOneComplex tracker = new ContentPopularityTracker_OrderOneComplex();

        // Test increasing popularity
        tracker.increasePopularity(1);
        tracker.increasePopularity(2);
        tracker.increasePopularity(3);
        tracker.increasePopularity(1);
        tracker.increasePopularity(2);
        tracker.increasePopularity(3);
        tracker.increasePopularity(1);
        tracker.increasePopularity(2);
        tracker.increasePopularity(3);
        tracker.increasePopularity(1);
        tracker.increasePopularity(2);
        tracker.increasePopularity(3);
        System.out.println(tracker.mostPopular()); // 1, 2, or 3

        // Test decreasing popularity
        tracker.decreasePopularity(1);
        tracker.decreasePopularity(2);
        tracker.decreasePopularity(3);
        System.out.println(tracker.mostPopular()); // 1, 2, or 3

        // Test edge case: decreasing popularity to zero
        tracker.decreasePopularity(1);
        tracker.decreasePopularity(2);
        tracker.decreasePopularity(3);
        tracker.decreasePopularity(1);
        tracker.decreasePopularity(2);
        tracker.decreasePopularity(3);
        tracker.decreasePopularity(1);
        tracker.decreasePopularity(2);
        tracker.decreasePopularity(3);
        System.out.println(tracker.mostPopular()); // -1

        // Test increasing and decreasing popularity
        tracker.increasePopularity(4);
        tracker.increasePopularity(5);
        tracker.increasePopularity(4);
        tracker.decreasePopularity(4);
        System.out.println(tracker.mostPopular()); // 5

        // Test increasing popularity of new content
        tracker.increasePopularity(6);
        tracker.increasePopularity(6);
        tracker.increasePopularity(6);
        System.out.println(tracker.mostPopular()); // 6
    }

    public void print() {
        Node node = dummyHead.next;
        while (node != dummyTail) {
            System.out.print(" (freq:" + node.frequency + ", contentIds:" + node.contentIds.toString() + ")");
            node = node.next;
        }
        System.out.println();
    }

    private static class Node {
        int frequency;
        Set<Integer> contentIds = new HashSet<>();
        Node prev = null;
        Node next = null;

        Node(int frequency) {
            this.frequency = frequency;
        }
    }
}
