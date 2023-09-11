package Hard;

import java.util.TreeSet;

//https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/
/*
 * This is a Matrix, Binary Tree (TreeSet), Dynamic Programming (Kadane's Algorithm) problem.
 * The problem is HARD, should not expect to be able to solve it without even any hints
 * 
 * First of all, consider brute force approach. We need to either:
 * 	>	Select all left upper corner and lower right corner, and find sum. O(n^2 * m^2) if uses prefix sum method. Otherwise
 * 		time complexity is worse
 * 	>	Instead of above, you can also consider select all left upper corner, then all possible width and height. Same time
 * 		complexity.
 * 
 * 	We need a method to lower the time complexity. Let's see how:
 * 	>	We can afford to consider all rectangles of different widths. Thus we can attempt rectangles with width from coordinates
 * 		(0,0), (0,1), (0,2)...(1,1), (1,2)... (N,N), taking time O(N^2) at first
 * 
 * 	>	Within each of this width rectangles, we want a better approach to find out what height gives the best sum within k.
 * 		What we could do is, perform prefix sum across that width rectangle.
 * 
 * 		Say our matrix is sized 5 row x 3 column. Then, During iteration covering columns (0,2), we should have a prefix sum
 * 		array of size 5 representing prefix sum for each rows.
 * 		Say this array is called prefixSumArr. prefixSumArr[1] should have row 2's prefix sum from 0 to 2.
 * 
 * 	>	If the problem simply wants the maximum value, we could run Kadane's algorithm, a simple Dynamic Programming algorithm
 * 		to find out maximum sum subarray from 1D array. However, since this problem restrict that our sum must be <= k, we cannot
 * 		directly apply.
 * 
 * 		Instead, we kind of apply the same idea to iterate thru the prefix sum array, summing for the "prefix sum of prefix sum"
 * 		The prefix sum of prefix sum will contain the sum of elements within the area. However, we may want to remove some subarray
 * 		to obtain the maximum sum.
 * 		
 * 		So, we store the prefix sum of prefix sum in each iteration in a sorted container structure -- Binary Search Tree. We are able
 * 		to search for the one subarray which when subtracted from current prefix sum of prefix sum, gives maximum value <= k.
 * 
 * 	
 * 	Leetcode contains the best explaination, and I highly recommend you to read it instead.
 */

public class $0363_Max_Sum_of_Rectangle_No_Larger_Than_K {
	
	public int maxSumSubmatrix(int[][] matrix, int k) {
		final int r = matrix.length, c = matrix[0].length;
		int res = Integer.MIN_VALUE;
		
		//Selects the left bound
		for (int left = 0; left < c; ++left) {
			int[] arr = new int[r];
			
			//Selects the right bound
			for (int right = left; right < c; ++right) {
				//Sum across the column elements
				for (int i = 0; i < r; ++i) arr[i] += matrix[i][right];
				
				int localRes = bestSumRestricted(arr, k);
				res = Math.max(res, localRes);
			}
		}
		return res;
    }
	
	
	//	Helper function that given 1D array, finds maximum sum less than K
	private int bestSumRestricted(int[] arr, int k) {
		TreeSet<Integer> set = new TreeSet<>();
		set.add(0);

		int res = Integer.MIN_VALUE;
		int prefixSum = 0;
		
		for (int i: arr) {
			prefixSum += i;
			Integer binSearch = set.ceiling(prefixSum - k);	// The target to find shall >= (prefixSum - k), so prefixSum - tgt will <= k
			
			if (binSearch != null)
				res = Math.max(res, prefixSum - binSearch);
			
			set.add(prefixSum);
		}
		return res;
	}
}
