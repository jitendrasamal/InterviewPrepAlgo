package personal.practice.educativeio.slidingwindow;

//https://www.educative.io/courses/grokking-coding-interview/minimum-window-subsequence
//https://leetcode.com/problems/minimum-window-subsequence/description/
public class MinimumWindowSubsequence {
    /*
    ab bd bdcdebdde
         i
    abbdbdcdebdde
    bbde
     i

     */

    public static void main(String[] args) {
        MinimumWindowSubsequence minimumWindowSubsequence = new MinimumWindowSubsequence();
        String[] str1 = {
                "abcdedeaqdweq", "fgrqsqsnodwmxzkzxwqegkndaa", "zxcvnhss", "alpha", "beta"
        };
        String[] str2 = {
                "adeq", "kzed", "css", "la", "ab"
        };
        for (int i = 0; i < str1.length; i++) {
            System.out.println(i + 1 + ".\tInput String: " + "(" + str1[i] + ", " + str2[i] + ")");
            System.out.println("\tSubsequence string: " + minimumWindowSubsequence.minWindow(str1[i], str2[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    public String minWindow(String str1, String str2) {
        int index1 = 0;
        int index2 = 0;
        while (index1 < str1.length() && index2 < str2.length()) {
            if (str1.charAt(index1) == str2.charAt(index2)) {
                ++index2;
            }
            ++index1;
        }
        if (index2 != str2.length()) {
            return "";
        }
        int startIndex = 0;
        String result = str1.substring(startIndex, index1);
        --index1;
        for (; index1 < str1.length(); ++index1) {
            if (str1.charAt(index1) == str2.charAt(str2.length() - 1)) {
                int end2 = str2.length() - 1;
                int end1 = index1;
                while (end2 >= 0 && end1 >= startIndex) {
                    if (str1.charAt(end1) == str2.charAt(end2)) {
                        --end2;
                    }
                    --end1;
                }
                if (end2 < 0) {
                    startIndex = end1 + 1;
                    if ((index1 - startIndex + 1) < result.length()) {
                        result = str1.substring(startIndex, index1 + 1);
                    }
                }
            }
        }

        return result;
    }
}
