package Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/
/*
 * This is a tree, recursion DFS problem.
 * 
 * Since the labels are only lowercase alphabet letters of 26 characters, we can simply do recursion and query
 * the frequency of each letters of the subtree. Then using that information to solve the original problem of
 * how many nodes in subtree that has the same label.
 */




class $1519_Number_of_Nodes_in_the_Sub_Tree_With_the_Same_Label {

    public int[] res;

    public int[] countSubTrees(int n, int[][] edges, String labels) {
        res = new int[n];
        
        // Build tree
        Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int[] edge: edges) {
            tree.putIfAbsent(edge[0], new ArrayList<>());
            tree.putIfAbsent(edge[1], new ArrayList<>());
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }

        // Visited record
        Set<Integer> visited = new HashSet<>();

        recurse(0, labels, tree, visited );
        return res;
    }

    public int[] recurse(int n, String labels, Map<Integer, List<Integer>> tree, Set<Integer> visited) {
        int i = labels.charAt(n) - 'a';
        visited.add(n);

        int[] count = new int[26];
        ++count[ i ];

        for (int child: tree.get(n)) {
            if (visited.contains(child)) continue;
            int[] childCount = recurse(child, labels, tree, visited);
            for (int c = 0; c < 26; ++c) count[c] += childCount[c];
        }

        res[ n ] = count[ i ];
        return count;
    }
}