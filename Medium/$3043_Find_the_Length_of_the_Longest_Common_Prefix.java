package Medium;

import java.util.Set;
import java.util.HashSet;

// https://leetcode.com/problems/find-the-length-of-the-longest-common-prefix/

// This is a Set / Trie problem.
// From arr1, we can derive all the possible prefixes and store them in a set for efficient lookup.
// Then, we can iterate through arr2 and check if any of the prefixes are present in the set.
// If we find a prefix, we can update the result with the maximum of the current result and the prefix.
//
// Another way to solve this problem is to use a Trie data structure.
// We can insert all the prefixes from arr1 into the Trie and then search for the prefixes in arr2.

public class $3043_Find_the_Length_of_the_Longest_Common_Prefix {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        int res = 0;
        Set<Integer> set = new HashSet<>();

        for (int i: arr1) {
            while (i > 0) {
                set.add(i);
                i /= 10;
            }
        }

        for (int i: arr2) {
            while (i > 0) {
                if (set.contains(i)) {
                    res = Math.max(res, i);
                    break;
                }
                i /= 10;
            }
        }
        
        return res == 0 ? 0 : Integer.toString(res).length();
    }    
}
