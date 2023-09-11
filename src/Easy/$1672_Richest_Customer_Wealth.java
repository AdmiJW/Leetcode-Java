package Easy;

//https://leetcode.com/problems/richest-customer-wealth/
/*
 * Simple matrix summation problem
 * 
 * Sum each row. Return the maximum.
 */



public class $1672_Richest_Customer_Wealth {
	
	// Simple matrix sum
	public int maximumWealth(int[][] accounts) {
		int res = 0;
		
		for (int[] cust: accounts) {
			int sum = 0;
			for (int amt: cust) sum += amt;
			res = Math.max(res, sum);
		}
		return res;
	}
	
}
