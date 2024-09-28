package Medium;

import Linked_List.BiListNode;

// https://leetcode.com/problems/design-circular-deque

// This is a Linked List problem.
// The question description actually didn't mention about using a circular linked list, but it can be used to solve the problem.
// For insertion, we simply have to handle those cases:
//  1. If the deque is empty, we insert the first node, which links to itself.
//  2. If the deque is not empty, we append the new node after the tail and ensure the tail points to the new node.
//  3. If the deque is full, we return false.
// For deletion, we simply have to handle those cases:
//  1. If the deque is empty, we return false.
//  2. If the deque has only 1 node, we set the tail to null.
//  3. If the deque has more than 1 node, we have to update the pointers of the nodes before and after the node to delete.
// To handle this easily, I used the BiListNode class, which is a bi-directional linked list node.

public class $0641_Design_Circular_Deque {
    class MyCircularDeque {
        BiListNode tail;
        int size;
        int capacity;

        public MyCircularDeque(int k) {
            capacity = k;
            size = 0;
            tail = null;
        }
        
        public boolean insertFront(int value) {
            if (size == capacity) return false;
            
            if (isEmpty()) insertFirstNode(value);
            else appendAfterTail(value);
            
            ++size;
            return true;
        }
        
        public boolean insertLast(int value) {
            if (size == capacity) return false;

            if (isEmpty()) insertFirstNode(value);
            else {
                BiListNode node = appendAfterTail(value);
                tail = node;
            }

            ++size;
            return true;
        }
        
        public boolean deleteFront() {
            if (isEmpty()) return false;
            
            if (size == 1) tail = null;
            else {
                // The node to delete has 2 pointers pointing to it:
                // 1. The node before it (tail)'s `next`
                // 2. The node after it `prev`
                BiListNode head = tail.next;
                BiListNode nodeAfterHead = head.next;
                tail.next = head.next;
                nodeAfterHead.prev = tail;
            };

            --size;
            return true;
        }
        
        public boolean deleteLast() {
            if (isEmpty()) return false;

            if (size == 1) tail = null;
            else {
                // The node to delete has 2 pointers pointing to it:
                // 1. The node before it (tail's prev)'s `next`
                // 2. The node after it (head)'s `prev`
                BiListNode head = tail.next;
                BiListNode nodeBeforeTail = tail.prev;
                nodeBeforeTail.next = tail.next;
                head.prev = nodeBeforeTail;
                tail = nodeBeforeTail;
            }
            
            --size;
            return true;
        }
        
        public int getFront() {
            if (size == 0) return -1;
            return tail.next.val;
        }
        
        public int getRear() {
            if (size == 0) return -1;
            return tail.val;
        }
        
        public boolean isEmpty() {
            return size == 0;
        }
        
        public boolean isFull() {
            return size == capacity;
        }

        private BiListNode appendAfterTail(int value) {
            BiListNode node = new BiListNode(value);
            BiListNode head = tail.next;
            tail.next = node;
            node.next = head;
            node.prev = tail;
            head.prev = node;
            return node;
        }

        private void insertFirstNode(int value) {
            tail = new BiListNode(value);
            tail.next = tail;
            tail.prev = tail;
        }
    }
}
