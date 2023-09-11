package Medium;
import java.util.ArrayDeque;
import java.util.Deque;

import Binary_Tree.TreeNode;

//https://leetcode.com/problems/maximum-width-of-binary-tree/
/*
*	This is a Tree, Breadth first search, Doubly linked list problem
*	
*	First, you need to realize the way to index each node in one level:
*		Left node = currIndex * 2
*		Right node = currIndex * 2 + 1
*	Results in:
* 
*					(0)
*				(0)		(1)
*		(0)	(1)				(2)	(3)
*	(0) (1) (2) (3)		(4) (5) (6) (7)
* 
*	With this, seems like getting the width is not so hard! In our BFS, we simply store the nodes along with their computed
*	indices. Then, if we are using a data structure that can retrieve both ends in O(1) time, we can quickly compute the width
*	of one level.
* 
*	However, problem arises when the tree is long: Consider a tree where there is always a right node - The indices increases
*	exponentially! 
*	One solution is to "shift" the indices according to the leftmost node. Consider:
* 
*					(0)
*						(1)
*						(2)	(3)
*								(6) (7)
* 
*	You can see, even if (1), (2) and (6) are leftmost node in the level, they still increase at an exponential rate, and that's
*	not good at all.
*	One remedy is to "shift" them! Before iteration of one level, obtain the leftmost node's index as the shift value, so that
*	leftmost node will become index 0:
* 
*					(0)
*						(0)
*						(0)	(1)
*							(0)	(1)
*/


public class $0662_Maximum_Width_of_Binary_Tree {
	
	class NodeIndex {
		TreeNode node;
		long index;
		
		public NodeIndex(TreeNode node, long index) {
			this.node = node;
			this.index = index;
		}
	}
	
	public int widthOfBinaryTree(TreeNode root) {
		Deque<NodeIndex> level = new ArrayDeque<>();
		long res = 0;

		level.offer( new NodeIndex(root, 0) );

		while (!level.isEmpty()) {
			// Record size.
			res = Math.max(
				res,
				level.peekLast().index - level.peekFirst().index + 1
			);
			
			// We have to use shift to prevent overflow
			long shift = level.peekLast().index;

			// Level traversal.
			for (int i = level.size(); i > 0; --i) {
				NodeIndex pop = level.poll();

				if (pop.node.left != null)
					level.offer( new NodeIndex( pop.node.left, (pop.index - shift) * 2 ));
				if (pop.node.right != null)
					level.offer( new NodeIndex( pop.node.right, (pop.index - shift) * 2 + 1 ));
			}
		}


		return (int)res;
    }
}
