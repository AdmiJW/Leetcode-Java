package Easy;

//https://leetcode.com/problems/add-digits/
/*
 * The solution with iterations/recursion is simple. However, there exists a mathematical solution
 * which solves it in O(1) using digital root - Sum of digits in a number.
 * 
 * See the official solution.
 */


public class $0258_Add_Digits {

//	public int addDigits(int num) {
//        int res = num;
//        
//        while (res / 10 != 0) {    	
//        	int curr = res;
//        	res = 0;
//        	
//        	while (curr / 10 != 0 ) {
//        		res += curr % 10;
//        		curr /= 10;
//        	}
//        	res += curr;
//        }
//        
//        return res;
//    }
	
	public int addDigits(int num) {
		return (num == 0)? 0: (num - 1) % 9 + 1;
	}
	
}
