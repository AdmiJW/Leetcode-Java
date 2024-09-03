package Easy;

// https://leetcode.com/problems/sum-of-digits-of-string-after-convert/

// This is a simple String, simulation problem.
// Do be careful, as the string length can be up to 100, where 'z' will be 26, so the initial conversion to digits may be very large to fit in `int` or even `long`.
// Therefore, one might consider just to use `String` for the conversion and summing up the digits.

public class $1945_Sum_of_Digits_of_String_After_Convert {
    public int getLucky(String s, int k) {
        String res = stringToDigits(s);
        for (int i = 0; i < k; ++i) res = sumOfDigits(res);
        return Integer.parseInt(res);
    }

    private String stringToDigits(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c: s.toCharArray()) sb.append(c - 'a' + 1);
        return sb.toString();
    }

    private String sumOfDigits(String s) {
        int res = 0;
        for (char c: s.toCharArray()) res += c - '0';
        return Integer.toString(res);
    }
}
