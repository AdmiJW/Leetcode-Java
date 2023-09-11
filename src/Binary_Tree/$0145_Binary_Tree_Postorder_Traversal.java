package Binary_Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

//https://leetcode.com/explore/learn/card/data-structure-tree/134/traverse-a-tree/930/

/*
 * 	Binary Tree has 3 types of DFS traversals. Postorder Traversal is one of the traversal ways which traverse the binary
 * 	tree in the order (Left -> Right -> Root).
 * 	This pattern ensures whatever job is done with left subtree, then the right subtree, then only we return to the
 * 	parent.
 * 
 * 	Implementing with recursion is easier. Just call the function of the left subtree, followed by right subtree,
 * 	then only we perform the job we want, and return to upper recursion stack.
 * 
 * 	=============================================================================================================
 * 
 * 	The challenge comes when we try to implement a iterative method. First we'll introduce the 2 Stacks method.
 * 
 * 	This 2 Stacks method use the property of reversing a list using Stack. If we say push 1 -> 2 -> 3 into a stack,
 * 	when popped, the list will be reversed. 3 -> 2 -> 1
 * 
 * 	This method is more intuitively understandable. THe algorithm goes like this:
 * 		Push root into stack1
 * 		while stack1 is not empty:
 * 			pop from stack1
 * 			Push popped node into stack2
 * 			Push popped node's left into stack1 if exists
 * 			Push popped node's right into stack1 if exists
 * 
 * 	At the end, when we pop everything from stack2, it will be in postorder traversal equivalent.
 * 
 * 	The reason why this works is because
 * 		>	Popping from stack1, we are always first pushing the parent node into stack2. Due to LIFO, the parent node will
 * 			be the last to be popped from the stack2.
 * 		>	We push left node first then right node into the stack1. Due to this reason, the node that get popped
 * 			from stack1 is the right node first, and get added into stack2 first compared to the left node. Due to LIFO,
 * 			the left node will be popped first from stack2
 * 
 * 	===============================================================================================================
 * 
 * 	One stack method is the most challenging to come up with a solution with. It requires the following strategy:
 * 		>	Keep adding the node into the stack, and traversing into the left subtree.
 * 		>	Eventually we will reach at the null, and the stack top is the last node of left subtree.
 * 		>	When that occurs, we will see (peek) if that node has a right subtree. If it does, set pointer to its right
 * 			subtree and repeat above steps, without popping the peeked node
 * 		>	We will have a last pointer which also points to the LAST NODE RECORDED. In above step when the node is
 * 			peeked, also check if the last node is the peeked node's right. If it does, that means although the peeked
 *			node has a right subtree, the right subtree had already been fully explored.
 *			In both cases, the peeked node can be popped and recorded. Remember to set the last node as the popped node,
 *			and set current node as null so next iteration will pop node from Stack.
 */

public class $0145_Binary_Tree_Postorder_Traversal {
	
	//========================================
	//	RECURSIVE METHOD
	//========================================
	void postOrderRecursion( TreeNode root ) {
		List<Integer> li = new ArrayList<>();
		recurse(root, li);
		System.out.println( li );
	}
	private void recurse(TreeNode node, List<Integer> li) {
		if (node == null) return;
		
		recurse(node.left, li);
		recurse(node.right, li);
		
		li.add( node.val );
	}
	
	
	
	//========================================
	//	2 Stacks METHOD
	//========================================
	void postOrder2Stacks( TreeNode root ) {
		if (root == null) return;
		
		List<Integer> li = new ArrayList<>();
		Deque<TreeNode> stack1 = new ArrayDeque<>();
		Deque<TreeNode> stack2 = new ArrayDeque<>();
		
		stack1.push( root );
		while ( !stack1.isEmpty() ) {
			TreeNode popped = stack1.pop();
			stack2.push( popped );
			if (popped.left != null ) stack1.push( popped.left );
			if (popped.right != null ) stack1.push( popped.right );
		}
		
		while ( !stack2.isEmpty() ) {
			li.add( stack2.pop().val );
		}
		System.out.println( li );
	}
	
	
	//========================================
	//	1 Stacks METHOD
	//========================================
	void postOrder1Stack( TreeNode root ) {
		if (root == null) return;
		
		List<Integer> li = new ArrayList<>();
		Deque<TreeNode> stack = new ArrayDeque<>();
		TreeNode curr = root;
		TreeNode last = null;
		
		//	Iterate as long as the current is not null, or the stack is not empty
		while ( curr != null || !stack.isEmpty() ) {
			//	If current is not null, push this node and traverse deep into left subtree.
			if ( curr != null ) {
				stack.push( curr );
				curr = curr.left;
			} 
			//	Otherwise current is null, we may have reached max depth of one left subtree.
			else {
				//	Check the previous node if it has a right subtree
				curr = stack.peek();
				//	No right subtree. It is time to print this node out
				if (curr.right == null || last == curr.right) {
					stack.pop();
					li.add( curr.val );
					last = curr;
					curr = null;
				} 
				//	Previous node has a right subtree! Go traverse the right subtree before this parent node!
				else {
					curr = curr.right;
				}
			}
		}
		
		System.out.println( li );
	}
	
	
}
