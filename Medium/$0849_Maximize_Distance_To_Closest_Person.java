package Medium;


//https://leetcode.com/problems/maximize-distance-to-closest-person/
/*
	This is a Array problem.
	
	The best suitable test case for us to use is the one starting with empty seats, and middle empty seats surrounded by 2 person,
	then ending with empty seats. Like so:
	
	    ( ) ( ) (X) ( ) ( ) (X) ( ) ( )
	
	By iterating over the array once, we can find out the spaces between 2 person, by keeping track of the frequency of the empty
	seats met so far, and resetting it to 0 once we encounter a non empty seat.
	
	So in example above, when we iterate from first X to the second X, the empty seat counter shall be 2. Then, to find out the
	maximum distance, we just divide it into 2. In cases like (X) ( ) ( ) ( ) (X), we have to return answer = 2 in this case,
	so :
	        (Dist + 1 ) / 2
	should be fine, or
	        Ceiling( dist / 2.0 )
	
	Now, we have to deal with edge seats: The one starting empty and the ending empty seats. 
	For the ending seats, we actually don't have to do much. The counter keep incrementing whenever it met with empty seats.
	Therefore, at the end the counter shall be 2, which is exactly the distance from last seat to the closest occupied seats.
	
	As for starting empty seats, we can run a initial while loop, while the seat is empty, keep incrementing. That will be our
	starting maximum distance.

	---------------------------------------------------------------

	On the other hand, you could also solve it in Dynamic Programming manner using O(N) space and 2 passes.
	First pass, we will record the maximum distance from customer from the left only. This shouldn't be hard
	Second pass, we will combine the maximum distance from customer from the right with the previous result. The result
	will be the maximum distance between customer from two sides.
*/

public class $0849_Maximize_Distance_To_Closest_Person {
	
	public int maxDistToClosest(int[] seats) {
		int max = 0, dist = 0;
		int idx = 0;
		final int len = seats.length;
		
		//  While the starting seat is empty, keep incrementing
		while (idx < len && seats[idx] == 0) {
			max ++;
		}
		//  Iterate after meeting first non-empty seat
		while (idx < len) {
			//  Occupied seat
			if (seats[idx] == 1) {
				max = (int) Math.max( max , Math.ceil(dist / 2.0) );
				dist = 0;
			} 
			//  Empty seat
			else {
				dist ++;
			}
		}
		//  Now the dist is the distance from last occupied seat to the ending empty seat, if it is empty
		return Math.max( dist , max );
    }
	
	
	// DP solution using 1D array
	public int maxDistToClosest2(int[] seats) {
		final int len = seats.length;
		int[] dp = new int[len];
		int res = Integer.MIN_VALUE;
		
		int count = Integer.MAX_VALUE / 2;
		for (int i = 0; i < len; ++i) {
			if (seats[i] == 1) count = 0;
			else ++count;
			
			dp[i] = count;
		}
		
		count = Integer.MAX_VALUE / 2;
		for (int i = len - 1; i >= 0; --i) {
			if (seats[i] == 1) count = 0;
			else ++count;
			
			dp[i] = Math.min(dp[i], count);
			res = Math.max(res, dp[i]);
		}
		
		return res;
	}
	
}
