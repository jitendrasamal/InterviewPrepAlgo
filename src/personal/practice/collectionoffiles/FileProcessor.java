package personal.practice.collectionoffiles;

import java.util.*;

/*
 * https://leetcode.com/discuss/interview-experience/1504593/Atlassian-or-SDE-2-or-P4-or-September-2021-or-Bangalore
 * -or-Offer
 */
public class FileProcessor {
    private final Map<String, Integer> fileCollectionSizes = new HashMap<>();
    private int totalSizeOfFileProcessed;

    public static void main(String[] args) {
        FileProcessor fileProcessor = new FileProcessor();
        fileProcessor.processFile("file1", 10, List.of("collection1", "collection2", "collection3"));
        fileProcessor.processFile("file2", 20, List.of("collection1", "collection2", "collection4"));
        fileProcessor.processFile("file3", 30, List.of("collection1", "collection3", "collection5"));
        fileProcessor.processFile("file4", 40, List.of("collection1", "collection4", "collection6"));
        fileProcessor.processFile("file5", 50, List.of("collection1", "collection5", "collection7"));
        fileProcessor.processFile("file6", 60, List.of("collection1", "collection6", "collection8"));
        fileProcessor.processFile("file7", 70, List.of("collection1", "collection7", "collection9"));
        fileProcessor.processFile("file8", 80, List.of("collection1", "collection8", "collection10"));
        fileProcessor.processFile("file9", 90, List.of("collection1", "collection9", "collection11"));
        fileProcessor.processFile("file10", 100, List.of("collection1", "collection10", "collection12"));
        System.out.println("Top 5 collections: " + fileProcessor.topKCollections(5));
        System.out.println("Total size of files processed: " + fileProcessor.getTotalSizeOfFileProcessed());
    }

    @SuppressWarnings("UnusedVariable")
    public void processFile(String fileName, int size, List<String> collections) {
        totalSizeOfFileProcessed += size;
        for (String collection : collections) {
            fileCollectionSizes.put(collection, size + fileCollectionSizes.getOrDefault(collection, 0));
        }
    }

    public List<String> topKCollections(int k) {
        PriorityQueue<String> topKCollections = new PriorityQueue<>(k,
                (a, b) -> {
                    int sizeA = fileCollectionSizes.get(a);
                    int sizeB = fileCollectionSizes.get(b);
                    if (sizeA == sizeB) {
                        return a.compareTo(b);
                    }
                    return Integer.compare(sizeA, sizeB);
                });
        for (String collection : fileCollectionSizes.keySet()) {
            topKCollections.add(collection);
            if(topKCollections.size() > k) {
                topKCollections.poll();
            }
        }
        List<String> result = new ArrayList<>();
        while (!topKCollections.isEmpty()) {
            result.add(topKCollections.poll());
        }
        Collections.reverse(result);
        return result;
    }

    public int getTotalSizeOfFileProcessed() {
        return totalSizeOfFileProcessed;
    }

}
