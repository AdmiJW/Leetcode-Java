package Medium;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//https://leetcode.com/problems/smallest-string-with-swaps/
/*
 * This is a graph, DFS, optionally Union find (Disjoint set) problem. Quite hard to implement
 * 
 * Prior to discussing the algorithm, you must observe a key point from the problem: Pairs can be used infinitely.
 * This indicates that characters at any of the connected pairs can be moved freely! See:
 * 
 * 		Pairs: [0,1],[1,2],[2,3]
 * 	
 * In the above case, you shall realize that characters at (0,1,2,3) can be moved freely throughout among themselves!
 * The character at index 0 can be moved to index 1, 2 or 3 with no restriction!
 * 
 * However, this rule does have a 'locality' to it. See:
 * 		
 * 		Pairs: [0,1],[1,2],[3,4]
 * 
 * Although character at (0,1,2) can move freely among themselves, character at (3,4) cannot move to (0,1,2)!
 * (Hopefully you see why with the disjoint set now?)
 * 
 * With this being clear, you can realize:
 * 	- If we have a list of indices and characters within a "local" group (Eg: indices and characters of (0,1,2) in above
 *    case), we can easily sort them to obtain how they should be rearranged to obtain lowest lexicographical order!
 *    
 * -----------------------------------------------------------------------
 * 
 * For the graph DFS solution, simply build a adjacency list out of the pairs first. Then, using DFS, we can easily
 * obtain every indices and characters within a local group. Then we would sort them, and rearrange the characters
 * accordingly in the result string.
 */

public class $1202_Smallest_String_With_Swaps {
	
	public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
		final int len = s.length();
		
		StringBuilder sb = new StringBuilder(s);
		List<Integer>[] adj = new ArrayList[len];
		boolean[] visited = new boolean[len];
		
		for (int i = 0; i < len; ++i) adj[i] = new ArrayList<>();
		
		// Create adjacency list
		for (List<Integer> p: pairs) {
			adj[p.get(0)].add( p.get(1) );
			adj[p.get(1)].add( p.get(0) );
		}
		
		// For each of the 'local' groups
		for (int i = 0; i < len; ++i) {
			if (visited[i] || adj[i].isEmpty() ) continue;
			
			// DFS to find out every character included in the local group
			List<Integer> indices = new ArrayList<>();
			List<Character> ch = new ArrayList<>();
			dfs(s, i, visited, indices, ch, adj );
			
			// Sort them to obtain how the local group should be arranged to lowest lexicographical
			Collections.sort(indices);
			Collections.sort(ch);
			
			// Arrange them back 
			for (int j = 0; j < indices.size(); ++j)
				sb.setCharAt( indices.get(j), ch.get(j) );
		}
		
		return sb.toString();
    }
	
	
	
	// Simple dfs to explore the graph, adding indices and characters
	private void dfs(String s, int i, boolean[] visited, List<Integer> indices, List<Character> ch, List<Integer>[] adj) {
		if (visited[i]) return;
		
		indices.add(i);
		ch.add(s.charAt(i));
		visited[i] = true;
		
		for (int next: adj[i]) dfs(s, next, visited, indices, ch, adj);
	}
	
}
