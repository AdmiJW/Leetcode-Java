package Easy;
import Binary_Tree.TreeNode;

//https://leetcode.com/problems/construct-string-from-binary-tree/
/*
 * 	This is a DFS problem - Preorder traversal best solved using recursion
 * 
 * 	The idea is that given a node, we represent in string with parenthesis like so:
 * 		root(left subtree)(right subtree)
 * 	Where left subtree and right subtree itself is also a parenthesis expression just like the one above.
 * 
 * 
 * 	Therefore we create a recursive function. It shall append the root value first, then check:
 * 
 * 	>	If the node is leaf node, we are done and shall not add any unnecessary parenthesis.
 * 	>	Otherwise, we MUST put a parenthesis for left subtree. If we omit it, right subtree would be viewed as left subtree.
 * 	>	Check if right subtree is null. If true, omit the parenthesis altogether.
 */

public class $0606_Construct_String_From_Binary_Tree {
	
	public String tree2str(TreeNode root) {
		StringBuilder res = new StringBuilder();
		recurse(root, res);
		return res.toString();
    }
	
	public void recurse(TreeNode root, StringBuilder str) {
		str.append(root.val);
		
		if (root.left == null && root.right == null) return;
		
		str.append('(');
		if (root.left != null) recurse(root.left, str);
		str.append(')');
		
		if (root.right != null) {
			str.append('(');
			recurse(root.right, str);
			str.append(')');
		}
	}
}
