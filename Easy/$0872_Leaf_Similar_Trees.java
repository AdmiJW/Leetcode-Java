package Easy;
import java.util.LinkedList;
import java.util.Queue;

import Binary_Tree.TreeNode;

// https://leetcode.com/problems/leaf-similar-trees/
/*
 * A binary tree, depth first search problem.
 * 
 * Perform a DFS on the first tree, record all the leaves in a linear data structure like a queue.
 * Then perform a DFS on the second tree, compare the leaves with the queue. 
 */



public class $0872_Leaf_Similar_Trees {

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        Queue<TreeNode> leaves = new LinkedList<>();
        dfsAndAddLeaves(root1, leaves);
        return dfsAndCompareLeaves(root2, leaves) && leaves.isEmpty();
    }

    private void dfsAndAddLeaves(TreeNode root, Queue<TreeNode> leaves) {
        if (root == null) return;
        if (root.left == null && root.right == null) leaves.offer(root);
        dfsAndAddLeaves(root.left, leaves);
        dfsAndAddLeaves(root.right, leaves);
    }

    private boolean dfsAndCompareLeaves(TreeNode root, Queue<TreeNode> leaves) {
        if (root == null) return true;
        if (root.left == null && root.right == null)
            return (!leaves.isEmpty()) && (root.val == leaves.poll().val);

        return dfsAndCompareLeaves(root.left, leaves) && dfsAndCompareLeaves(root.right, leaves);
    }
}
