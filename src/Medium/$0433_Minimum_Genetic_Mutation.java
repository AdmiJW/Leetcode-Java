package Medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/minimum-genetic-mutation/
/*
 * 	This is a BFS, HashMap/HashSet problem.
 * 
 * 	Starting from the start string, we will be generating all of its possible mutations. All mutations
 * 	that are inside the bank will be pushed to the queue for next iteration.
 * 
 * 	Since we are using Breadth First Search, we can guarantee that whenever we found the mutation string matches
 * 	end, it is always the minimum number of mutations for the BFS depth. 
 * 
 * 	Generating all mutations for a string takes approximately O(SE) where S = string length, E = number of genetic elements (ACGT)
 * 	At worst case we will generate all mutations B times, where B = bank size.
 * 
 * 	Thus time complexity O(BSE)
 */


public class $0433_Minimum_Genetic_Mutation {
	
	public int minMutation(String start, String end, String[] bank) {
        Deque<String> queue = new ArrayDeque<>();
		Set<String> visited = new HashSet<>();
		
		final char[] elem = {'A', 'C', 'G', 'T'};
		
		for (String s: bank) visited.add(s);
		visited.remove(start);
		queue.offer(start);
		
		for (int depth = 0; !queue.isEmpty(); ++depth) {
			
			for (int layerSize = queue.size(); layerSize > 0; --layerSize) {
				String toMutate = queue.poll();
				if (toMutate.equals(end)) return depth;
				
				StringBuilder sb = new StringBuilder(toMutate);
				
				for (int i = 0; i < toMutate.length(); ++i) {
					for (char c: elem) {
						sb.setCharAt(i, c);
						String mutated = sb.toString();
						
						if (visited.contains(mutated)) {
                            queue.offer(mutated);
							visited.remove(mutated);
						}
					}
					
					sb.setCharAt(i, toMutate.charAt(i));
				}
			}
		}
		
		return -1;
    }
	
}
