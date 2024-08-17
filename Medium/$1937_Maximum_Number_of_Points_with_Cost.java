package Medium;

// https://leetcode.com/problems/maximum-number-of-points-with-cost

// This is an array, dynamic programming problem. By the optimization nature in the problem, we can identify DP as the solution.
//
// The problem is to find the maximum points that can be obtained by selecting one cell from each row. 
// Penalties are applied to the selected cell based on the distance from the previous row's selected cell.
// The brute force solution to attempt all possible combinations of cells from each row will be too computationally expensive.
//
// Let `rows` be the number of rows and `cols` be the number of columns in the input array.
// The initial thought to solve by DP would be that we can have an array of length `cols`, let's call it `prevMax`, to store the maximum points 
// that can be obtained where the last cell selected is the cell at that index.
// Initially, we can fill this array with the points from the first row. Then, for each row from the second row onwards, we will use another
// array, `currMax`. Each cell, we will iterate over `prevMax` to attempt all possible cells from the previous row, applying penalties, and
// storing the maximum points that can be obtained by selecting the current cell.
// Such solution seems plausible, but would stil be too slow, as the time complexity would be O(rows * cols^2).
// 
// Instead, we can use the concept of DP within DP. For each column c, we can actually precompute the maximum points that can be obtained by
// strictly selecting cells from the left of c or from the right of c. 
// For each row, we will first compute `leftMax` and `rightMax` arrays, where `leftMax[c]` will store the maximum points that can be obtained
// by selecting cells from <= c, and `rightMax[c]` will store the maximum points that can be obtained by selecting cells from >= c.
// The formula for `leftMax` would be:
//    - For the element at `leftMax[0]`, it will be the same as the first element of `prevMax`.
//    - For the rest of the elements, it will be max( prevMax[c], leftMax[c-1] - 1 ). Where -1 is the penalty for selecting a cell from the left.
// Similarly, the formula for `rightMax` would be:
//    - For the element at `rightMax[cols-1]`, it will be the same as the last element of `prevMax`.
//    - For the rest of the elements, it will be max( prevMax[c], rightMax[c+1] - 1 ). Where -1 is the penalty for selecting a cell from the right.
// After computing `leftMax` and `rightMax`, we can compute `currMax`:
//    - For each cell c, `currMax[c]` will be points[r][c] + max( leftMax[c], rightMax[c] ).
// Finally, the result will be the maximum value in the `prevMax` array once the last row is processed.
// This way, we can reduce the time complexity to O(rows * cols).

public class $1937_Maximum_Number_of_Points_with_Cost {
    public long maxPoints(int[][] points) {
        int rows = points.length;
        int cols = points[0].length;
        long[] prevMax = new long[cols];
        long[] currMax = new long[cols];
        long[] leftMax = new long[cols];
        long[] rightMax = new long[cols];

        for (int i = 0; i < cols; ++i) prevMax[i] = points[0][i];

        for (int r = 1; r < rows; ++r) {
            int[] currRow = points[r];
            leftMax[0] = prevMax[0];
            rightMax[cols - 1] = prevMax[cols - 1];

            for (int c = 1; c < cols; ++c) leftMax[c] = Math.max(leftMax[c - 1] - 1, prevMax[c]);
            for (int c = cols - 2; c >= 0; --c) rightMax[c] = Math.max(rightMax[c + 1] - 1, prevMax[c]);
            for (int c = 0; c < cols; ++c) currMax[c] = currRow[c] + Math.max(leftMax[c], rightMax[c]);

            prevMax = currMax;
        }

        long res = Long.MIN_VALUE;
        for (int c = 0; c < cols; ++c) res = Math.max(res, prevMax[c]);
        return res;
    }
}