package Medium;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/nearest-exit-from-entrance-in-maze
/*
 * This is a BFS problem.
 * 
 * Starting from the entrance, we can use BFS to find the nearest exit, layer by layer.
 * Use a queue to store the coordinates of the cells to perform BFS.
 */


public class $1926_Nearest_Exit_From_Enterance_In_Maze {

    public int nearestExit(char[][] maze, int[] entrance) {
        int directions[][] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(entrance);
        maze[entrance[0]][entrance[1]] = '+';

        for (int i = 0; !queue.isEmpty(); i++) {
            for (int j = queue.size(); j > 0; --j) {

                int[] current = queue.poll();
                if (isExit(maze, current, entrance)) return i;
                
                for (int[] direction : directions) {
                    int x = current[0] + direction[0];
                    int y = current[1] + direction[1];

                    if (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == '.') {
                        maze[x][y] = '+';
                        queue.offer(new int[]{x, y});

                        System.out.println(x + " " + y);
                    }
                }
            }
        }

        return -1;
    }


    private boolean isExit(char[][] maze, int[] current, int[] entrance) {
        return !( current[0] == entrance[0] && current[1] == entrance[1] ) &&
            ( current[0] == 0 || current[0] == maze.length - 1 || current[1] == 0 || current[1] == maze[0].length - 1 );
    }

}
