package Medium;

import java.util.List;

//https://leetcode.com/problems/diagonal-traverse/
/*
 * 	This is an Matrix traversal problem, which can solve with simulation or reversal
 * 
 * 	The first approach i use is simulation. Keeping a boolean flag to indicate whether from current position, i should go
 * 	bottom left or to top right. This flag is alternated each time after one diagonal traversal.
 * 
 * 	From the first grid (0, 0), it is initialized towards top right. 
 * 
 * 	For towards top right, 
 * 		>	Each iteration, go up one row, and go right one column. 
 * 		>	Once go out of bound, check
 * 			-	If we still within the columns, just go down. It will be the grid for next bottom left direction iteration
 * 			-	Otherwise, we need to go down two row (since due to top right iteration, we are 2 grids away from next grid)
 * 
 * 	For towards bottom left, 
 * 		>	Each iteration, go down one row, and go left one column
 * 		>	Once go out of bound, check
 * 			-	If we still within the rows, just go right. It will be the grid for the next top right direction iteration
 * 			-	Otherwise, we need to go right two columns (since due to bottom left iteration, we are 2 grids away from next grid)
 * 
 * ---------------------------------------------------------------------------------------------------------------
 * 
 *  The reversal solution is also plausible. 
 *  
 *  The easier problem is just to go in bottom left direction. Then, every even number of iterations, we have to reverse
 *  the result retrieved.
 *  
 *  Say we have a 3x3 matrix. We need to traverse 5 times, obtained by r + c - 1,
 *  Which is (0,0), (0,1), (0,2), (1,2), (2,2)
 *  which is along the top border and right border
 *  
 *  A reverse of array is easily done in place by using two pointers technique
 */


public class $0498_Diagonal_Traverse {
	
	// My Simulation solution
	public int[] findDiagonalOrder(int[][] matrix) {
		final int rows = matrix.length;
        if (rows == 0) return new int[0];
		final int cols = matrix[0].length;
		
		int r = 0, c = 0;
		boolean isDown = false;
		
		int[] res = new int[rows * cols];
		int pt = 0;
		
		//	While index is not out of bounds, there is still uniterated elements
		while (r < rows && r >= 0 && c < cols && c >= 0) {
			//	Direction towards bottom left 
			if (isDown) {
				while (r < rows && r >= 0 && c < cols && c >= 0) {
					res[pt++] = matrix[r][c];
					++r; --c;
				}
				
				//	The index at end needs to be corrected. There are 2 cases
				if (r < rows && r >= 0)
					c = 0;
				else {
					c += 2;
					r = rows - 1;
				}
			} 
			//	Direction towards top right
			else {
				while (r < rows && r >= 0 && c < cols && c >= 0) {
					res[pt++] = matrix[r][c];
					--r; ++c;
				}
				
				//	The index at end needs to be corrected. There are 2 cases
				if (c < cols && c >= 0)
					r = 0;
				else {
					r += 2;
					c = cols -1;
				}
			}
			
			isDown = !isDown;	//	Alternate directions
		}
		return res;		
    }
	
	
	
	//	Reversal solution
	public int[] findDiagonalOrder2(int[][] matrix) {
		final int rows = matrix.length;
        if (rows == 0) return new int[0];
		final int cols = matrix[0].length;
		
		int[] res = new int[rows * cols];
		int pt = 0;
		boolean rev = false;
		
		for (int i = 0; i < rows + cols - 1; ++i) {
			int s = pt;
			int r = (i < cols)? 0: i - cols + 1;
			int c = (i < cols)? i: cols - 1;
			
			while (r < rows && c >= 0)
				res[pt++] = matrix[r++][c--];
			
			if (rev) reverseArray(res, s, pt-1);
			rev = !rev;
		}
		
		return res;
	}
	
	
	private void reverseArray(int[] arr, int from, int to) {
		while (from < to) {
			int temp = arr[from];
			arr[from] = arr[to];
			arr[to] = temp;
			++from; --to;
		}
	}
	
}
