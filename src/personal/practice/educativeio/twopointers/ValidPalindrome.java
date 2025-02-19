package personal.practice.educativeio.twopointers;

public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        char[] charArr = s.toCharArray();
        int start = 0;
        int end = charArr.length - 1;
        while (start < end) {
            if (Character.toLowerCase(charArr[start]) != Character.toLowerCase(charArr[end])) {
                return false;
            }
            ++start;
            --end;
        }
        return true;
    }

}
