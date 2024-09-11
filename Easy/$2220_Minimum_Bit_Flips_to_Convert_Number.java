package Easy;

// https://leetcode.com/problems/minimum-bit-flips-to-convert-number

// This is a bit manipulation problem.
// To solve this is simple, as long as start and goal are not equal, there exists bit flips that has to be done.
// We can select the least significant bit of start and goal, and compare them.  If they are not equal, we have to flip the bit.
// Then perform a right shift to both start and goal to compare the next bit.
//
// Another mentionable approach is to use XOR operation, which will give us the difference between start and goal.
// Then, we can obtain the hamming weight of the XOR result to get the number of bits that are different between start and goal.

public class $2220_Minimum_Bit_Flips_to_Convert_Number {
    public int minBitFlips(int start, int goal) {
        int res = 0;

        while (start > 0 || goal > 0) {
            if ((start & 1) != (goal & 1)) ++res;
            start >>= 1; goal >>= 1;
        }

        return res;
    }
}
