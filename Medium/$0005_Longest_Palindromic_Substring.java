package Medium;

//https://leetcode.com/problems/longest-palindromic-substring/
/*
 * 	This is a Dynamic Programming Problem!
 * 
 * 	By Brute Force, we can solve the problem in O(N^3) time:
 * 
 * 	For each starting position, for each possible length, check if the substring covered is palindrome or not
 * 	With this algorithm, there are many ways to optimize it, by immediately returning when it is detected an
 * 	impossible case
 * 
 * 	However the worst case scenario can't be escaped: O(N^3)
 * 
 * 	------------------------------------------------------------
 * 
 * 	There are a lot of repeated computation! A string to check palindrome may already has substring that
 * 	is already been checked if it is palindrome before or not!
 * 
 * 	Use Dynamic Programming To store the results!
 * 
 * 	We have 2D table of size lxl. Row indicates the starting position in string. Column indicates the ending
 * 	position in string.
 * 
 * 	The base case is: All substring length 1 is palndrome!
 * 	Unlike left to right, we will be traversing the DP array in diagonal, top left to bottom right.
 * 	
 * 	This is because, for bottom up approach, we start with length 1, length 2... until the last length of
 * 	l.
 * 	By iterating in diagonal manner, we ensure to check all, say length 2 before checking length 3
 * 
 * 
 * 	It will be boolean array indicating whether it is palindrome or not. A palindrome is found when:
 * 	
 * 	>	First char matches last char
 * 	>	The substring in between first and last char is palindrome.
 * 
 * 	------------------------------------------------------------------------
 * 
 * 	The above solution only involves 2 row of array! We can optimize to O(2N) space!
 * 
 * 	---------------------------------------------------------------------------
 * 
 * 	Notice that palindrome starts from center, and we can use two pointers extending outwards to check
 * 	how far can the palindrome can go.
 * 	Thus we can have the solution:
 * 		
 * 		>	Choose a center
 * 		>	Extend outwards
 * 
 * 	The center can be choosen is O(2N+1) because apart from len 1 centers like 'aba', we can have length 2
 * 	centers like 'abba'
 * 
 * 	Extend outwards using two pointers is O(N) worst case.
 * 
 * 	Overall is O(N^2) time complexity but optimized space to O(1) only
 */

public class $0005_Longest_Palindromic_Substring {
	
	//	Brute Force Solution - O(N^3) worst case
	public String longestPalindrome(String s) {
		final int len = s.length();
		int start = 0, palinLen = 1;		//	Indicates the longest palindrome starting position and the span length
		
		//	i is valid starting pos, and it is still possible to find a longer palindrome
		//	(Not yet meet Previous palindrome longer than from current pos to end)
		for (int i = 0; i < len && len - i > palinLen; ++i) {
			
			//	Try out all lengths, where length span is initialized to previously meet length
			for (int l = palinLen; i+l < len; ++l) {
				//	Since the way l is initialized, if is palindrome, it must be longer already
				if ( checkPalindrome(s, i, i+l) ) {
					start = i;
					palinLen = l+1;
				}
			}
		}
		return s.substring(start, start+palinLen);
    }
	private boolean checkPalindrome(String s, int from, int to) {
		while (from < to) {
			if (s.charAt(from) != s.charAt(to) ) return false;
			++from; --to;
		}
		return true;
	}
	
	
	//	Dynamic Programming Solution. Using space to trade for time O(N^2) space O(N^2) time
	public String longestPalindrome2(String s) {
		final int len = s.length();
		boolean[][] dp = new boolean[len][len];
		int resStart = 0, resLen = 1;
		
		for (int i = 0; i < len; ++i)
			dp[i][i] = true;
		
		for (int palinLen = 2; palinLen <= len; ++palinLen) {
			for (int start = 0; start + palinLen - 1 < len; ++start ) {
				if (s.charAt(start) == s.charAt(start + palinLen - 1) 
					&& (palinLen == 2 || dp[start+1][start+palinLen-2] )) 
				{
					dp[start][start+palinLen-1] = true;
					
					if (palinLen > resLen) {
						resStart = start;
						resLen = palinLen;
					}
				}
			}
		}
		return s.substring(resStart, resStart+resLen);
	}
	
	
	//	Optimized space DP solution. O(2N) space instead
	public String longestPalindrome3(String s) {
		final int len = s.length();
		boolean[] dp1 = new boolean[len];
		boolean[] dp2 = new boolean[len];
		int resStart = 0, resLen = 1;
		
		for (int i = 0; i < len; ++i) {
			dp1[i] = true;
			dp2[i] = true;
		}
		
		for (int palinLen = 2; palinLen <= len; ++palinLen) {
            
            for (int start = 0; start + palinLen - 1 < len; ++start) { 
				if (s.charAt(start) == s.charAt(start + palinLen - 1) && dp2[start + 1] ) {
					dp2[start] = true;
					
					if (palinLen > resLen) {
						resStart = start;
						resLen = palinLen;
					}
				} else {
                    dp2[start] = false;
                }
			}
            boolean[] temp = dp1;
            dp1 = dp2;
            dp2 = temp;
		}
		return s.substring(resStart, resStart+resLen);
	}
	
	
	
	
	//	Expand from center solution - No DP
	public String longestPalindrome4(String s) {
		final int len = s.length();
		int start = 0, palinLen = 1;
		
		for (int i = 1; i < len; ++i) {
			int expandA = expand(s, i, i);
			int expandB = expand(s, i-1, i);
			
			if (expandA > palinLen) {
				start = i - expandA / 2;
				palinLen = expandA;
			}
			if (expandB > palinLen) {
				start = i - expandB / 2;
				palinLen = expandB;
			}
		}
		return s.substring( start, start+palinLen);
	}
	private int expand(String s, int left, int right) {
		final int len = s.length();
		while (left >= 0 && right < len && s.charAt(left) == s.charAt(right) ) {
			--left; ++right;
		}
		
		return Math.max( 1, right - left - 1);
	}
	
	
	
	
}
