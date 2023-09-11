package Easy;

// https://leetcode.com/problems/reverse-vowels-of-a-string/
/*
 * Easy String problem
 * 
 * You could do a two pass - One pass to record all the vowels, and second pass to replace the vowels reversely.
 * Or you could do two pointers: Left and right. Once the two pointers are both vowels, swap them.
 */


public class $0345_Reverse_Vowels_of_a_String {
	
	public String reverseVowels(String s) {
		StringBuilder sb = new StringBuilder(s);
		
		int l = 0, r = s.length() - 1;
		
		while (l < r) {
			if ( !isVowel(sb.charAt(l) ) ) ++l;
			else if ( !isVowel(sb.charAt(r) ) ) --r;
			else {
				char temp = sb.charAt(l);
				sb.setCharAt(l, sb.charAt(r));
				sb.setCharAt(r, temp);
				++l; --r;
			}
		}
		
		return sb.toString();
    }
	
	private boolean isVowel(char c) {
		c = Character.toLowerCase(c);
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}
	
}
