package Medium;

//https://leetcode.com/problems/reconstruct-original-digits-from-english/
/*
 * 	This is a brainteaser, string problem
 * 	
 * 	The alphabets are shuffled! How can i know if a digit occur how many times in original english word?
 * 	Well, the core point is that:
 * 		>	The english string is always valid. Every alphabet exists for a reason - belonging to one digit
 * 
 * 	Therefore, we can identify unique features that other digits don't have! We can use a set to easily find that out!
 * 
 * 	>	'g' only occur in 8 (eight)
 * 	>	'x' only occur in 6 (six)
 * 	>	'u' only occur in 4 (four)
 * 	>	'w' only occur in 2 (two)
 * 	>	'z' only occur in 0 (zero)
 * 
 * 	That's all unique! But with half of them elimiated, we can now easily find out the rest through elimination
 * 
 * 	>	's' occur in 6 and 7 (six, seven), but six is elimiated earlier
 * 	>	'v' occur in 5 and 7 (five, seven), but seven is elimiated earlier
 * 	>	'r' occur in 0, 3 and 4 (zero, three, four), but zero, four is eliminated earlier
 * 	>	'o' occur in 0, 1, 2, 4 (zero, one, two, four), but zero, two and four are eliminated earlier
 * 	>	'i' occur in 5,6,8,9 (five, six, eight, nine), but five, six, eight are eliminated earlier
 */

public class $0423_Construct_Original_Digits_From_English {
	
	public String originalDigits(String s) {
		int[] freq = new int['z' + 1];
		for (char c: s.toCharArray() )
			++freq[c];
		
		int[] digits = new int[10];
		
		//	Eight
		if (freq['g'] != 0) {
			digits[8] += freq['g'];
			decrementFreq(freq, "eight", freq['g']);
		}
		//	Six
		if (freq['x'] != 0) {
			digits[6] += freq['x'];
			decrementFreq(freq, "six", freq['x']);
		}
		//	Four
		if (freq['u'] != 0) {
			digits[4] += freq['u'];
			decrementFreq(freq, "four", freq['u']);
		}
		//	Two
		if (freq['w'] != 0) {
			digits[2] += freq['w'];
			decrementFreq(freq, "two", freq['w']);
		}
		//	Zero
		if (freq['z'] != 0) {
			digits[0] += freq['z'];
			decrementFreq(freq, "zero", freq['z']);
		}
		//	Seven
		if (freq['s'] != 0) {
			digits[7] += freq['s'];
			decrementFreq(freq, "seven", freq['s']);
		}
		//	Five
		if (freq['v'] != 0) {
			digits[5] += freq['v'];
			decrementFreq(freq, "five", freq['v']);
		}
		//	Three
		if (freq['r'] != 0) {
			digits[3] += freq['r'];
			decrementFreq(freq, "three", freq['r']);
		}
		//	One
		if (freq['o'] != 0) {
			digits[1] += freq['o'];
			decrementFreq(freq, "one", freq['o']);
		}
		//	Nine
		if (freq['i'] != 0) {
			digits[9] += freq['i'];
			decrementFreq(freq, "nine", freq['i']);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= 9; ++i) {
			while (digits[i]-- > 0)
				sb.append(i);
		}
		return sb.toString();
    }
	
	private void decrementFreq(int[] f, String s, int n) {
		for (char c: s.toCharArray() )
			f[c] -= n;
	}
	
	
	//	Optimized and clean code. Using Set minus concept
	public String originalDigits2(String s) {
		int[] f = new int[10];
		
		for (char c: s.toCharArray() ) {
			if (c == 'g') ++f[8];	//	8
			if (c == 'x') ++f[6];	//	6
			if (c == 'u') ++f[4];	//	4
			if (c == 'w') ++f[2];	//	2
			if (c == 'z') ++f[0];	//	0
			if (c == 's') ++f[7];	//	6,7
			if (c == 'v') ++f[5];	//	5,7
			if (c == 'r') ++f[3];	//	3,4
			if (c == 'o') ++f[1];	//	0,1,2,4
			if (c == 'i') ++f[9];	//	5,6,8,9
		}
		
		f[7] -= f[6];	//	f7 = f7 - f6
		f[5] -= f[7];	//	f5 = f5 - f7
		f[3] -= f[0] + f[4];	//	f3 = f3 - f0 - f4
		f[1] -= f[0] + f[2] + f[4];	//	f1 = f1 - f0 - f2 - f4
		f[9] -= f[5] + f[6] + f[8];	//	f9 = f9 - f5 - f6 - f8
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= 9; ++i) {
			while (f[i]-- > 0) sb.append(i);
		}
		return sb.toString();
	}
	
}
