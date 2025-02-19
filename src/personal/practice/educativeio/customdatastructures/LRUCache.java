package personal.practice.educativeio.customdatastructures;

import java.util.LinkedHashMap;
import java.util.Map;

//https://leetcode.com/problems/lru-cache/
//https://www.educative.io/courses/grokking-coding-interview/lru-cache
class LRUCache {
    private final LinkedHashMap<Integer, Integer> linkedHashMap;

    public LRUCache(int capacity) {
        linkedHashMap = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
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

    public void put(int key, int value) {
        linkedHashMap.put(key, value);
    }

    public int get(int key) {
        return linkedHashMap.getOrDefault(key, -1);
    }
}
