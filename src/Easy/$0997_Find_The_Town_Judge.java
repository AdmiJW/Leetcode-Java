package Easy;



//https://leetcode.com/explore/challenge/card/may-leetcoding-challenge/535/week-2-may-8th-may-14th/3325/
/*
 *	The problem is a graph theory problem, where the judge should have 0 Outgoing edges, and exactly N-1 incoming edges
 *	Even better, every time a person trusts a person, we simply decrement it by 1, and when a person get trusted, increment it by one
 *	Lastly use a linear search to check if there is a person (judge) which value is exactly N-1
 */

public class $0997_Find_The_Town_Judge {
	
	public int findJudge(int N, int[][]trust) {
		int[] trustCount = new int[N];
		
		//Remember that indexing starts at 0, therefore index 0 refers to person 1
		for (int[] t: trust) {
			trustCount[t[0] - 1 ] --;
			trustCount[t[1] - 1 ] ++;
		}
		for (int i = 0; i < N; i ++ ) {
			if (trustCount[i] == N-1 ) return i+1;
		}
		return -1;
	}
	
}
