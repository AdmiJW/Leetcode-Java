package Medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// https://leetcode.com/problems/sequential-digits/
/*
 * 	This is a "brute-force" problem, or you could say simulation at least?
 * 
 * 	Start out with digits from 1 to 9. We will attempt to append sequenatial digits at the end of the numbers.
 * 	Once they fit inside the [low, high] boundary, add it to the result set.
 * 
 * 	Discard the number as soon as it become higher than upper boundary.
 */

public class $1291_Sequential_Digits {
	
	public List<Integer> sequentialDigits(int low, int high) {
		List<Integer> res = new ArrayList<>();
		for (int i = 1; i < 9; ++i) recurse(res, i, low, high);
		Collections.sort(res);
		return res;
	}
	
	
	
	private void recurse(List<Integer> res, int n, int low, int high) {
		if (n > high) return;
		if (low <= n) res.add(n);
		
		if (n % 10 == 9) return;
		recurse(res, n * 10 + (n % 10 + 1), low, high);
	}
}
