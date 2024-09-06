package Medium;

import java.util.Set;
import java.util.HashSet;
import Linked_List.ListNode;

// https://leetcode.com/problems/delete-nodes-from-linked-list-present-in-array/

// This is a Linked List, Hash Set problem
// For fast lookup of elements in the array, we use a HashSet to store the elements
// Then, we iterate through the linked list and remove the nodes that are present in the HashSet
// We need to handle the case where the head of the linked list is in the HashSet

public class $3217_Delete_Nodes_From_Linked_List_Present_in_Array {
    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> set = new HashSet<>();
        for (int i: nums) set.add(i);

        while (head != null && set.contains(head.val)) head = head.next;

        ListNode node = head;

        while (node != null) {
            while (node.next != null && set.contains(node.next.val)) node.next = node.next.next;
            node = node.next;
        }
        return head;
    }
}