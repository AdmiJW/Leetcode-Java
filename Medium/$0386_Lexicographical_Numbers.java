package Medium;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/lexicographical-numbers

// This is a DFS based problem.
// Thinking of it as a tree, Each layer of the tree will look roughly like this:
//     (1)              (2)                 (3)             ...
//    / | \            / | \               / | \
//  10 11 12 ...     20 21 22 ...        30 31 32 ...
// 
// The idea is to explore the tree based on the rules. Layer n will have n digits, Eg layer 2 will have 2 digits like 10, 11, 12, 13, 14, 15, 16, 17, 18, 19.
// The tree will be explored in a DFS manner, so we will first explore all the children of 1, then all the children of 2, then all the children of 3 and so on.
// 
// Using this approach in iterative manner, we can generate the lexicographically sorted numbers.
// We will always try to see if appending 0 to the current number is possible, if yes, we will do that.
// If not, we will try to increment the number by 1, as long as the last digit isn't 9.
// If the last digit is 9, we will keep dividing the number by 10, until the last digit isn't 9, or we reach 0 (end of the tree).

public class $0386_Lexicographical_Numbers {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        int i = 1; res.add(1);

        while (i > 0) {
            // Approach 1: Multiply 10 (append 0)
            i = i * 10;
            if (i <= n) {
                res.add(i);
                continue;
            }

            // Aproach 2: Increment 1 (as long as last digit isn't 9)
            i = i / 10;

            while (i % 10 == 9 || i + 1 > n) i = i / 10;

            if (i > 0 && i % 10 != 9) {
                i = i + 1;
                res.add(i);
            }
        }

        return res;
    }
}
