package Easy;

// https://leetcode.com/problems/average-salary-excluding-the-minimum-and-maximum-salary/
/*
 * A very simple array problem.
 * Just iterate through the array, obtain the min and max salary, and the sum of all the salary in O(N) time and O(1) space.
 * 
 * At the end, just subtract the min and max from the sum, and divide by N - 2
 */



class Solution {
    public double average(int[] salary) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int sum = 0;

        for (int s: salary) {
            min = Math.min(min, s);
            max = Math.max(max, s);
            sum += s;
        }

        return (sum + 0.0 - min - max) / (salary.length - 2);
    }
}