package Medium;

import java.util.PriorityQueue;

// https://leetcode.com/problems/single-threaded-cpu/
/*
 * This is a heap problem.
 * 
 * Maintain two heaps, one sorted by start time, one sorted by duration. We will always pick the process with
 * shortest duration to execute. If there is no process in durationPQ, we need to wait until the next process
 * starts. If there are multiple processes with shortest duration, we will pick the one with smallest id.
 * 
 * We will keep track of the time starting from t=1. Start by adding all processes into startPQ. 
 * We will simulate the adding of processes by adding the processes from startPQ that start 
 * before current time to durationPQ. Then just select one process that is the shortest time to execute at each
 * iteration.
 * 
 * Time complexity: O(nlogn)
 * Space complexity: O(n)
 */


public class $1834_Single_Threaded_CPU {

    // Definition for a process to ease computation
    class Process {
        int id;
        int startTime;
        int duration;

        public Process(int id, int startTime, int duration) {
            this.id = id;
            this.startTime = startTime;
            this.duration = duration;
        }
    }


    public int[] getOrder(int[][] tasks) {
        final int n = tasks.length;

        // Sort by start time
        PriorityQueue<Process> startPQ = new PriorityQueue<>((x, y)-> {
            return x.startTime == y.startTime ? x.id - y.id : x.startTime - y.startTime;
        });
        // Sort by duration
        PriorityQueue<Process> durationPQ = new PriorityQueue<>((x, y)-> {
            return x.duration == y.duration ? x.id - y.id : x.duration - y.duration;
        });
        
        // Add all processes to startPQ first
        for (int i = 0; i < n; i++)
            startPQ.offer(new Process(i, tasks[i][0], tasks[i][1]));

        // Current time
        int time = 1;
        int[] res = new int[n];
        
        for (int i = 0; i < n; ) {
            // If there is no process in durationPQ, we need to wait until the next process starts
            if (durationPQ.isEmpty()) {
                Process p = startPQ.poll();
                time = Math.max(time, p.startTime);
                durationPQ.offer(p);
            }
            
            // Add all processes that start before current time to durationPQ
            while (!startPQ.isEmpty() && startPQ.peek().startTime <= time)
                durationPQ.offer(startPQ.poll());

            // Process the process with shortest duration
            Process p = durationPQ.poll();
            res[i++] = p.id;
            time += p.duration;
        }

        return res;
    }
}
