package Medium;

import java.util.PriorityQueue;

// https://leetcode.com/problems/remove-stones-to-minimize-the-total/description/
/*
 * This is simply a heap problem.
 * 
 * We can use a max heap to store the piles. Since we want to minimize the stones, we will always pick the
 * largest pile to halve it, thus some elements of Greedy algorithm. Do this k times.
 */



public class $1962_Remove_Stones_to_Minimize_the_Total {
    public int minStoneSum(int[] piles, int k) {
        // Create a max heap
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int i: piles) pq.offer(i);
        
        // Halve stone k times
        while (k-- > 0) {
            int cur = pq.poll();
            pq.offer(cur - cur / 2);
        }

        // Return sum
        int sum = 0;
        while (!pq.isEmpty()) sum += pq.poll();
        return sum;
    }
}
