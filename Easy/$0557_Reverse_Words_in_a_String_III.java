package Easy;

import java.util.Stack;

//https://leetcode.com/problems/reverse-words-in-a-string-iii/
/*
 * This is a String, optinally Stack problem.
 * 
 * Stack is known for its properties to reverse anything, including this case.
 * Traverse through string s, pushing non-space characters into the stack.
 * If we did encounter a space ' ', empty the stack's content into result string, and don't forget the space character too.
 * 
 * Another way of dealing with this problem with better space optimization is to simply use two pointers, one pointing at
 * the start of a word, and one at the end of word. Reverse everything between the two pointers
 */

public class $0557_Reverse_Words_in_a_String_III {
	
	// Stack solution
	public String reverseWords(String s) {
		StringBuilder res = new StringBuilder();
		Stack<Character> stk = new Stack<>();
		
		for (char c: s.toCharArray() ) {
			if (c != ' ') stk.push(c);
			else {
				while (!stk.isEmpty()) res.append(stk.pop());
				res.append(c);
			}
		}
		
		while (!stk.isEmpty()) res.append(stk.pop());
		
		return res.toString();
    }
	
	
	// Pointers solution
	public String reverseWords2(String s) {
		final int len = s.length();
		StringBuilder res = new StringBuilder();
		int l = 0, r = 0;
		
		while (l < len) {
			// Fix left pointer.
			while (l < len && s.charAt(l) == ' ') ++l;
			// Fix right pointer. It should point at ' ' or len+1 at the end.
			r = l+1;
			while (r < len && s.charAt(r) != ' ') ++r;
			
			for (int i = r-1; i >= l; --i) res.append(s.charAt(i) );
			if (r < len) res.append(' ');
			
			l = r+1;
		}
		

		return res.toString();
	}
}
