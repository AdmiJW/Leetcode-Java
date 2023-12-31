package Medium;
import Linked_List.ListNode;

//https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
/*
 * 	This is a linked list problem.
 * 
 * 	The iterative way is, we are going to iterate through the linked list, checking if each node is a duplicate or
 * 	not. Verified by checking its previous node (if any) and next node (if any)
 * 	If it is a duplicate, then skip this node. Don't append it into the new linked list
 *  Otherwise, append it to tail of new linked list, update the tail and continue on with next node
 *  
 *  -----------------------------------------------------------
 *  
 *  For recursion, we are going to check if the head node argument is a duplicated node or not. This is done by checking
 *  the head node's next (if any).
 *  
 *  The recursion call will return the Linked list which doesn't contain any duplicate node starting from this head node
 *  Let's see how:
 *  
 *  >	If the head node is NOT a duplicate node, surely the  return value shall be
 *  			(HEAD) -> ( recurse(HEAD.next) )
 *  
 *  >	Otherwise if the head node is duplicated, then skip over all the duplicate nodes that has same value as the head
 *  	node. Then, we eventually will stop at either the end of linked list, or the first node that isn't having the same
 *  	value as head node.
 *  
 *  	Since we are unable to determine whether the node we stopped at is also a duplicate or not, we are going to call
 *  	recursion on THAT node, and return the linked list returned by recursion call, which is the linked list that
 *  	contain no duplicates
 *  	
 */


public class $0082_Remove_Duplicates_From_Sorted_List_II {
	
	//	Iterative solution using dummy node
	public ListNode deleteDuplicates(ListNode head) {
		ListNode dummy = new ListNode();
		ListNode tail = dummy;
		
		ListNode prev = null;
		while (head != null) {
			//	If previous and next node has different values (or is null), then this is unique node
			if ( (prev == null || prev.val != head.val) && (head.next == null || head.next.val != head.val) ) {
				tail.next = head;
				tail = tail.next;
			}
			prev = head;
			head = head.next;
		}
		
		//	The last node may not yet been disconnected
		tail.next = null;
		return dummy.next;
	}
	
	
	//	Recursive solution
	public ListNode deleteDuplicates2(ListNode head) {
		if (head == null || head.next == null) return head;
		
		//	Duplicate node. Skip over the duplications
		if (head.val == head.next.val) {
			ListNode skip = head;
			while (skip != null && skip.val == head.val) skip = skip.next;
			return deleteDuplicates2(skip);
		} 
		//	Head is unique. Recurse on next node and append to head's next
		else {
			head.next = deleteDuplicates2(head.next);
			return head;
		}
	}
}
