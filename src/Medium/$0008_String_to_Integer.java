package Medium;

//https://leetcode.com/problems/string-to-integer-atoi/
/*
 * This is a String problem, requires you to simulate the algorithm
 * 
 * My algorithm goes as follows:
 * 
 * 	1. Iterate to consume all the leading white spaces.
 *  2. Consume the sign if contains.
 *  3. Iterate to consume all the digits
 *  	> Add the digit to result.
 *  	> If goes out of bound of Integer range, return the respective value
 *  
 *  However, this problem can also be solved using Finite State Machines, but you'll need to design it and code it out
 */


public class $0008_String_to_Integer {
	
	public int myAtoi(String s) {
		final int len = s.length();
		long res = 0;
		char sign = ' ';
		int i = 0;
		
		// Consume leading spaces
		while (i < len && s.charAt(i) == ' ') ++i;
		
		// Consume sign (if contains)
		if (i < len && (s.charAt(i) == '+' || s.charAt(i) == '-')) sign = s.charAt(i++);
		
		// Consume digits. Stop if encounter non digit
		while (i < len) {
			char c = s.charAt(i);
			if (!Character.isDigit(c)) break;
			
			res = res * 10 + (c - '0');
			
			if (sign == '-' && -res <= Integer.MIN_VALUE) return Integer.MIN_VALUE;
			if (sign != '-' && res >= Integer.MAX_VALUE) return Integer.MAX_VALUE;
			
			++i;
		}
		
		if (sign == '-') res = -res;
		return (int)res;
    }
}
