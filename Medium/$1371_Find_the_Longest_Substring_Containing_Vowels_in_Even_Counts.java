package Medium;

import java.util.Map;
import java.util.HashMap;

// https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/

// This is a bit manipulation, hash map problem
// Note that we can use a bitmask to represent the vowels. A bit of 0 means even count, and a bit of 1 means odd count
// Whenever we encounter a vowel, we toggle the corresponding bit in the mask. We only need 5 bits to represent the 5 vowels.
// We will iterate the string as index i from 0 to n-1. We will keep a mask to represent the vowels count of the substring from 0 to i.
// Now, If we were to find the longest substring with even count of vowels, we need to find if there is a previous index j such that the mask at i and j are the same.
// Additionally, for it to be the longest substring, the j should be as front as possible in the string.
//
// Therefore, the algorithm is as follows:
// 1. Initialize a mask with 0, and a map with 0, 0. The map will store the index of the masks. Storing 0, 0 means that the mask 0 when the string is empty.
// 2. Iterate the string from 0 to n-1. For each character, toggle the corresponding bit in the mask.
// 3. If the mask is already in the map, then we have found a substring with even count of vowels. Update the result with the maximum length.
// 4. If the mask is not in the map, then add the mask to the map with the index i+1. This is because the substring from 0 to i has the mask.
// 5. Return the result.

public class $1371_Find_the_Longest_Substring_Containing_Vowels_in_Even_Counts {
    private final int bitA = 1;
    private final int bitE = 1 << 1;
    private final int bitI = 1 << 2;
    private final int bitO = 1 << 3;
    private final int bitU = 1 << 4;

    public int findTheLongestSubstring(String s) {
        int l = s.length();
        int res = 0;
        int mask = 0;
        Map<Integer, Integer> prefix = new HashMap<>();

        prefix.put(0, 0);

        // Compute prefix XORs, while checking if there is previous same mask in the map to cancel out the vowels.
        for (int i = 0; i < l; ++i) {
            char c = s.charAt(i);
            if (c == 'a') mask ^= bitA;
            if (c == 'e') mask ^= bitE;
            if (c == 'i') mask ^= bitI;
            if (c == 'o') mask ^= bitO;
            if (c == 'u') mask ^= bitU;

            if (prefix.containsKey(mask)) {
                int index = prefix.get(mask);
                res = Math.max(res, i - index + 1);
            } else {
                prefix.put(mask, i+1);
            }
        }

        return res;
    }
}
