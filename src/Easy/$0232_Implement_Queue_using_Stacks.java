package Easy;

import java.util.Stack;

// https://leetcode.com/problems/implement-queue-using-stacks
/*
 * This is a stack and queue problem.
 * Stack is known to be LIFO and the application to reverse elements. 
 * 
 * Therefore if we have a queue and we push all of them into a stack, the element will be reversed,
 * and if reverse the stack again, the element will be in the same order as the queue.
 * 
 * When adding a new element, push all elements of queue stack into temp stack, then push the new element into queue stack,
 * then push all elements of temp stack into queue stack.
 * 
 * O(n) time for push, O(1) time for pop, peek and empty.
 */


public class $0232_Implement_Queue_using_Stacks {

    class MyQueue {
        Stack<Integer> temp = new Stack<>();
        Stack<Integer> queue = new Stack<>();
        
        public void push(int x) {
            // Move all elements of queue into temp
            while (!queue.isEmpty())
                temp.push(queue.pop());

            // Push x into queue
            queue.push(x);

            // Push everything back to queue
            while (!temp.isEmpty())
                queue.push(temp.pop());
        }
        
        public int pop() {
            return queue.pop();
        }
        
        public int peek() {
            return queue.peek();
        }
        
        public boolean empty() {
            return queue.isEmpty();
        }
    }

}
