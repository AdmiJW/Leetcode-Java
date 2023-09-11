package Medium;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//https://leetcode.com/problems/split-array-into-consecutive-subsequences/
/*
 * 	This is a HashMap, Greedy problem
 * 
 * 	One solution is to simulate the behavior of the problem. For each element e, we can either extend an existing
 * 	subsequence [..., e], or start a new subsequence of length 1 consisting of only e itself [e].
 * 
 * 	This is the greedy behavior of the problem. We try to extend existing subsequence first, then only if we have no
 * 	choice, we create a new subsequence.
 * 
 * 	At the end, check if all existing subsequences are greater than 3. 
 * 
 *  Therefore, we could use a HashMap, where table[i] maps to a collection of sequences that ends with element i.
 *  If we were to extend an existing subsequence, we would greedily want to extend the shortest one. Therefore,
 *  table[i] should be a Heap storing the length of subsequences, where when we peek() or poll(), will return the
 *  shortest subsequence available to extend.
 *  
 *  ============================================================
 *  
 *  A more optimal solution is to extend the above idea. For each element e:
 *  
 *  	1. If e can extend an existing subsequence, then use it to extend.
 *  	2. Else, e should be able to start a new subsequence of at least length 3: [e, e+1, e+2]
 *  	3. Otherwise, e is left alone and thus the problem fails.
 *  
 *  Due to case (2) where we have to know if e+1 and e+2 is available, we have to initialize a frequency table,
 *  vacant[i] that maps to number of element i that are not used in forming subsequences yet.
 *  
 *  The rest would be to just implement the above logic. This time, Time/space complexity is reduced to O(N),
 *  and allows for early return once an element e is detected cannot be used in forming subsequences. 
 */

public class $0659_Split_Array_Into_Consecutive_Subsequences {

	// HashMap + PriorityQueue solution. Slow.
	public boolean isPossible(int[] nums) {
		// Stores existing sequences in HashTable by last element
		// Each HashTable entry stores collection of sequences (as length only) in ascending order
		Map<Integer, PriorityQueue<Integer>> table = new HashMap<>();
		
		// I use a counter to count number of subsequences that below length 3
		int counter = 0;
		
		for (int i: nums) {
			if ( !table.containsKey(i) ) table.put(i, new PriorityQueue<>() );
			
			// Can extend an existing subsequence
			if (table.get(i-1) != null && !table.get(i-1).isEmpty() ) {
				int prevLen = table.get(i-1).poll();
				table.get(i).offer(prevLen + 1);
				
				if (prevLen + 1 == 3) --counter;
			}
			// Cannot extend. Start a new subsequence
			else {
				table.get(i).offer(1);
				++counter;
			}
		}
		
		return counter == 0;
    }
	
	
	
	// O(N) HashMap solution
	public boolean isPossible2(int[] nums) {
		// vacant[i] maps to number of i that we haven't used up
		Map<Integer, Integer> vacant = new HashMap<>();
		// seq[i] maps to number of subsequences that end with i
		Map<Integer, Integer> seq = new HashMap<>();
		
		// Add all numbers to vacant first
		for (int i: nums) vacant.put(i, vacant.getOrDefault(i, 0) + 1);
		
		// Iterate again. All element must either:
		//		1. Extend one of previous existing subsequences. (Priority)
		//		2. Start a new subsequence of at least length 3
		// If both cases cannot be satisfy, then immediately return false
		for (int i: nums) {
			// Check if current i already used up by subsequences
			if ( vacant.get(i) == 0 ) continue;
			vacant.put(i, vacant.get(i) - 1);
			
			// Case 1
			if ( seq.getOrDefault(i-1, 0) > 0 ) {
				seq.put( i, seq.getOrDefault(i, 0) + 1);
				seq.put( i-1, seq.get(i-1) - 1);
			}
			// Case 2
			else if ( vacant.getOrDefault(i+1, 0) > 0 && vacant.getOrDefault(i+2, 0) > 0) {
				vacant.put( i+1, vacant.get(i+1) - 1);
				vacant.put( i+2, vacant.get(i+2) - 1);
				seq.put( i+2, seq.getOrDefault(i+2, 0) + 1);
			}
			else return false;
		}
		
		// Successfully used all elements in sequence of length 3 or more.
		return true;
	}
	
}
