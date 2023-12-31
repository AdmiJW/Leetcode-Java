package Easy;

//https://leetcode.com/problems/valid-mountain-array/
/*
 * 	This is an array iteration problem.
 * 
 *	A mountain array can be split into 2 parts: uphill part, and downhill part.
 *	For this problem, we can simulate the climbing process in 2 loops:
 *	
 *	>	Initially starting at second element, if current element is greater than previous one, this is still
 *		valid go proceed to next grid
 *	
 *	Then, we will stop when we are at start of downhill. However, there is some cases:
 *	>	If we still
 *			-	At starting point. This means that we didnt climb at all due to 2nd element being smaller already
 *			-	At the end of entire array. This means the array is entirely uphill. No downhill at all
 *		Those are false cases, return false
 *
 *	Then similarly, climb downhill as long as current element is lesser than previous one.
 *	
 *		At the end, we should at the end of mountain. If not, return false.	
 */

public class $0941_Valid_Mountain_Array {
	public boolean validMountainArray(int[] A) {
		final int len = A.length;
		if (len < 3) return false;
		
		int i = 1;
		while (i < len && A[i-1] < A[i]) ++i;
		if (i == 1 || i == len) return false;
		while (i < len && A[i-1] > A[i]) ++i;
		return i == len;
	}
}
