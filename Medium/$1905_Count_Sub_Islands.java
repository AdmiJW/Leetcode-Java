package Medium;

import java.util.Queue;
import javafx.util.Pair;
import java.util.LinkedList;

// https://leetcode.com/problems/count-sub-islands/

// This is a graph problem, and we can solve it with a simple BFS.
// The solution is to use a BFS to explore the islands in grid2 and check if they are sub-islands of grid1.
// The idea is to explore the islands in grid2 like we are solving the original number of islands problem.
// However, we need to check if for every island cell in grid2, the corresponding cell in grid1 is also an island cell.
// Once we detect that the island in grid2 is not a sub-island of grid1, we record it and continue exploring the rest of the island.
// Once the island is fully explored, we return the result - 1 if all the cells are islands in grid1, 0 otherwise.

public class $1905_Count_Sub_Islands {
    private final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int r = grid1.length, c = grid1[0].length;
        boolean[][] visited = new boolean[r][c];
        int res = 0;

        for (int i = 0; i < r; ++i) {
            for (int j = 0; j < c; ++j) {
                if (grid2[i][j] == 1 && !visited[i][j])
                    res += explore(i, j, grid1, grid2, visited);
            }
        }

        return res;
    }

    private int explore(int i, int j, int[][] grid1, int[][] grid2, boolean[][] visited) {
        int r = grid1.length, c = grid1[0].length;
        int res = 1;
        Queue<Pair<Integer, Integer>> q = new LinkedList<>();
        q.add(new Pair<>(i, j));
        visited[i][j] = true;
        
        while (!q.isEmpty()) {
            Pair<Integer, Integer> p = q.poll();
            i = p.getKey(); j = p.getValue();
            if (grid1[i][j] == 0) res = 0;

            for (int[] dir: DIRS) {
                int k = i + dir[0], l = j + dir[1];
                if (k < 0 || k >= r || l < 0 || l >= c || visited[k][l]) continue;
                visited[k][l] = true;
                if (grid2[k][l] != 1) continue;
                q.add(new Pair<>(k, l));
            }
        }

        return res;
    }
}
