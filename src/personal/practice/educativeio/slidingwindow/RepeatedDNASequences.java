package personal.practice.educativeio.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//https://www.educative.io/courses/grokking-coding-interview/repeated-dna-sequences
//https://leetcode.com/problems/repeated-dna-sequences/
public class RepeatedDNASequences {
    public static void main(String[] args) {
        RepeatedDNASequences repeatedDNASequences = new RepeatedDNASequences();
        System.out.println(repeatedDNASequences.findRepeatedSequences_UsingString("CGG", 1));
        System.out.println(repeatedDNASequences.findRepeatedSequences_UsingString("AAAAACCCCCAAAAACCCCCC", 8));
    }

    public Set<String> findRepeatedSequences_UsingString(String dna, int k) {
        Set<String> result = new HashSet<String>();
        if (dna.length() < k)
            return result;
        Map<String, Integer> sequenceCount = new HashMap<>();
        int index = 0;
        while (index + k <= dna.length()) {
            String key = dna.substring(index, index + k);
            sequenceCount.put(key, 1 + sequenceCount.getOrDefault(key, 0));
            ++index;
        }
        for (Map.Entry<String, Integer> entry : sequenceCount.entrySet()) {
            if (entry.getValue() > 1) {
                result.add(entry.getKey());
            }
        }

        return result;
    }
}
