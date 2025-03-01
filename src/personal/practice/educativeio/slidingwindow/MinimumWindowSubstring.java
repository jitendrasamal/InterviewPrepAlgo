package personal.practice.educativeio.slidingwindow;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/minimum-window-substring/description/
//https://www.educative.io/courses/grokking-coding-interview/minimum-window-substring
public class MinimumWindowSubstring {
    public static void main(String[] args) {
        MinimumWindowSubstring minimumWindowSubstring = new MinimumWindowSubstring();
        System.out.println(minimumWindowSubstring.minWindow("ADOBECODEBANC", "ABC"));
    }

    public String minWindow(String s, String t) {
        if (s.isEmpty() || t.isEmpty() || s.length() < t.length())
            return "";
        Map<Character, Integer> requiredMap = new HashMap<>();
        int requiredCount = 0;
        for (char c : t.toCharArray()) {
            requiredMap.put(c, requiredMap.getOrDefault(c, 0) + 1);
            ++requiredCount;
        }
        int start = 0;
        int end = 0;
        String result = "";
        Map<Character, Integer> currentWindowMap = new HashMap<>();
        int currentWindMatchCount = 0;
        for (; end < s.length(); ++end) {
            char c = s.charAt(end);
            if (requiredMap.containsKey(c)) {
                currentWindowMap.put(c, currentWindowMap.getOrDefault(c, 0) + 1);
                if (currentWindowMap.get(c) <= requiredMap.get(c))
                    ++currentWindMatchCount;
            }
            while (requiredCount == currentWindMatchCount) {
                if (result.isEmpty() || result.length() > (end - start + 1)) {
                    result = s.substring(start, end + 1);
                }
                c = s.charAt(start);
                if (requiredMap.containsKey(c)) {
                    currentWindowMap.put(c, currentWindowMap.get(c) - 1);
                    if (currentWindowMap.get(c) < requiredMap.get(c)) {
                        --currentWindMatchCount;
                    }
                }
                ++start;
            }
        }
        return result;
    }
}
