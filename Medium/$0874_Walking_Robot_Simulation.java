package Medium;

import java.util.Set;
import java.util.HashSet;
 
// https://leetcode.com/problems/walking-robot-simulation

// This is a simulation, Map problem.
// The core intuitive to solve this problem is to simulate the robot's movement.
// The problem is to be able to check if the robot will hit an obstacle or not efficiently.
// To do so, we can use a `Set` to store the obstacles' locations, where it is stored as a `String` of the form "x,y".

public class $0874_Walking_Robot_Simulation {
    private final int[][] UNIT_VECTORS = {
        {0, 1},
        {1, 0},
        {0, -1},
        {-1, 0}
    };

    private int changeDirection(int dir, int command) {
        if (command == -1) return (dir + 1) % 4;
        return dir == 0 ? 3: dir - 1;
    }

    public int robotSim(int[] commands, int[][] obstacles) {
        Set<String> obs = new HashSet<>();
        for (int[] o: obstacles) obs.add(o[0] + "," + o[1]);

        int direction = 0;
        int[] location = {0, 0};
        int res = 0;

        for (int c: commands) {
            if (c < 0) {
                direction = changeDirection(direction, c);
                continue;
            }

            int[] uv = UNIT_VECTORS[direction];

            for (int i = 0; i < c; ++i) {
                location[0] += uv[0];
                location[1] += uv[1];
                if (obs.contains(location[0] + "," + location[1])) {
                    location[0] -= uv[0];
                    location[1] -= uv[1];
                    break;
                }
            }

            res = Math.max(location[0] * location[0] + location[1] * location[1], res);
        }

        return res;
    }
}