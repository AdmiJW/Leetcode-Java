package Medium;

//https://leetcode.com/problems/count-and-say/
/*
 * 	This is a recursion problem.
 * 
 * 	First, to parse a digit string into (count + digit) form, simply run a linear scan and keep
 * 	track of the count of the current running digit. Once the digit changes, append the (count + digit)
 * 	into the result string and reset the counter.
 * 
 * 	For a function call to countAndSay(n), we have to get the string first from countAndSay(n-1), which
 * 	base case is when n=1
 * 
 * 	Of course, recursion is entirely optional as you can simply use a loop and start from n=1 to n
 */

public class $0038_Count_and_Say {
	
	public String countAndSay(int n) {
		if (n == 1) return "1";
		
		String str = countAndSay(n-1);
		return getString(str);
    }
	
	
	private String getString(String s) {
		int curr = s.charAt(0) - '0';
		int count = 0;
		StringBuilder sb = new StringBuilder();
		
		for (char c: s.toCharArray()) {
			int d = c - '0';
			
			if (d != curr) {
				sb.append(count + "" + curr);
				curr = d;
				count = 1;
			} 
			else ++count;
		}
		
		sb.append(count + "" + curr);
		return sb.toString();
	}
}
