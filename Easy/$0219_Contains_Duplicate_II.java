package Easy;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/contains-duplicate-ii/
/*
 * 	This is a sliding window + HashMap problem.
 * 
 * 	We have to return true if there is duplicated element within sub-window of the array of size k+1.
 * 	Therefore, use the sliding window technique to update our window HashMap that a number is within the current subwindow or not.
 */

public class $0219_Contains_Duplicate_II {
	
	public boolean containsNearbyDuplicate(int[] nums, int k) {
		Map<Integer, Boolean> window = new HashMap<>();
		
		for (int i = 0; i <= Math.min(nums.length - 1, k); ++i) {
			if (window.getOrDefault(nums[i], false)) return true;
			window.put(nums[i], true);
		}
		
		for (int i = k+1; i < nums.length; ++i) {
			window.put(nums[i - k - 1], false);
			if (window.getOrDefault(nums[i], false)) return true;
			window.put(nums[i], true);
		}
		
		return false;
    }
	
}
