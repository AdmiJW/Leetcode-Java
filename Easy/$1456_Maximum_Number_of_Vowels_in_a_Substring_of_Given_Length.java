package Easy;


// https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/
/*
 * This is a Sliding Window problem.
 * 
 * We need to find the maximum number of vowels in a substring of given length k.
 * Therefore, initialize the window with the first k characters, and count the number of vowels in the window.
 * Then, keep sliding the window to the right by 1 character, and count the number of vowels in the new window, and update the maximum.
 */


public class $1456_Maximum_Number_of_Vowels_in_a_Substring_of_Given_Length {

    public int maxVowels(String s, int k) {
        int window = 0;
        for (int i = 0; i < k; i++) window += isVowel(s.charAt(i));
        int max = window;

        for (int i = k; i < s.length(); ++i) {
            window += isVowel(s.charAt(i)) - isVowel(s.charAt(i - k));
            max = Math.max(max, window);
        }

        return max;
    }

    private int isVowel(char c) {
        return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') ? 1 : 0;
    }

}
