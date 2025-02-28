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
        System.out.println(repeatedDNASequences.findRepeatedSequences_rollingWindow("CGG", 1));
        System.out.println(repeatedDNASequences.findRepeatedSequences_slidingWindowWithBitMasking("CGG", 1));

        System.out.println(repeatedDNASequences.findRepeatedSequences_UsingString("AAAAACCCCCAAAAACCCCCC", 8));
        System.out.println(repeatedDNASequences.findRepeatedSequences_rollingWindow("AAAAACCCCCAAAAACCCCCC", 8));
        System.out.println(repeatedDNASequences.findRepeatedSequences_slidingWindowWithBitMasking(
                "AAAAACCCCCAAAAACCCCCC", 8));

        System.out.println(repeatedDNASequences.findRepeatedSequences_UsingString("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
                10));
        System.out.println(repeatedDNASequences.findRepeatedSequences_rollingWindow("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
                , 10));
        System.out.println(repeatedDNASequences.findRepeatedSequences_slidingWindowWithBitMasking(
                "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
                , 10));
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

    public Set<String> findRepeatedSequences_rollingWindow(String dna, int k) {
        Set<String> result = new HashSet<String>();
        if (dna.length() < k)
            return result;

        Map<Character, Integer> charToInt = new HashMap<>() {{
            put('A', 0);
            put('C', 1);
            put('G', 2);
            put('T', 3);
        }};

        int hash = 0;
        int base = 4;
        int lastDigitPow = (int) Math.pow(base, k - 1);
        Set<Integer> seen = new HashSet<>();
        int index = 0;
        for (; index < k; index++) {
            int num = charToInt.get(dna.charAt(index));
            hash *= base;
            hash += num;
        }
        seen.add(hash);
        for (; index < dna.length(); index++) {
            int last = charToInt.get(dna.charAt(index - k));
            hash -= (last * lastDigitPow);
            hash *= base;
            hash += charToInt.get(dna.charAt(index));
            if (seen.contains(hash)) {
                result.add(dna.substring(index - k + 1, index + 1));
            } else {
                seen.add(hash);
            }

        }
        return result;
    }

    public Set<String> findRepeatedSequences_slidingWindowWithBitMasking(String dna, int k) {
        Set<String> result = new HashSet<String>();
        if (dna.length() < k)
            return result;
        Map<Character, Integer> charToInt = new HashMap<>() {{
            put('A', 0);
            put('C', 1);
            put('G', 2);
            put('T', 3);
        }};
        int hash = 0;
        Set<Integer> seen = new HashSet<>();
        int index = 0;
        for (; index < k; index++) {
            int num = charToInt.get(dna.charAt(index));
            hash <<= 2;
            hash |= num;
        }
        seen.add(hash);
        int bitmask = ~(3 << 2 * k);
        for (; index < dna.length(); ++index) {
            int num = charToInt.get(dna.charAt(index));
            hash <<= 2;
            hash |= num;
            hash &= bitmask;
            if (seen.contains(hash)) {
                result.add(dna.substring(index - k + 1, index + 1));
            } else {
                seen.add(hash);
            }
        }
        return result;
    }
}
