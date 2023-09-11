package Easy;

/*
 * 	This is a bitwise / math question.
 * 
 * 	We are asked to find the complement of a non-negative integer. The result of this complement shall
 * 	NEVER be a negative integer too.
 * 
 * 	First approach is to purely utilize the bitwise operator's complement sign (~). What it will do
 *  is convert all the 0 to 1 and vice versa for an integer in 32 bit form. 
 *  However, that's not exactly what we want. For integer 1, we think the binary form is only 1, but
 *  what Java will do is represent it in 0000 0000 .... 0001, then flip to 1111 1111 .... 1110, which
 *  most probably result in a negative number.
 *  
 *  What we could do is to eliminate those padding zeroes. We will COUNT how many 0 padding before the
 *  actual 1, using mask technique. Now note the edge case 0, which does not have 1 at all, therefore
 *  edge case just return 1 for it.
 *  
 *  After counting, we will perform left shift so that now it is the 1 as the leftmost binary digit.
 *  We can perform complement now, then right shift it back to its correct places.
 *  
 * ===========================================================================================
 * 
 * 	Notice a pattern: Complement will change all 0 to 1, and 1 to 0. Meaning, if we add those numbers
 * 	together, they'll become 1111 1111 .... 1111.
 * 	Again, those padding zeroes has to be dealt with. We will initially have a SUM variable which
 * 	consists of all 1111 1111... 1111. Then, for every padding zeroes we met, we will right shift it by
 * 	1 place so that the sum is reduced to the maximum binary digits place.
 * 	
 * 	Eg: for integer 5, binary representation is 101. The sum shall be reduced to 111, which is 7.
 * 	
 * 	Now since N + (ans) = SUM, we simply subtract SUM by N to get the ans (complement).
 * 
 * 
 *  *** In Java, there is 2 right shift operators:
 *  
 *  >	SIGNED RIGHT SHIFT (>>) - Preserves the sign. So if it is negative, it remains negative
 *  >	UNSIGNED RIGHT SHIFT (>>>) - Perform the right shift as it is, regardless if it changes the sign
 */

public class $1009_Complement_of_Base_10_Integer {
	
	public static int bitwiseComplement(int N) {
		if (N == 0) return 1;
        
		int mask = Integer.MIN_VALUE; 	//	This mask is 1000 0000.... up to 32 digits in total
										//	can be used to check if the first binary digit is 1 or 0
		int count = 0;
		
		while ( (mask & N) == 0) {
			N <<= 1;
			count++;
		}
		
		N = ~N;
		
		while (count-- > 0) {
			N >>= 1;
		}
		
		return N;
    }
	
	
	
	public static int bitwiseComplement2(int N) {
		if (N == 0) return 1;
		
		int ncopy = N;
		int mask = Integer.MIN_VALUE;
		int sum = -1;	//	This mask is 1111 1111 up to 32 digits in total. 
		
		while ( (ncopy & mask) == 0 ) {
			sum >>= 1;
		}
		
		return sum - N;
	}
	
	
	public static void main(String[]args) {
		bitwiseComplement(1);
	}
	
	
}
