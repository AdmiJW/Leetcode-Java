package Medium;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/maximum-erasure-value/
/*
 * 	This is a Two pointers/ Sliding Window + Set problem.
 * 
 * 	First of all, the fastest querying approach to know whether an element is currently in our 'window' is through
 * 	the use of HashSet, querying in O(1)
 * 
 * 	Then, how do we attempt to try out subarrays? One method is by brute force of generating all subarray of possible
 * 	sizes, which take O(N^2).
 * 	However, we shall use two pointers approach for efficiency. Have 2 pointers:
 * 		>	Left - The start of subarray
 * 		>	Right - The end of subarray
 * 	
 * 	Of course, we want our subarray to be as long as possible to maximize sum. However, what if we cant?
 * 		>	If the number at right pointer is not yet in subarray, extend our subarray!
 * 			++right
 * 
 * 		>	However, if number at right pointer is ALREADY in subarray, we'll try to shrink our subarray to exclude
 * 			the contained element so we can extend right pointer
 * 			++left
 * 
 * 	By this method, we will be checked all possible subarrays in O(N) time. Efficient!
 */

public class $1695_Maximum_Erasure_Value {
	
	public int maximumUniqueSubarray(int[] nums) {
		final int len = nums.length;
		Set<Integer> subarr = new HashSet<>();
		int res = 0;
		int subarrSum = 0;
		int left = 0, right = 0;
		
		while (right < len) {
			if ( !subarr.contains(nums[right]) ) {
				subarr.add(nums[right]);
				subarrSum += nums[right];
				++right;
				
				res = Math.max(res, subarrSum);
			} else {
				subarr.remove(nums[left]);
				subarrSum -= nums[left];
				++left;
			}
		}
		return res;
    }
	
}
