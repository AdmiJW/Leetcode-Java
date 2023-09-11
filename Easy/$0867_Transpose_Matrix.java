package Easy;

//https://leetcode.com/problems/transpose-matrix/
/*
 *	Simple array problem.
 *
 *	In a matrix transposition, Say the original matrix is of size MxN, then the result matrix will be size NxM
 *	Therefore, we cannot modify the array in place. Extra space of O(MN) is required.
 *
 *	Once we have the resulting matrix initialized, simply iterate through elements of original array, applying the
 *	formula:
 *		
 *		Result[j][i] = Original[i][j]
 */

public class $0867_Transpose_Matrix {
	
	public int[][] transpose(int[][] matrix) {
		int[][] result = new int[ matrix[0].length ][ matrix.length ];
		
		for (int i = 0; i < matrix.length; ++i)
			for (int j = 0; j < matrix[0].length; ++j)
				result[j][i] = matrix[i][j];
		
		return result;
	}
	
}
