package Medium;

// https://leetcode.com/problems/minimum-falling-path-sum/
/*
 * This is a DP problem.
 * 
 * Starting from the last row, the minimum falling path sum of each element is itself. Then we move upwards, and for each
 * element, we find the minimum sum of the three elements below it, and add it to itself. This is done in a bottom-up manner.
 */

public class $0931_Minimum_Falling_Path_Sum {

    public int minFallingPathSum(int[][] matrix) {
        final int r = matrix.length, c = matrix[0].length;
        int[] dp = new int[c];
        
        for (int i = 0; i < c; ++i) dp[i] = matrix[r-1][i];

        for (int i = r - 2; i >= 0; --i) {
            int[] temp = new int[c];
            
            for (int j = 0; j < c; ++j) {
                int min = dp[j];
                if (j > 0) min = Math.min(min, dp[j-1]);
                if (j < c - 1) min = Math.min(min, dp[j+1]);
                temp[j] = min + matrix[i][j];
            }
            dp = temp;
        }

        int min = dp[0];
        for (int i = 1; i < c; ++i) min = Math.min(min, dp[i]);
        return min;
    }

}
