package Medium;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/longest-increasing-subsequence/
/*
 * This is a Dynamic Programming / Binary Search problem.
 * 
 * For DP approach, we will have a DP array of 1D, same size as the array itself. Each index i in dp[i]
 * records the longest increasing subsequence that possibly can be formed from nums[0] until nums[i],
 * but MUST END WITH ELEMENT nums[i].
 * 
 * To fill dp[i], scan backwards to see whether current element, nums[i], can extend nums[j], where j < i.
 * Therefore, this algorithm takes O(N^2) time and O(N) extra space.
 * 
 * ===================================================================
 * 
 * For binary search, let's see how we can actually solve the problem by attempting to build the subsequence itself.
 * Say we have a subsequence ready: [10,20,30,40] that is current longest.
 * 
 * > If next element is 50, we know we can extend the subsequence immediately since it is larger than the largest element
 * 	 in our subsequence. 
 *   Thus it becomes [10,20,30,40,50]
 *   
 * > Say if element is 35, although it is not largest, but we can TRY TO MAXIMIZE THE POSSIBILITY TO EXTEND IN THE FUTURE.
 *   To maximize the possibility, we want each elements in the subsequence to be small as possible, right?
 *   
 *   In this case, we can use this 35 to replace 40, thus it becomes [10,20,30,35,50]. Say if next element is 36, then it
 *   becomes [10,20,30,35,36]. Now if again it is 37, we now is able to extend! This is what is meant to 'maximize the
 *   possibility to extend'.
 *   
 *   To search for replacement, since subsequence is sorted, we can use binary search. Thus time complexity O(N log N).
 * 
 */

public class $0594_Longest_Increasing_Subsequence {
	
	// Conventional O(N^2) DP approach
	public int lengthOfLIS(int[] nums) {
		final int len = nums.length;
		int[] dp = new int[len];
		int res = 1;
		
		for (int i = 0; i < len; ++i) {
			dp[i] = 1;			// An element can always start a subsequence by itself
			for (int j = i-1; j >= 0; --j)
				if (nums[i] > nums[j])
					dp[i] = Math.max(dp[j]+1, dp[i]);
			res = Math.max(res, dp[i]);
		}
		return res;
    }
	
	// O(N log N) intelligent build subsequence approach
	public int lengthOfLIS2(int[] nums) {
		final int len = nums.length;
		List<Integer> l = new ArrayList<>(len);
		
		l.add(nums[0]);
		
		for (int i = 1; i < len; ++i) {
			if (nums[i] > l.get( l.size() - 1) )
				l.add(nums[i]);
			else {
				// binary search on the number that is greater than it, but least
				int left = 0, right = l.size() - 1;
				while (left < right) {
					int mid = left + (right - left) / 2;
					if (l.get(mid) >= nums[i])
						right = mid;
					else
						left = mid + 1;
				}
				l.set(left, nums[i]);
			}
		}
		return l.size();
	}
	
}
