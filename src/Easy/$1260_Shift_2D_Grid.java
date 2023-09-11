package Easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//https://leetcode.com/problems/shift-2d-grid/
/*
* This is a matrix problem.
* 
* Although it is a 2D matrix which consist of 2 indices i and j, we can easily "flatten" the indices:
* Given a matrix of IxJ, we can flatten it as an linear array of size IxJ, and we can easily convert from the linear index to
* row and column index:
* 
* To 2D indices:
*       i = index / col
*       c = index % col
* To linear index
*       index = i * col + c
* 
* --------------------------------
* 
* To solve this problem, we can either use extra space by creating a brand new matrix and returning it, or we can do it inplace.
* To solve this problem in-place by modifying the input matrix, you may refer to problem "Rotate Array" also on Leetcode, and
* available in the same repository.
* 
* 
* !! In-place solution in C++ available.
* 
*/

public class $1260_Shift_2D_Grid {
	public List<List<Integer>> shiftGrid(int[][] grid, int k) {
		final int row = grid.length, col = grid[0].length;
        final int n = row * col;
        
        // Construct the resulting matrix
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < row; ++i) {
        	res.add( new ArrayList<>(col) );
        	for (int j = 0; j < col; ++j) res.get(i).add(0);
        }

        // Avoid overshifting
        k = k % n;

        for (int i = 0; i < n; ++i) {
        	int e = grid[i / col][i % col];
        	
            int shiftTo = (i + k) % n;
            int r = shiftTo / col;
            int c = shiftTo % col;
            
            res.get(r).set(c, e);
        }

        return res;
    }
}
