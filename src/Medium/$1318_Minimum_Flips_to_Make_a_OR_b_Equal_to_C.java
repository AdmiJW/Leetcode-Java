package Medium;


// https://leetcode.com/problems/minimum-flips-to-make-a-or-b-equal-to-c
/*
 * This is a bit manipulation problem.
 *  
 * Iterate through each bit of a, b, c. If c's bit is 0, but a and b's bit is 1, we need to flip both a and b's bit.
 * Otherwise if c's bit does not match a or b's bit, we need to flip one of them.
 * Finally, return the total number of flips.
 * Time Complexity: O(1) - 32 bits
 */


public class $1318_Minimum_Flips_to_Make_a_OR_b_Equal_to_C {

    public int minFlips(int a, int b, int c) {
        int flips = 0;

        for (int i = 0; i < 32; ++i) {
            int aBit = a & 1;
            int bBit = b & 1;
            int cBit = c & 1;

            // Case 1: c's bit is 0, but a and b's bit is 1. Flip both
            if ( (cBit == 0) && (aBit == 1) && (bBit == 1) ) flips += 2;
            // Case 2: c's bit does not match, flip one
            else if ((aBit | bBit) != cBit ) ++flips;
            // Case 3: c's bit match. Ignore.

            a >>= 1;
            b >>= 1;
            c >>= 1;
        }

        return flips;
    }

}