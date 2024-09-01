package Easy;

// https://leetcode.com/problems/convert-1d-array-into-2d-array/

// This is a easy array problem.
// The solution is to iterate over the original array and fill the 2D array with the elements of the original array.
// We can calculate the row and column of the 2D array by dividing the index of the original array by the number of columns of the 2D array.
// The column is the remainder of the division.

public class $2022_Convert_1D_Array_Into_2D_Array {
    public int[][] construct2DArray(int[] original, int m, int n) {
        if (original.length != m * n) return new int[][]{};
        int[][] res = new int[m][n];

        for (int i = 0; i < original.length; ++i) {
            int r = i / n;
            int c = i % n;
            res[r][c] = original[i];
        }
        return res;
    }
}
