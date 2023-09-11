package Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/
/*
 * This is a recursion DFS, Tree problem.
 * 
 * Build the tree first. Then, we would maintain a global variable `time` to represent the minimum time to collect
 * all apples in the tree.
 * 
 * Observe: Let say we are on node N and it has parent node P. It is mandatory that we arrive at node N from node P
 * using 2 times: Once (P -> N) and once (N -> P), since we must return to root node as stated in the problem.
 * 
 * So, we can use recursion to solve this problem. On each node, we try to solve whether we have a need to arrive
 * at this node from the parent node, and if we do, we need to do (time := time + 2) to account for the arriving
 * trip and return trip.
 * As for whether we need to arrive at the node, depends on whether the node has apple, or any of its child has
 * apples or not. 
 */




class $1443_Minimum_Time_to_Collect_All_Apples_in_a_Tree {

    public int time = 0;

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        // Build tree
        Map<Integer, List<Integer>> tree = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        for (int[] edge : edges) {
            tree.putIfAbsent(edge[0], new ArrayList<>());
            tree.putIfAbsent(edge[1], new ArrayList<>());
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }

        // Recurse
        visited.add(0);
        for (int child: tree.get(0)) recurse(child, tree, visited, hasApple);

        return time;
    }

    public boolean recurse(int n, Map<Integer, List<Integer>> tree, Set<Integer> visited, List<Boolean> hasApple) {
        boolean res = hasApple.get(n);
        visited.add(n);

        for (int child: tree.get(n)) {
            if (!visited.contains(child))
                res |= recurse(child, tree, visited, hasApple);
        }

        if (res) time += 2;
        return res;
    }


}