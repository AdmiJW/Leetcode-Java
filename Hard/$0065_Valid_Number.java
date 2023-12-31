package Hard;

import java.util.regex.*;

//https://leetcode.com/problems/valid-number/
/*
 * 	Facing this kind of string parsing problem, I prefer to use Regex instead of attempting parsing myself.
 * 	
 * 	There are indeed great solutions that use flags and if statements to check validity�B For me, testing 
 * 	string patterns call for regex.
 * 
 * 	The following is my attempted regex, although not the fastest:
 * 	
 * 	^									- Start of line
 * 	[+-]?								- A + or - sign, but optionally
 * 	(?:									- Matching group, but non-capturing
 * 		\\.\\d+							- Decimal point, then followed by one or more digit. Handles edge cases " .12345..."
 * 		|								- OR
 * 		\\d*\\.?\\d+					- Any amount of digit, then a optional decimal point, then one digit or more
 * 	)
 * 	(?:									- Matching group, but non-capturing
 * 		[eE]							- Exponent sign
 * 		[+-]?							- Sign
 * 		\\d+							- Exponent must have integers
 * 	)?									- The non-capturing group is optional. Either it occurs, or none
 * 	$									- End of line
 */

public class $0065_Valid_Number {
	
	public boolean isNumber(String s) {
		return Pattern.matches("^[+-]?(?:\\.\\d+|\\d+\\.?\\d*)(?:[eE][+-]?\\d+)?$", s);
    }
	
}
