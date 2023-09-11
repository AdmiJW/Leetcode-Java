package Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

//https://leetcode.com/problems/network-delay-time/
/*
 * This is a Dijkstra's algorithm problem (Shortest path).
 * 
 * From N nodes, every node has its definite answer for the minimum cost to reach it from source node k.
 * From the N answers, select the maximum.
 * 
 * To find out the minimum cost to reach the nodes, use shortest path algorithm, like Dijkstra's algorithm.
 * 
 * We also have to keep track of how many nodes we have explored. If there are some left out nodes that are
 * unreachable, we will be returning -1 instead.
 */

public class $0743_Network_Delay_Time {
	
	public int networkDelayTime(int[][] times, int n, int k) {
		// Graph construction ( Node -> Neighbors, cost )
		Map<Integer, List<Integer>> neighbors = new HashMap<>();
		Map<Integer, List<Integer>> costs = new HashMap<>();
		
		for (int i = 1; i <= n; ++i) {
			neighbors.put(i, new ArrayList<>());
			costs.put(i, new ArrayList<>());
		}
		
		for (int[] i: times) {
			neighbors.get( i[0] ).add( i[1] );
			costs.get( i[0] ).add( i[2] );
		}
		
		
		// Dijkstra
		boolean[] visited = new boolean[n + 1];
		int covered = 0;
		int res = -1;
		// [ destination node, cost ]
		PriorityQueue< int[] > pq = new PriorityQueue<>((l, r)->  {
			return l[1] - r[1];
		});
		
		pq.add( new int[] {k, 0} );
		
		while (!pq.isEmpty() ) {
			int[] c = pq.poll();
			if ( visited[c[0]] ) continue;
			
			++covered;
			res = c[1];
			visited[c[0]] = true;
			
			for (int i = 0; i < neighbors.get(c[0]).size(); ++i ) {
				pq.add( new int[] {
					neighbors.get(c[0]).get(i),
					c[1] + costs.get(c[0]).get(i)
				});
			}
		}
		
		
		return (covered == n)? res: -1;
    }
}
