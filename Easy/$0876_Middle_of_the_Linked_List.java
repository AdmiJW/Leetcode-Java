package Easy;

import Linked_List.ListNode;

//https://leetcode.com/problems/middle-of-the-linked-list/
/*
 * 	This is a two pointers, linked list problem.
 * 
 * 	We start with 2 pointers - one slow and one fast. At each iteration, slow pointer moves 1 node, and fast
 * 	pointer moves 2 node at a time.
 * 
 * 	Therefore, when the fast pointer had moved until the end of the linked list, the slow pointer should be pointing
 * 	at the middle of linked list.
 */


public class $0876_Middle_of_the_Linked_List {

	// Two pointers - Slow and fast pointer method
	public ListNode middleNode(ListNode head) {
        if (head == null) return head;
        
        ListNode slow = head, fast = head;
        
        while (fast.next != null && fast.next.next != null) {
        	slow = slow.next;
        	fast = fast.next.next;
        }
        
        return (fast.next == null)
    		? slow
			: slow.next;
    }
	
}
