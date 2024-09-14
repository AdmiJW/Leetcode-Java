package Medium;

// https://leetcode.com/problems/longest-subarray-with-maximum-bitwise-and/

// This is a array, bit manipulation, with a little bit of brain teaser
// Observe that the bitwise AND of any two numbers, can only result in two cases:
//      1. If the numbers are same, then the result is the number itself
//      2. If the numbers are different, then the result will always strictly be less than the smaller number
// So, the maximum value of bitwise AND of any subarray of the given array, will always be the maximum number in the array
// The problem has been reduced to finding the longest subarray of the maximum number in the array
// So, we find the maximum number in the array, and then find the length of the longest consecutive subarray of that number 

public class $2419_Longest_Subarray_with_Maximum_Bitwise_AND {
    public int longestSubarray(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i: nums) max = Math.max(i, max);

        // Now find the length of longest consecutive max values
        int res = 0;
        int curr = 0;

        for (int i: nums) {
            if (i == max) ++curr;
            else curr = 0;

            res = Math.max(curr, res);
        }

        return res;
    }
}
