package Medium;

import java.util.Arrays;

//https://leetcode.com/problems/3sum-closest/
/*
 * 	This is a Two pointers, sorting, (Optional: Binary Search) problem.
 * 
 * 	For binary search solution, what you do is first sort the array. Then, fix the first two numbers. 
 * 	The right side of the second fixed number you will perform binary search on it to find the closest 3sum to
 * 	target.
 * 	Time taken is O(N^2 log N)
 * 
 * 	==================================================
 * 
 * 	When handling with 3Sum problems or Nsum problem, always remember, you could ALWAYS reduce the time complexity
 * 	to N-1, by reducing the problem into N-1sum Problem.
 * 
 * 	Say in this case, we could fix the first number, then on the remaining subarray on the right, we could perform
 * 	a 2Sum algorithm (Two pointers) on it, which runs in O(N) time, resulting in O(N^2) time complexity.
 * 
 * 	When fixed the first element, we will have two pointers initially pointing at the head and tail of remaining
 * 	subarray. Check the sum of the three pointers. If sum > target, decrement the right pointer. Otherwise, increment
 * 	the left pointer.
 * 	Remember to update the res variable every iteration.
 */

public class $0016_3Sum_Closest {
	
	// O(N^2 log N) Sorting + Binary Search approach. Not optimal, but acceptable
	public int threeSumClosest(int[] nums, int target) {
		final int len = nums.length;
		int res = Integer.MAX_VALUE / 2;	// This value only works if range of elements in nums[] is small.
		
		Arrays.sort(nums);
		
		for (int i = 0; i < len-2; ++i) {
			for (int j = i+1; j < len-1; ++j) {
				int l = j+1, r = len-1;
				
				while (l < r) {
					int mid = l + (r - l) / 2;
					int diff = target - nums[i] - nums[j] - nums[mid];
					
					if (diff > 0)
						l = mid+1;
					else
						r = mid;
				}
				// Since left pointer can easily exceed, we check the left pointer and its previous element.
				if (Math.abs(target - nums[i] - nums[j] - nums[l]) < Math.abs(target - res))
					res = nums[i] + nums[j] + nums[l];
				if (l-1 > j && Math.abs(target - nums[i] - nums[j] - nums[l-1]) < Math.abs(target - res))
					res = nums[i] + nums[j] + nums[l-1];
			}
		}
		return res;
    }
	
	
	// Two pointers solution, reducing to two sum problem, O(N^2) time.
	public int threeSumClosest2(int[] nums, int target) {
		final int len = nums.length;
		int res = Integer.MAX_VALUE / 2;
		
		Arrays.sort(nums);
		
		for (int i = 0; i < len-2; ++i) {
			int l = i+1, r = len-1;
			
			while (l < r) {
				int sum = nums[i] + nums[l] + nums[r];
				
				// Update res
				if (Math.abs(target - res) > Math.abs(target - sum))
					res = sum;
				
				if (target - sum > 0)
					++l;
				else
					--r;
			}
		}
		return res;
	}
	
}
