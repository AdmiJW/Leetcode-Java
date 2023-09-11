package Medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

// https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words/
/*
 * This is a Greedy, HashMap counting problem.
 * 
 * First of all, count the number of occurrences of words into a frequency table/Hashmap.
 * 
 * We will handle 2 cases: words that are symmetric like 'aa', and those who are unsymmetric 'ab'
 * 
 * -----
 * 
 * For unsymmetric its simple: If we want to use one occurrence of 'ab', we must append 'ba' at the other end.
 * Therefore for each of possible word w, we can make our result string longer by:
 * 
 * 		min(
 * 			frequency( w ),
 * 			frequency( reverse(w) )
 * 		) * 4
 * 
 * -----
 * 
 * For symmetric ones, it can be ambiguous in 2 ways:
 * 		> If we have 2 'aa', we can treat them like in unsymmetric case: append one at the front and one at the back no problem.
 * 		> if we have lone 'aa', we can append it right at the center of the string, only ONCE
 * 
 * Therefore, for each of the symmetric word w, we add to the result string:
 * 
 * 		closestLowerEvenNumber(
 * 			frequency( w )
 * 		) + 
 * 		( 2 if the center is not occupied yet and if frequency(w) is odd )
 */



public class $2131_Longest_Palindrome_by_Concatenating_Two_Letter_Words {
	
	public int longestPalindrome(String[] words) {
		Map<String, Integer> symmetric = new HashMap<>();
		Map<String, Integer> unsymmetric = new HashMap<>();
		int res = 0;
		
		for (String s: words) {
			if (s.charAt(0) == s.charAt(1)) symmetric.compute(s, (k,v)-> v == null? 1: v+1);
			else unsymmetric.compute(s, (k,v)-> v == null? 1: v+1);
		}
		
		// Process symmetric first
		boolean usedMiddle = false;
		
		for (Entry<String, Integer> e: symmetric.entrySet()) {
			boolean isOdd = e.getValue() % 2 != 0;
			
			res += (e.getValue() - (isOdd? 1: 0) ) * 2;
			
			if (!usedMiddle && isOdd) {
				res += 2;
				usedMiddle = true;
			}
		}
		
		
		// Now process unsymmetric
		for (String s: unsymmetric.keySet() ) {
			String reverse = reverse2(s);
			
			if (unsymmetric.getOrDefault(reverse, 0) != 0) {
				res += Math.min(unsymmetric.get(s), unsymmetric.get(reverse) ) * 4;
				unsymmetric.put(s, 0);
				unsymmetric.put(reverse, 0);
			}
		}
		
		return res;
    }
	
	
	private String reverse2(String s) {
		return "" + s.charAt(1) + s.charAt(0);
	}
	
}
