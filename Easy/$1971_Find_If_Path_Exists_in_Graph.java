package Easy;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

// https://leetcode.com/problems/find-if-path-exists-in-graph/
/*
 * This is a graph problem.
 * Simply construct the graph and do BFS search starting from source node.
 * Once we find the destination node, return true.
 * If we exhaust all nodes and cannot find the destination node, return false.
 */

public class $1971_Find_If_Path_Exists_in_Graph {

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // Construct graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }

        // BFS search
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            if (node == destination) return true;
            if (visited[node]) continue;

            visited[node] = true;
            for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                if (!visited[neighbor]) queue.offer(neighbor);
            }
        }

        return false;
    }
}
