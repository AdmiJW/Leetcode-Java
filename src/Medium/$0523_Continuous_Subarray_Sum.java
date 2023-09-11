package Medium;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/continuous-subarray-sum/
/*
 * 	This is a prefix sum, modulus math problem.
 * 
 * 	The main challenge in solving this question is to realize the best way to detect if past subarray contains
 * 	a multiple of k.
 * 
 * 	Establish this: Let's say we have a prefix sum array, prefix[]
 * 	
 * 	If 
 * 		prefix[i] % k = A
 * 	and at a later index j where i < j
 * 		prefix[j] % k = A
 * 	that means the subarray between i and j actually has multiple of k in it! See example:
 * 
 * 	k = 6, [1, 3, 3, 4]
 * 	The prefix[] will be [1, 4, 7, 11]
 * 	and after modulus k, it is [1, 4, 1, 5]
 * 	
 * 	You can see at i = 0, j = 2, the modulus is the same (1). WHy is this?
 *	At i = 0, prefix[i] = 1. At j = 2, prefix[j] = 7. You can see that the [3,3] part of the subarray actually is multiple of k!
 *	
 *	That means if we encounter the two modulus that are the same, it means there exists a subarray in between i and j that
 *	sums up to multiple of k!
 */

public class $0523_Continuous_Subarray_Sum {
	
	public boolean checkSubarraySum(int[] nums, int k) {
		Map<Integer, Integer> mod = new HashMap<>();
		int sum = 0;
		
		mod.put(0, 0);
		
		for (int i = 0; i < nums.length; ++i) {
			sum += nums[i];
			
			if (!mod.containsKey(sum % k))
				mod.put(sum % k, i + 1);
			else if (mod.get(sum % k) < i)
				return true;
		}
		
		return false;
    }
}
