package Medium;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import javafx.util.Pair;

// https://leetcode.com/problems/path-with-maximum-probability/
//
// This is a graph problem, and we can solve it with Dijkstra algorithm.
// The solution is to use a modified Dijkstra algorithm to find the maximum probability path.
// To use Dijkstra, we need to use a max heap (maximum probability) as opposed to a min heap (minimum distance).
// One advantage that we can take is that two probability multiplication is always result in a smaller number or equal to the original number.
// So, we can know that we can avoid visiting a node again if we have already visited it with a higher probability.
// The algorithm is as follows:
// 1. Build the graph with the given edges and probabilities.
// 2. Initialize the maximum probability array with 0s, and set the start node to 1.
// 3. Add the start node to the priority queue.
// 4. While the priority queue is not empty, pop the node with the maximum probability.
// 5. If the node is the end node, return the probability.
// 6. If the node is not in the graph, continue.
// 7. For each edge of the node, calculate the probability of the next node.
// 8. If the probability is greater than the maximum probability, update the maximum probability and add the node to the priority queue.
// 9. Return 0 if the end node is not reachable.

public class $1514_Path_with_Maximum_Probability {
    public double maxProbability(int n, int[][] edges, double[] succProb, int startNode, int endNode) {
        // Build graph
        Map<Integer, List<Pair<Integer, Double>>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; ++i) {
            double p = succProb[i];
            int l = edges[i][0], r = edges[i][1];
            graph.putIfAbsent(l, new ArrayList<>());
            graph.putIfAbsent(r, new ArrayList<>());
            graph.get(l).add(new Pair<>(r, p));
            graph.get(r).add(new Pair<>(l, p));
        }

        double[] maxProb = new double[n];
        maxProb[startNode] = 1d;

        // Priority queue
        PriorityQueue<Pair<Integer, Double>> pq = new PriorityQueue<>((l, r)-> {
            return r.getValue() > l.getValue() ? 1: -1;
        });
        pq.add(new Pair<>(startNode, 1d));

        // Graph exploration with Dijkstra
        while (!pq.isEmpty()) {
            Pair<Integer, Double> curr = pq.poll();
            int currNode = curr.getKey();
            double currProb = curr.getValue();

            if (currNode == endNode) return currProb;
            if (!graph.containsKey(currNode)) continue;

            for (Pair<Integer, Double> edge: graph.get(currNode)) {
                int nextNode = edge.getKey();
                double edgeProb = edge.getValue();

                if (currProb * edgeProb > maxProb[nextNode]) {
                    maxProb[nextNode] = currProb * edgeProb;
                    pq.add(new Pair<>(nextNode, maxProb[nextNode]));
                }
            }
        }
        
        return 0;
    }
}
