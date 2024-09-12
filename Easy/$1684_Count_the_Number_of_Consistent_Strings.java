package Easy;

// https://leetcode.com/problems/count-the-number-of-consistent-strings

// This is a HashMap, with possible of bit manipulation solution.
// The idea is to create a boolean array of size 26, and mark the characters that are allowed.
// Then, for each word, check if all the characters are allowed, if not, decrement the result.
// To use bit manipulation, we can use a single integer to store the allowed characters, where each bit represents a character.
// Then, we can use the bitwise AND operation to check if the character is allowed or not.

public class $1684_Count_the_Number_of_Consistent_Strings {
    public int countConsistentStrings(String allowed, String[] words) {
        boolean[] exists = new boolean[26];
        for (char c: allowed.toCharArray()) exists[c - 'a'] = true;

        int res = words.length;

        for (String w: words) {
            for (char c: w.toCharArray()) {
                if (!exists[c - 'a']) { --res; break; }
            }
        }

        return res;
    }
}
