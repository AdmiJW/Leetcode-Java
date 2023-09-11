package Hard;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//https://leetcode.com/problems/reachable-nodes-in-subdivided-graph/
/*
 * This is a HARD Graph, Dijkstra Algorithm problem. (Of course I looked at solution. What you expect?)
 * 
 * The problem is much easier if we view it as weighted graph and asking for the number of nodes we can reach
 * (no subnodes). Since weights are all positive, we can use dijkstra shortest path algorithm to always pick
 * the path with most moves left.
 * Dijkstra is greedy and always lets us compute the optimal way to a node first. If a node is already explored
 * before when we are considering a path, it means the current one is not optimal and thus not even need to
 * compute.
 * 
 * Now, with the real problem, we have to realize:
 * 		>	At the end, solution will be (Number of REAL nodes covered) + (Number of non-overlap cost used)
 * where the cost is the 'subnodes' problem is talking about.
 * 
 * The number of real nodes is simply the size of the HashSet used to keep track of visited nodes. How about
 * the non-overlap cost?
 * 
 * Turns out, we could just check each of the edges at the end of algorithm:
 * Every edge, we could have covered ALL of subnodes, or only partially, from A to B and B to A.
 * >	The maximum subnodes covered in an edge is of course, the number of subnodes available in that edge.
 * 		edge[2].
 * >	Otherwise, we would see MAXIMUM OF HOW MANY MOVES LEFT WHEN WE ARRIVED AT NODE A and NODE B respectively,
 * 		and from there we'll get to know how far can we even cover.
 * 
 * 		Eg: Edge:
 * 					(A) -------- 10 ------- (B)
 * 		From dijkstra algorithm, at node A we have 4 moves left and at node B we have 3 moves left.
 * 		Then, the number of subnodes covered would be 4+3 = 7
 * 
 * 		On the other hand, if at node A we have 7 moves left and at node B we have 4 moves left,
 * 		Then, the number of subnodes covered would be 10, because we don't have 7+4 = 11 nodes, only 10 maximum.
 */

public class $0882_Reachable_Nodes_In_Subdivided_Graph {
	
	//	Dijkstra Algorithm solution
	public int reachableNodes(int[][] edges, int maxMoves, int n) {
		// Build graph
		// Maps (Node)->(Neighbors)->(Cost)
		Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
		for (int i = 0; i < n; ++i) graph.put(i, new HashMap<>());
		for (int[] edge: edges) {
			graph.get(edge[0]).put(edge[1], edge[2]);
			graph.get(edge[1]).put(edge[0], edge[2]);
		}
		// Max heap used to pick out highest cost left first
		// Stored in { costLeft, sourceNode }
		PriorityQueue<int[]> pq = new PriorityQueue<>((p,q)-> q[0] - p[0]);
		// Starting at node 0, I have maxMoves left
		pq.offer(new int[] {maxMoves, 0});
		
		// Records all the nodes that I have REALLY reached (not partially/halfway).
		// The value stores the maximum moves left when I reached the node.
		Map<Integer, Integer> reached = new HashMap<>();
		
		while (!pq.isEmpty() ) {
			int movesLeft = pq.peek()[0];
			int currNode = pq.peek()[1];
			pq.poll();
			
			// Not yet reach this node b4, so we record. If we reached the node b4, because this is dijkstra,
			// We don't even need to consider, because the previous computation must be optimal.
			if (!reached.containsKey(currNode)) {
				reached.put(currNode, movesLeft);
				
				// Explore neighbors
				for (int neighbor: graph.get(currNode).keySet()) {
					int movesLeftIfGo = movesLeft - graph.get(currNode).get(neighbor) - 1;
					if (!reached.containsKey(neighbor) && movesLeftIfGo >= 0)
						pq.add(new int[] {movesLeftIfGo, neighbor});
				}
			}
		}
		
		// First, we take into consideration of each REAL nodes we reached
		int res = reached.size();
		// Then, each node has subnodes. The max subnodes reached is edge[2], or however far the node
		// can go:
		for (int[] edge: edges) {
			int AtoB = reached.getOrDefault(edge[0], 0);
			int BtoA = reached.getOrDefault(edge[1], 0);
			res += Math.min(AtoB + BtoA, edge[2]);
		}
		return res;
    }
	
}
