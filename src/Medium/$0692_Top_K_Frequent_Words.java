package Medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

// https://leetcode.com/problems/top-k-frequent-words/
/*
 * 	This is a min heap problem.
 * 
 * 	First, obtain the frequencies of a string in O(N) time using a HashMap.
 * 
 * 	Then, we have to sort them based on frequency first in descending order, then ascending with lexicographical.
 * 	That would take O(N log N) time where N is number of unique words.
 * 
 * 	Since K can be less than N by, a lot, we can reduce the time complexity to O(N log K) with min heap. The idea is the same:
 * 	The min heap will poll out the least frequency words, then the ones with greater lexicographical. Push all the unique strings
 * 	one by one into the heap, and poll once the size exceeds k.
 * 
 * 	At the end, what's left in the heap is most frequent k strings. We can poll them out and reverse the order to get the final
 * 	solution.
 */

public class $0692_Top_K_Frequent_Words {

	public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap<>();
        
        for (String s: words) 
        	count.put( s, count.getOrDefault(s, 0) + 1 );
     
        // Min heap
        PriorityQueue<String> pq = new PriorityQueue<>((x,y)-> {
        	// Pop y first if x is less lexicographically, given frequency is same for x and y
        	if (count.get(x) == count.get(y)) return y.compareTo(x);
        	// Less frequency ones goes first, so be popped first
        	return count.get(x) - count.get(y);
        });
        
        for (String s: count.keySet()) {
        	pq.add(s);
        	if (pq.size() > k) pq.poll();
        }
        
        List<String> res = new ArrayList<>();
        while (!pq.isEmpty()) res.add(pq.poll());
        Collections.reverse(res);
        return res;
    }
	
}	
