package Medium;

import java.util.PriorityQueue;

//https://leetcode.com/problems/k-closest-points-to-origin/
/*
 * 	This is a sorting problem, with variations of heap / binary search / heap
 * 
 * 	
 * 	The sorting is straightforward - Sort the array based on distance to origin, and select the front K points 
 * 	as the answer. Time is O(N log N)
 * 
 * 	However, if K is small, we could optimize by maintaining only a K sized heap. The heap will also keep data
 * 	in sorted order, but since K is small, each insert, removal of elements in heap is log K. Therefore final
 * 	time complexity is O(N log K)
 */



public class $0973_K_Closest_Points_To_Origin {
	
	// Heap solution - O(N + K log K)
	public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y)-> {
        	return distance(x) > distance(y)? -1: 1;
        });
        
        // Maintain a heap of size K
        for (int[] p: points) {
        	pq.add(p);
        	
        	if (pq.size() > k) pq.poll();
        }
        
        // Result
        int[][] ans = new int[k][2];
        for (int i = 0; i < k; ++i)
        	ans[i] = pq.poll();
        return ans;
    }
	
	
	private double distance(int[] point) {
		return Math.sqrt( point[0] * point[0] + point[1] * point[1] );
	}
}
