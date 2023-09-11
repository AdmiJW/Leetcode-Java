package Medium;
import Binary_Tree.TreeNode;

//https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
/*
 * This is a binary tree problem, searching algorithm using depth first search
 * 
 * Given a current node, node p and node q. We have to search for their lowest common ancestor.
 * The idea is, we could call recursion to search in the left subtree and right subtree for node p and q.
 * If the search is successful, it should inform back to this parent node that the search is fruitful.
 * 
 * Thus, let's see possible cases:
 * 	>	Both left and right subtree return result. This means current node is the lowest common ancestor.
 * 		Therefore return current node
 * 	>	If the current node is p or q, then we have following case:
 * 		- Another node is inside one of the current node subtree. In this case, current node is lowest common
 * 		  ancestor
 * 		- Another node is not inside subtree. But we still need to inform parent about the search is fruitful
 * 
 * 		Either case, we have to return current node, immediately.
 * 
 * 	>	If only one side return a non null result, return that non null node returned. Else return null.
 */

public class $0236_Lowest_Common_Ancestor_of_a_Binary_Tree {
	
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
		//	Current node is one of p or q. Immediately return.
        if (root == p || root == q) return root;
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (left != null && right != null) return root;
        //	Return the non-null result, if any
        return left != null? left: right;
    }
	
}
