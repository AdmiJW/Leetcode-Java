package Medium;

import java.util.Arrays;

// https://leetcode.com/problems/maximum-bags-with-full-capacity-of-rocks
/*
 * This is a greedy, sorting problem.
 * 
 * From capacity[] and rocks[] array, derive an array of left[] which indicates the spaces left for rocks in each bag
 * Then sort the left[] array. The greedy idea is that if I have limited number of additional rocks, I should fill
 * in the bags with the least spaces left first, and then the second least, and so on. 
 */

public class $2279_Maximum_Bags_With_Full_Capacity_of_Rocks {
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        final int n = capacity.length;
        int[] left = new int[n];

        for (int i = 0; i < n; i++)
            left[i] = capacity[i] - rocks[i];

        Arrays.sort(left);

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (left[i] > additionalRocks) break;
            
            additionalRocks -= left[i];
            ++res;
        }

        return res;
    }
}
