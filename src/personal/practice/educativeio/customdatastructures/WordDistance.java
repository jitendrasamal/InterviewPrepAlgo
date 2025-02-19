package personal.practice.educativeio.customdatastructures;

//https://leetcode.com/problems/shortest-word-distance-ii/
//https://www.educative.io/courses/grokking-coding-interview/shortest-word-distance-ii

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class WordDistance {

    HashMap<String, List<Integer>> wordIndices;

    public WordDistance(String[] wordsDict) {
        wordIndices = new HashMap<>();
        for (int i = 0; i < wordsDict.length; i++) {
            List<Integer> list = wordIndices.computeIfAbsent(wordsDict[i], k -> new ArrayList<>());
            list.add(i);
        }
    }

    public static void main(String[] args) {
        WordDistance wordDistance = new WordDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"});
        System.out.println(wordDistance.shortest("coding", "practice")); // return 3
        System.out.println(wordDistance.shortest("makes", "coding"));    // return 1
    }

    public int shortest(String word1, String word2) {
        List<Integer> list1 = wordIndices.get(word1);
        List<Integer> list2 = wordIndices.get(word2);
        return shortestDiff(list1, list2);
    }

    private int shortestDiff(List<Integer> arr1, List<Integer> arr2) {
        int index1 = 0, index2 = 0;
        int result = Integer.MAX_VALUE;
        while (index1 < arr1.size() && index2 < arr2.size()) {
            result = Math.min(result, Math.abs(arr1.get(index1) - arr2.get(index2)));
            if (arr1.get(index1) < arr2.get(index2)) {
                ++index1;
            } else {
                ++index2;
            }
        }
        return result;
    }

}