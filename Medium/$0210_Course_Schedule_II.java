package Medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

//https://leetcode.com/problems/course-schedule-ii/

/*
 * 	This question involves directed graphs which we have to topologically sort it. (Meaning, return an array of nodes which the nodes are
 * 	sorted in such a way that can be think of having preconditions, some node has to be visited first before visiting this node.
 * 
 * 	This can be solved using Topological sort algorithm (For more info, see Topics/Algorithms/Graph_Topological_Sort
 */

public class $0210_Course_Schedule_II {
	
//	public static int[] findOrder(int numCourses, int[][] prerequisites) {
//		
//		Map<Integer, List<Integer> > graph = new HashMap<>();
//		Set<Integer> visited = new HashSet<>();
//		Stack<Integer> sorting = new Stack<>();
//		
//		for (int[] pair: prerequisites) {
//			graph.putIfAbsent(pair[1], new LinkedList<>() );
//			graph.get(pair[1] ).add(pair[0] );
//		}
//		
//		for (int node = 0; node < numCourses; node ++ ) {
//			if (!recurse(node, graph, visited, sorting, new HashSet<>() ) ) return new int[] {};
//		}
//		
//		int[] sorted = new int[numCourses];
//		for (int i = 0; i < numCourses; i ++ ) {
//			sorted[i] = sorting.pop();
//		}
//		return sorted;
//	}
//	
//	
//	private static boolean recurse(int node, Map<Integer, List<Integer>> graph, Set<Integer> visited, Stack<Integer> sorting, Set<Integer> cycle) {
//		if (cycle.contains(node) ) return false;
//		if (visited.contains(node) ) return true;
//		
//		visited.add(node);
//		cycle.add(node);
//		
//		for (int next: graph.getOrDefault(node, new LinkedList<>() ) ) {
//			if (!recurse(next, graph, visited, sorting, cycle) ) return false;
//		}
//		sorting.push(node);
//		cycle.remove(node);
//		return true;
//	}
	
	//-----------------------------------------------------------------------------------------
	
	public static int[] findOrder(int numCourses, int[][] prerequisites) {
		HashMap<Integer, Set<Integer>> map = new HashMap<>();
		int[] incoming = new int[numCourses];
		
		// Construction of graph: { node -> set of neighbors }
		for (int[] pairs: prerequisites) {
			map.putIfAbsent( pairs[1] , new HashSet<>() );
			map.get( pairs[1]).add(pairs[0] );
			incoming[pairs[0] ] ++;
		}
		
		Queue<Integer> queue = new LinkedList<>();
		
		// The ones we'll start exploring is the one without any prerequisites
		for ( int i = 0; i < numCourses; i ++ )
			if (incoming[i] == 0 ) queue.add(i);
		
		int[] res = new int[numCourses];
		int i = 0;
		
		while (!queue.isEmpty() ) {
			int polled = queue.poll();
			// Explore neighbors
			for (int next: map.getOrDefault(polled, new HashSet<>() ) ) {
				// Only explore if after deducting the inbound edge, we get in-degree of 0.
				if ( --incoming[next] == 0 )
						queue.add(next);
			}
			res[i++] = polled;
		}
		
		return (i < numCourses)? new int[] {}: res;
	}
	
	
}
