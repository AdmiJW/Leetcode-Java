package Medium;

// https://leetcode.com/problems/2-keys-keyboard

// This is a dynamic programming problem / Math prime factorization problem.
// However we will only discuss on the DP solution.
//
// The problem is to find the minimum number of operations to get n 'A's on the screen.
// Every step, we can either copy all the characters from the screen or paste the characters from the clipboard.
// The brute force solution would be to try all possible combinations of copying and pasting, but that would be too expensive.
//
// The DP idea is that, we will maintain an array of length n, called `dp`, where it will maintain the minimum number of operations to get i 'A's on the screen.
// Initially, we will fill the array with Integer.MAX_VALUE, except for dp[1] = 0, as we already have 1 'A' on the screen.
// Then, we will iterate over the clipboard length, c, from 1 to n. Each iteration of c strictly only considers cases when the characters in the clipboard is c.
// 
// Let's see when c = 1, to set up, we would have to first copy the one 'A' on the screen, thus 1 operation. Then we iterate over the multiples of 1, l, from 1 to n.
// For each l, we will paste the clipboard to the screen, thus 1 operation. We will then update dp[l] with the minimum of dp[l] and the number of operations.
// For c = 2, we would have to perform copy operation when the screen has 2 'A's. Thus the number of operations would be dp[2] + 1, since dp[2] is the minimum 
// number of operations to get 2 'A's.
// Then, iterate over the multiples of 2, l, from 2 to n. For each l, we will paste the clipboard to the screen, thus 1 operation. We will then update dp[l] with
// the minimum of dp[l] and the number of operations.
// We will continue this process until c = n.

public class $0650_2_Keys_Keyboard {
    public int minSteps(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; ++i) dp[i] = Integer.MAX_VALUE;

        // Clipboard length loop
        for (int c = 1; c <= n; ++c) {
            int ops = dp[c] + 1;

            // N loop
            for (int l = c; l <= n; l += c) {
                dp[l] = Math.min(dp[l], ops);
                ++ops;
            }
        }

        return dp[n];
    }
}
