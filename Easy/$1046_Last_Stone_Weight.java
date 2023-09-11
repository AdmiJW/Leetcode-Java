package Easy;

import java.util.PriorityQueue;

//https://leetcode.com/problems/last-stone-weight/
/*
 * This is a Heap (Priority queue) problem.
 * Simply simulate the process of taking the 2 most heaviest stones from the existing stones and
 * crash them together. To obtain the 2 most heaviest stones, we use heap which retrieves them in O(log N) time.
 */

public class $1046_Last_Stone_Weight {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> heap = new PriorityQueue<>( (x,y)-> y - x );
        
        for (int i: stones) heap.add(i);
        
        while (heap.size() > 1) {
        	int i = heap.poll(), j = heap.poll();
        	if (i != j) heap.add( i - j );
        }
        
        return heap.size() == 0? 0: heap.poll();
    }
}
