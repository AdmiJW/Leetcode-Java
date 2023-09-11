package Medium;

// https://leetcode.com/problems/minimum-average-difference
/*
 * This is an array prefix sum problem.
 * 
 * First, obtain the sum of all the array elements. Note that the sum may exceed the integer range, use long type.
 * Then, in the iteration, do a prefix sum, and the right hand side sum is the sum of all elements minus the prefix sum.
 */


public class $2256_Minimum_Average_Difference {
    public int minimumAverageDifference(int[] nums) {
        final int n = nums.length;
        
        // One pass to obtain the sum of all numbers
        long sum = 0;
        for (int num: nums) sum += num;

        // Another pass to find out the minimum average difference
        long minDiff = Integer.MAX_VALUE;
        int minDiffIndex = -1;
        long runningSum = 0;

        for (int i = 0; i < n; i++) {
            runningSum += nums[i];
            long leftAvg = runningSum / (i + 1);
            long rightAvg = (i == n - 1)? 0: (sum - runningSum) / (n - i - 1);
            long diff = Math.abs(leftAvg - rightAvg);

            if (diff < minDiff) {
                minDiff = diff;
                minDiffIndex = i;
            }
        }

        return minDiffIndex;
    }
}
