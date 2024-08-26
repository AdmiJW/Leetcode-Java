package Easy;

import java.util.List;
import java.util.ArrayList;

// https://leetcode.com/problems/n-ary-tree-postorder-traversal/

// This is a tree traversal problem. We can easily solve it using recursion.
// For post-order traversal, we will visit each of the children of the current node first and then visit the current node.
// We will use a helper function to do the recursion.

public class $0590_N_ary_Tree_Postorder_Traversal {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }
    }

    public List<Integer> postorder(Node root) {
        List<Integer> l = new ArrayList<>();
        recursion(root, l);
        return l;
    }

    private void recursion(Node n, List<Integer> l) {
        if (n == null) return;
        for (Node c: n.children) recursion(c, l);
        l.add(n.val);
    }
}
