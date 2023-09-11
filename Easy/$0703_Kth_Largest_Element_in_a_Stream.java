package Easy;

import java.util.PriorityQueue;

//https://leetcode.com/problems/kth-largest-element-in-a-stream/
/*
 * 	To consistently obtain the kth largest element in an array where the elements are keep added, Heap data
 * 	structure is the best choice.
 * 
 * 	Given that we need the kth largest element, we will maintain a min heap of maximum size k. If the heap already
 * 	contains exactly k elements, then the element that we peek will be the kth maximum element!
 */

public class $0703_Kth_Largest_Element_in_a_Stream {

	class KthLargest {
		final int limit;
		PriorityQueue<Integer> heap;

	    public KthLargest(int k, int[] nums) {
	        heap = new PriorityQueue<>();
	        limit = k;
	        
	        for (int i: nums) add(i);
	    }
	    
	    public int add(int val) {
	        heap.add(val);
	        if (heap.size() > limit) heap.poll();
	        return heap.peek();
	    }
	}
	
}
