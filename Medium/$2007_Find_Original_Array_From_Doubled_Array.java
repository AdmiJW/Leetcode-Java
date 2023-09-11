package Medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/find-original-array-from-doubled-array/
/*
 * 	This is primarily a sorting + hashmap problem.
 * 
 * 	In an array that has elements e - The original element that isn't doubled, and 2e, how do we identify element e,
 * 	that isnt doubled?
 * 
 * 	If we sort the array, we can easily obtain the smallest element in the array. If the array is valid, then the
 * 	smallest element couldn't possibly be 2e. 
 * 
 * 	Therefore we will just try to eliminate the smallest element whenever possible.
 */

public class $2007_Find_Original_Array_From_Doubled_Array {
	
	public int[] findOriginalArray(int[] changed) {
		final int len = changed.length;
		if (len % 2 != 0) return new int[] {};
		
		// The array has to be sorted. Imagine case [12,6,3,24]. If we don't sort, we would've paired 12 with 6!
		Arrays.sort(changed);
		
		int[] res = new int[len / 2];
		int i = 0;
		Map<Integer, Integer> map = new HashMap<>();
		
		for (int n: changed) {
			if ( n % 2 == 0 && map.getOrDefault(n / 2, 0) > 0 ) {
				res[i++] = n / 2;
				map.put(n / 2, map.get(n / 2) - 1);
			}
			else map.put(n, map.getOrDefault(n, 0) + 1);
		}
		
		if (i != len / 2) return new int[] {};
		return res;
    }
	
}
