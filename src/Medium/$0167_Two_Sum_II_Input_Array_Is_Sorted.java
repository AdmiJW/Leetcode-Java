package Medium;

//https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
/*
 * This is a two-pointers problem.
 * 
 * Since the array is already sorted, we can use two pointers approach to solve the problem in O(N) time.
 * The two pointers are initialized to be at both ends of the array: Smallest element and Largest element.
 * Check the sum of two elements.
 * 		If the sum is too large - Decrement right pointer because the current large element cannot be used
 * 		If the sum is too small - Increment left pointer because the current small element cannot be used
 * 
 * If there is definitely one solution, we will eventually have left and right pointer such that the sum == target.
 */

public class $0167_Two_Sum_II_Input_Array_Is_Sorted {
	public int[] twoSum(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        
        while (l < r) {
        	int sum = numbers[l] + numbers[r];
        	if (sum == target) return new int[] { l+1, r+1 };
        	else if (sum > target) --r;
        	else ++l;
        }
        
        return new int[] { -1, -1 };
    }
}
