package Medium;

//https://leetcode.com/problems/number-of-islands/
/*
 * This is mainly a DFS/BFS problem.
 * 
 * The strategy is to iterate the grid. Once you encounter a '1' - an island, increment the island count,
 * and call the DFS/BFS method which will explore all lands of the island, and marking it as visited 
 * (Maybe mark it as '2').
 *  
 */


public class $0200_Number_of_Islands {
	
	private static final int[][] DIRS = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
	
	
	public int numIslands(char[][] grid) {
		int islands = 0;
		
		for (int i = 0; i < grid.length; ++i ) {
			for (int j = 0; j < grid[0].length; ++j ) {
				if (grid[i][j] == '1') {
					explore(grid, i, j);
					++islands;
				}
			}
		}
		
		return islands;
    }
	
	
	// Performs a dfs on provided grid and explores its adjacent neighbors
	// Marks the grid in-place to '2'
	private void explore(char[][] grid, int r, int c) {
		grid[r][c] = '2';
		
		for (int[] DIR: DIRS) {
			try {
				if (grid[r + DIR[0]][c + DIR[1]] == '1') 
					explore(grid, r+DIR[0], c+DIR[1]);
			} catch (Exception e) {}
		}
	}
}
