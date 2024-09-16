package Medium;

import java.util.List;

// https://leetcode.com/problems/minimum-time-difference

// This is an array, bucket sort problem.
// The initial idea can be sort the time points and then find the minimum difference between two adjacent time points.
// Note that we also need to account for the difference between the first and last time points.
// 
// However, since the domain of the time points is small (24 hours, 60 minutes), we can use a bucket sort to solve this problem.
// We will create a boolean array of size 24 * 60 to represent the time points. Then, we will iterate the time points and mark the corresponding time point in the array.
// After that, we will iterate the array to find the minimum difference between two adjacent time points.

public class $0539_Minimum_Time_Difference {
    public int findMinDifference(List<String> timePoints) {
        boolean[] bucket = new boolean[60 * 24];

        for (String t: timePoints) {
            String[] hm = t.split(":");
            int h = Integer.parseInt(hm[0]), m = Integer.parseInt(hm[1]);
            int mins = h * 60 + m;

            if (bucket[mins]) return 0;
            bucket[mins] = true;
        }

        int first = -1, last = -1;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < bucket.length; ++i) {
            if (!bucket[i]) continue;
            if (first == -1) first = i;
            if (last != -1) res = Math.min(res, i - last);
            last = i;
        }

        // Account for the difference between first and last element
        res = Math.min(res, (first + 60 * 24) - last);
        return res;
    }
}
