package Hard;

import java.util.Arrays;
import java.util.PriorityQueue;

//https://leetcode.com/problems/maximum-performance-of-a-team/
/*
 * 	This is a hard greedy, sorting problem.
 * 	Of course, couldn't come up with the solution. I looked at it.
 * 
 * 
 * 	The brute force involves generating all possible team formations, which involves backtracking and recursion,
 * 	and takes exponential time, not efficient and not satisfy the time limit.
 * 
 * 	=======================================================================================
 * 
 * 	The performance points is calculated by SUM(speed) * MIN(efficiency) of the team. We can see, the bottleneck
 * 	is the MIN(efficiency) there.
 * 
 * 	Observe this: in the optimal solution, surely there must be a minimum efficient engineer of the whole team.
 * 	Eg in the solution where efficiencies are given by [5,8,3,6], the engineer with efficiency 3 is the one.
 * 
 * 	What if we attempt to fix every engineer to have the minimum efficiency in whole team, then:
 * 
 * 		>	Other engineers in the team must have efficiency at least equal or higher than this fixed engineer
 *		>	With this, we know the MIN(efficiency) as the fixed engineer's efficiency value. How about SUM(speed)?
 *			Since it is summation, the more speed the better. Try to include as many engineers as possible, only
 *			if the efficiency is greater or equal to that of fixed engineer's
 *
 *	Therefore, if we sort the engineers in advance by efficiency in descending order, surely as we proceed iteration thru
 *	engineers,
 *		>	All engineers earlier are having efficiency value higher or equal to current fixed engineer's!
 *		>	The problem now is to choose the best previously seen engineers that give good SUMS.
 *
 *	To pick out the previously seen engineers with good SUMS, we would utilize heap (priority queue) for that.
 *	With a priority queue, we don't even have to iterate the previously seen engineers again for each fixed
 *	engineers, since we will always keep the best engineers of highest speed in the priority queue, popping
 *	out the lowest speed engineer as required.
 *
 *
 *	Time complexity is O(N (log N + log K))
 *		> N log N for sorting
 *		> N log K, for each engineer we fix as lowest efficiency engineer, we check if queue is size K, pop as necessary
 *
 *	Space is O(N + K). Since we construct the tuple array of size N here for sorting purposes.
 */

public class $1383_Maximum_Performance_of_a_Team {
	
	public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
		int MOD = 1000000007;
		long res = 0;
		
		//	Tuple of efficiency, speed. Remember the performance is [ sum(speed) * min(efficiency) ]
		int[][] pairs = new int[n][2];
		for (int i = 0; i < n; ++i) {
			pairs[i][0] = efficiency[i];
			pairs[i][1] = speed[i];
		}
		
		//	Sort the efficiency decreasing order.
		Arrays.sort(pairs, (left, right)-> {
			return right[0] - left[0];
		});
		
		//	Priority queue to record all larger (or equal) efficiency workers. Sorted by speed, where smallest speed pop first
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		long pq_speed_sum = 0;
		
		for (int[] worker: pairs) {
			int currentEfficiency = worker[0];
			int currentSpeed = worker[1];
			
			//	Since we are going to add the current worker, we must ensure queue is only having K-1 elements
			if (pq.size() == k)
				pq_speed_sum -= pq.poll();
			
			//	Add current worker.
			pq_speed_sum += currentSpeed;
			pq.add(currentSpeed);
			
			//	Calculate the performance points with current worker included
			res = Math.max(res, pq_speed_sum * currentEfficiency );
		}
		return (int)(res % MOD);
    }
}
