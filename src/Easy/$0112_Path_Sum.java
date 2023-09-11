package Easy;
import Binary_Tree.TreeNode;

//https://leetcode.com/problems/path-sum/
/*
 * 	A simple Tree DFS problem.
 * 
 * 	Every subtree is simply a subproblem. We have to minus the node.val from targetSum every node, and see if it
 * 	can be reduced to 0 at a leaf node.
 */

public class $0112_Path_Sum {
	
	public boolean hasPathSum(TreeNode root, int targetSum) {
		if (root == null) return false;
		
		int newTarget = targetSum - root.val;
		if (root.left == null && root.right == null) return newTarget == 0;
		return hasPathSum(root.left, newTarget) || hasPathSum(root.right, newTarget);
    }
	
}
