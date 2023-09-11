package Medium;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/delete-and-earn/
/*
 * This is a dynamic programming problem.
 * 
 * First of all, what matters more in this problem is the element itself, not the index in the array.
 * Therefore, we convert the array into a frequency table - { element - count }
 * 
 * While obtaining the frequency table, we should also obtain the maximum element which can go up to 10000 only.
 * The maximum element will be useful in DP part later.
 * 
 * ----------------------------------
 * 
 * For DP, we will imagine we have a dp array up until {max} size only. DP[i] will indicate the maximum points
 * that we can possibly earn from considered deleting elements from (0,i].
 * 
 * Therefore, since we will have to remove e-1 and e+1 when we try to earn points from element e, the transition
 * function shall be clearer:
 * 
 * 		dp[i] = max(
 * 			dp[i-1],						// <- If we do not earn from element i
 * 			dp[i-2] + (count of i) * i		// <- If we earn from element i. We cannot get points from element i-1
 * 		)
 * 
 * Using this, Dynamic programming approach shall be clear. Note that since we only use elements from i-1 and i-2,
 * we can space optimize to use only O(1) extra space.
 */

public class $0740_Delete_and_Earn {

	// Frequency table + DP solution �iNot space optimized)
	public int deleteAndEarn(int[] nums) {
        // Step 1 - convert array into frequency table (Also record maximum element)
		int max = 0;
		Map<Integer, Integer> freq = new HashMap<>();
		
		for (int i: nums) {
			max = Math.max(max, i);
			freq.compute(i, (k,v)-> v == null? 1: v + 1);
		}
		
		// Step 2 - Using DP to compute the maximum points, where DP[i] means maximum point earned if i covered up to
		// element i.
		int[] dp = new int[max + 1];
		dp[1] = freq.getOrDefault(1, 0);
		
		for (int i = 2; i <= max; ++i)
			dp[i] = Math.max(dp[i-1], dp[i-2] + freq.getOrDefault(i, 0) * i);
		
		return dp[max];
    }
}
