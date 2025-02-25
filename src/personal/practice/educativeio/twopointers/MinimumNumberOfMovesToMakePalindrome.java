package personal.practice.educativeio.twopointers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//https://leetcode.com/problems/minimum-number-of-moves-to-make-palindrome/
//https://www.youtube.com/watch?v=2Vcdjb-H8yA&ab_channel=CrackingFAANG
//https://www.educative.io/courses/grokking-coding-interview/minimum-number-of-moves-to-make-palindrome
public class MinimumNumberOfMovesToMakePalindrome {

    public int minMovesToMakePalindrome(String s) {
        List<Character> charList = new ArrayList<>();
        for (char ch : s.toCharArray()) {
            charList.add(ch);
        }
        int result = 0;
        while (charList.size() > 1) {
            int lastIndex = charList.size() - 1;
            char lastChar = charList.get(lastIndex);
            int fistIndex = charList.indexOf(lastChar);
            if (fistIndex == lastIndex) {
                result += (lastIndex / 2);
                charList.remove(lastIndex);
            } else {
                result += fistIndex;
                charList.remove(lastIndex);
                charList.remove(fistIndex);
            }
        }

        return result;
    }
}
