package Easy;

//https://leetcode.com/problems/baseball-game/
/*
 * A straightforward stack problem, which simulates the exact behavior we are doing in the problem.
 * 
 * C - Pop from the stack basically invalidates the previous score
 * D - Add a new integer which is double of previous score (peek)
 * + - Obtain last 2 integers from the stack, sum them up, and add the sum of the 2 into the stack
 * INT - Append to stack
 * 
 * Lastly, pop all the elements from the stack and sum them up.
 */


import java.util.ArrayDeque;
import java.util.Deque;

public class $0682_Baseball_Game {
	public int calPoints(String[] ops) {
        Deque<Integer> stk = new ArrayDeque<>();
        
        for (String s: ops) {
        	if (s.equals("D")) stk.push( stk.peek() * 2 );
        	else if (s.equals("C")) stk.pop();
        	else if (s.equals("+")) {
        		int prev = stk.pop();
        		int	score = prev + stk.peek();
        		stk.push(prev);
        		stk.push(score);
        	}
        	else stk.push( Integer.parseInt(s));
        }
        
        int sum = 0;
        while (!stk.isEmpty()) sum += stk.pop();
        
        return sum;
    }
}
