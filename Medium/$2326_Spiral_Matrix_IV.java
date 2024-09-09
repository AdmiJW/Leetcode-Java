package Medium;

import Linked_List.ListNode;

// https://leetcode.com/problems/spiral-matrix-iv/

// This is a linked list, matrix problem.
// The core concept is straightforward, we need to traverse the matrix in a spiral order and fill the matrix with the values from the linked list.
// Every step, we forward the head of the linked list and fill the matrix with the value of the head. If the head had reached the end, we fill the rest of the matrix with -1.

public class $2326_Spiral_Matrix_IV {
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] res = new int[m][n];

        int x = 0, y = 0;
        int dir = 0;        // 0 = Upper Left to Upper Right, 1 = Upper Right to Lower Right, 2 = Lower Right to Lower Left, 3 = Lower Left to Upper Left
        int bound = 0;

        for (int i = 0; i < m*n; ++i) {
            // System.out.println(x + " " + y);
            res[x][y] = head != null ? head.val : -1;
            if (head != null) head = head.next;

            if (dir == 0) {
                if (y < n - bound - 1) ++y;
                else { ++x; ++dir; }
            } else if (dir == 1) {
                if (x < m - bound - 1) ++x;
                else { --y; ++dir; }
            } else if (dir == 2) {
                if (y > bound) --y;
                else { --x; ++dir; }
            } else {
                if (x > bound + 1) --x;
                else { ++y; dir = 0; ++bound; }
            }
        }

        return res;
    }
}
