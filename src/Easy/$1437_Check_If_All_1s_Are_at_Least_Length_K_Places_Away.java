package Easy;

//https://leetcode.com/problems/check-if-all-1s-are-at-least-length-k-places-away/
/*
 * 	Fairly simple problem.
 * 
 * 	Either use a counter to count number of zeroes, and reset to 0 once it hits a 1
 * 	Or record the last index of 1, and once another 1 is hit, calculate distance
 * 
 * 	Here implementation is calculate distance
 */


public class $1437_Check_If_All_1s_Are_at_Least_Length_K_Places_Away {

	public boolean kLengthApart(int[] nums, int k) {
		int last = Integer.MIN_VALUE / 2;
		
		for (int i = 0; i < nums.length; ++i) {
			if (nums[i] == 1) {
				if (i - last - 1 < k) return false;
                last = i;
			}
		}
		return true;
    }
	
}
