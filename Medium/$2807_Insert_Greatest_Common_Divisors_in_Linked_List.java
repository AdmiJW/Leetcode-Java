package Medium;

import Linked_List.ListNode;

// https://leetcode.com/problems/insert-greatest-common-divisors-in-linked-list

// This is a linked list, math problem.
// For every node in the provided linked list, as long as the node has a next node, we will calculate the greatest common divisor of the current node and the next node.
// Then we will insert a new node between the current node and the next node with the value of the greatest common divisor.
// The common greatest divisor will be calculated using the Euclidean algorithm, which takes O(log(min(a, b))) time complexity.

public class $2807_Insert_Greatest_Common_Divisors_in_Linked_List {
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode curr = head;

        while (curr != null && curr.next != null) {
            int val = gcd(curr.val, curr.next.val);
            ListNode next = curr.next;
            ListNode node = new ListNode(val, next);
            curr.next = node;
            curr = next;
        }

        return head;
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
