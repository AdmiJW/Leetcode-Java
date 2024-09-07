package Medium;

import Linked_List.ListNode;
import Binary_Tree.TreeNode;

// https://leetcode.com/problems/linked-list-in-binary-tree/

// This is a recursion, DFS, Linked List, Binary Tree problem
// 
// The algorithm is as follows:
// 1. We have an outer DFS function that tries to search for the node in the binary tree that matches the head of the linked list.
// 2. If we find a match, we call the inner DFS function to check if the rest of the linked list is present in the binary tree.
// 3. If the inner DFS function returns true, we return true.
// 4. If the inner DFS function returns false, we continue the outer DFS function to search for the next node in the binary tree that matches the head of the linked list.

public class $1367_Linked_List_in_Binary_Tree {
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (root == null) return false;

        boolean isDfsMatch = false;
        if (head.val == root.val) isDfsMatch = dfsMatch(head, root);
        return isDfsMatch || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    public boolean dfsMatch(ListNode head, TreeNode root) {
        if (head == null) return true;
        if (root == null) return false;
        if (head.val != root.val) return false;

        return dfsMatch(head.next, root.left) || dfsMatch(head.next, root.right);
    }
}
