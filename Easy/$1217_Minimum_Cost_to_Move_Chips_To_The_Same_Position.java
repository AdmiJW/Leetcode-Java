package Easy;

//https://leetcode.com/problems/minimum-cost-to-move-chips-to-the-same-position/

/*
*   We have two type of moves:
*       >   Move 2 spaces, at no cost
*       >   Move 1 spaces, cost 1.
*   From this, we can observe that We can move all the tokens to any even or odd spaces, if they initially already is
*   even or odd. Meaning, if a token is odd, it can be moved to all odd spaces for free.
* 
*   In that case, we can simply move them into 2 neighboring spaces, A and B, which is next to each other.
*   
*   Now is the decision making time. Do we move all tokens from odd to even, or from even to odd?
*   The answer is just move whoever has the least tokens.
*/

public class $1217_Minimum_Cost_to_Move_Chips_To_The_Same_Position {
	
	public int minCostToMoveChips(int[] position) {
		int odds = 0, evens = 0;
		for (int i: position) {
			if ( (i & 1) == 1) odds ++;
			else evens ++;
		}
		
		return Math.min(odds, evens);
    }
	
}
