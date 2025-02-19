package personal.practice.educativeio.customdatastructures;

import java.util.HashMap;

public class LRUCacheCustomNode {

    ListNode head;
    ListNode tail;
    HashMap<Integer, ListNode> keyToNode;
    int capacity;

    public LRUCacheCustomNode(int capacity) {
        keyToNode = new HashMap<>(capacity + 1);
        head = new ListNode(-1, -1);
        tail = new ListNode(-1, -1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }

    public static void main_1(String[] args) {
        LRUCacheCustomNode lRUCache = new LRUCacheCustomNode(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // return -1 (not found)
        System.out.println(lRUCache.get(3));    // return 3
        System.out.println(lRUCache.get(4));    // return 4
    }
    public static void main(String[] args) {
        LRUCacheCustomNode lRUCache = new LRUCacheCustomNode(1);
        lRUCache.put(2, 1); // cache is {2=1}
        lRUCache.put(2, 2); // cache is {2=1}
        System.out.println(lRUCache.get(2));    // return 2
    }

    public void put(int key, int value) {
        if (keyToNode.containsKey(key)) {
            ListNode node = keyToNode.get(key);
            node.value = value;
            removeNode(node);
            addNodeToHead(node);
        } else {
            ListNode node = new ListNode(key, value);
            addNodeToHead(node);
            keyToNode.put(key, node);
            if (keyToNode.size() > capacity && tail.prev != head) {
                removeLastUsed();
            }
        }
    }

    public int get(int key) {
        if (!keyToNode.containsKey(key))
            return -1;
        ListNode node = keyToNode.get(key);
        removeNode(node);
        addNodeToHead(node);
        return node.value;
    }

    private void removeNode(ListNode node) {
        ListNode prev = node.prev;
        ListNode next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    private void addNodeToHead(ListNode node) {
        ListNode next = head.next;
        next.prev = node;
        node.next = next;
        node.prev = head;
        head.next = node;
    }

    private void removeLastUsed() {
        ListNode node = tail.prev;
        removeNode(node);
        keyToNode.remove(node.key);
    }

    private static class ListNode {
        int value;
        int key;
        ListNode next;
        ListNode prev;

        public ListNode(int key, int value) {
            this.value = value;
            this.key = key;
        }
    }

}
