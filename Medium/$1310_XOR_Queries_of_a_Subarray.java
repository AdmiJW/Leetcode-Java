package Medium;

// https://leetcode.com/problems/xor-queries-of-a-subarray

// This is a array, bit manipulation, prefix sum concept problem.
//
// For each of the queries, if we have to iterate over the range and XOR all the elements within the range, it will be too expensive.
// Instead, what we can do is utilize the property of XOR and the idea from prefix sum, to have a prefix XOR array.
// For the XOR of range [i, j], the result will be prefixXOR[j] ^ prefixXOR[i-1] if i > 0, else prefixXOR[j].
// We can compute the prefix XOR array in O(n) time and then for each query, we can compute the result in O(1) time.

public class $1310_XOR_Queries_of_a_Subarray {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int l = arr.length, q = queries.length;
        int[] prefixXOR = new int[l];

        // Compute prefix 'XOR'
        prefixXOR[0] = arr[0];
        for (int i = 1; i < l; ++i) prefixXOR[i] = prefixXOR[i-1] ^ arr[i];

        // Compute result
        int[] res = new int[q];

        for (int i = 0; i < q; ++i) {
            int[] query = queries[i];
            int start = query[0], end = query[1];
            if (start == 0) res[i] = prefixXOR[end];
            else res[i] = prefixXOR[end] ^ prefixXOR[start - 1];
        }

        return res;
    }
}