package Hard;


//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
/*
 * 	This is a DP problem.
 * 	We have a series of stock prices, and we are only limited to only just 2 transactions. How would we go about determining
 * 	the maximum profit we can take?
 * 
 * 	We can divide the stock prices into first, and second section. The first section and second section will be
 * 	the maximum profit that we'll reap on that timeframe. By combining the profit on those 2 different timeframes, 
 * 	we essentially had gotten ourselves maximum profit of 2 transactions
 * 
 * 	So we are going to generate 2 different DP arrays, one will indicate the maximum profit which timeframe starting from
 * 	first day and ending at index i of the array (variable ending day)
 * 	
 * 	The second DP array will indicate the maximum profit which timeframe ends at the last day and starting at index i of
 * 	the array (variable starting day)
 * 
 * 	To deal with those individual DP arrays are more simple.
 * 	For DP1, everyday we either have the choice of not selling (Taking max profit from prev day),
 * 			or sell the stocks on this day, which we bought on the minimum price day
 * 	So basically we have to keep track of the minimum price that had occurred in past days
 * 
 * 	For DP2, since we are going backwards from the end, we either has the choice of buying the stock this day, or do nothing
 * 			If we do nothing, then we will buy at some later day, and sell at the maximum price day
 * 			If we choose to buy, then we will profit for (maximum price in timeframe - thisdayprice)
 * 	So basically we have to keep track of the maximum price that had occurred in the timeframe
 * 
 * 	Since the two timeframes shall join together, we shall compare those DP arrays column by column,
 * 	We will return the maximum of DP1[i] + DP2[i]
 * 
 * 	--------------------------------------------------------------------
 * 
 * 	Turns out, like "Best Time to Buy and Sell Stock II", it can be solved in similar, one pass, DP solution with
 * 	the aid of state diagram. To understand this, please also understand Best Time to Buy and Sell Stock II first.
 * 	The DP solution is recorded there
 * 
 * 	There are total of 4 states in this question:
 * 		>	sold_2:				We've sold the 2nd stock. We can reach this state by either staying after having sold
 * 								previously hold 2nd stock, or selling holded 2nd stock (hold_2)
 * 		>	hold_2:				We are currently holding the 2nd stock. This state is reached by either buying in another
 * 								stock after selling the first one (sold_1), or keep holding the holded 2nd stock
 * 		>	sold_1:				We've sold the 1st stock. This state is reached via selling the previously hold 1st stock
 * 								(hold_1), or staying and do nothing after sold 1st stock.
 * 		>	hold_1:				We've bought in the 1st stock. This state is reached via holding the previously bought 1st
 * 								stock (not selling), or simply buy in the current stock with -price. 
 * 
 * 	Try to draw a state diagram and hopefully you can understand how each state connects.
 */

public class $0123_Best_Time_To_Buy_And_Sell_Stock_III {
	
	public int maxProfit(int[] prices) {
		if (prices.length == 0) return 0;
        final int days = prices.length;
        
        //	The first half section DP. 
        int min = prices[0];
        int[] dp1 = new int[days + 1];
        
        for (int i = 1; i <= days; i ++ ) {
        	dp1[i] = Math.max( prices[i - 1] - min, dp1[i - 1] );
        	min = Math.min( min, prices[i - 1] );
        }

        //	The second half section DP.
        int max = prices[days - 1];
        int[] dp2 = new int[days + 1];
        
        for (int i = days - 1; i >= 0; i --) {
        	dp2[i] = Math.max( dp2[i + 1], max - prices[i] );
        	max = Math.max( max, prices[i] );
        }
        
        //	Finally find the maximum combo
        int res = 0;
        for (int i = 0; i < days - 1; i ++ ) {
        	res = Math.max( res, dp1[i+1] + dp2[i] );
        }
        return res;
    }
	
	
	
	//	DP, State Diagram solution
	public int maxProfit2(int[] prices) {
		int sold2 = 0, sold1 = 0;
		int hold2 = Integer.MIN_VALUE, hold1 = Integer.MIN_VALUE;
		
		for (int p: prices) {
			sold2 = Math.max(sold2, hold2+p);
			hold2 = Math.max(hold2, sold1-p);
			sold1 = Math.max(sold1, hold1+p);
			hold1 = Math.max(hold1, -p);
		}
		
		return sold2;
	}

}
