package Medium;

// https://leetcode.com/problems/find-the-student-that-will-replace-the-chalk

// This is a prefix sum, binary search problem.
// The initial idea is to sum up all the chalks, and modulo the k by the sum. This fast forwards the expense of chalks
// to the final round.
// Without binary search, we can simulate the process by iterating through the chalks array, and keep track of the remaining
// chalks. Once it goes negative, we know the student is the one who will replace the chalk.
// Since the prefix sum is strictly increasing, we can perform binary search to locate the index that has the prefix sum
// strictly larger than k. This is the student who will replace the chalk. This will reduce the search time complexity from O(N) to O(log N)
// (Although the time complexity is still O(N) due to prefix sum calculation)

public class $1894_Find_the_Student_that_Will_Replace_the_Chalk {
    public int chalkReplacer(int[] chalk, int k) {
        int len = chalk.length;
        
        // Constrcut prefix sum array
        long[] prefixSum = new long[len];
        prefixSum[0] = chalk[0];
        for (int i = 1; i < len; ++i) prefixSum[i] = prefixSum[i-1] + chalk[i];

        // Modulo k by sum(chalk)
        k %= prefixSum[len - 1];

        // Perform binary search on prefixSum, to locate the index that is strictly larger than k.
        int l = 0, r = len - 1;
        while (l < r) {
            int m = l + (r - l) / 2;

            if (prefixSum[m] > k) r = m;
            else l = m + 1;
        }

        return l;
    }
}
