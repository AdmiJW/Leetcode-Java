package Medium;

import java.util.List;

// This is a arrays, greedy problem
//
// Given a 2D array of integers, each array is already sorted in ascending order.
// This already means that the first element of the array is the smallest, and the last element is the largest.
// We have to select TWO DIFFERENT arrays, and find the absolute maximum difference between the first element of one array and the last element of another array.
// 
// To do this in linear time complexity, we have to keep track of the minimum and maximum element we have seen so far.
// For each array, we can calculate the maximum difference between the minimum element and the last element, and the maximum element and the first element.
// Then we see if we want to update the maximum difference or not.
// Do note that do not update the max and min element first, then calculate the difference. It will lead to wrong answer as we may be selecting the same array
// for the maximum difference.

public class $0624_Maximum_Distance_in_Arrays {
    public int maxDistance(List<List<Integer>> arrays) {
        List<Integer> first = arrays.get(0);
        int min = first.get(0);
        int max = first.get(first.size() - 1);
        int abs = Integer.MIN_VALUE;

        for (int i = 1; i < arrays.size(); ++i) {
            List<Integer> l = arrays.get(i);
            int len = l.size();
            int localMin = l.get(0);
            int localMax = l.get(len-1);

            int localMinAbs = Math.abs(max - localMin);
            int localMaxAbs = Math.abs(localMax - min);
            int localAbs = Math.max(localMinAbs, localMaxAbs);
            abs = Math.max(abs, localAbs);
            min = Math.min(min, localMin);
            max = Math.max(max, localMax);
        }

        return abs;
    }
}