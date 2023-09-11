package Arrays;

//https://leetcode.com/explore/learn/card/fun-with-arrays/521/introduction/3237/

public class $1295_Find_Numbers_with_Even_Number_of_Digits {
	
	public int findNumbers(int[] nums) {
		int count = 0;
		for (int i : nums) {
			int digits = 0;
			while (i != 0 ) {
				i /= 10;
				digits++;
			}
			count += (digits % 2 == 0)? 1: 0;
		}
		return count;
	}
	
}
