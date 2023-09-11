package Easy;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


// https://leetcode.com/problems/find-the-difference-of-two-arrays
/*
 * This is a HashMap problem.
 * 
 * We need a way to knowing the occurrence of an element in both arrays in O(1) time. HashMap is the best choice.
 * 
 * First, we store all elements in nums1 into the map, with value 1.
 * Then, iterate through elements in nums2, if the element is not in map, store it with value 2. 
 * Else, store it with value 3, meaning it has occurred in both arrays.
 * 
 * Finally, iterate through the map, and add all elements with value 1 into res1, and all elements with value 2 into res2.
 * The total iteration is (M + N + (M + N - distinct(M,N))) = O(M + N)
 * Space is O(M + N)
 */



public class $2215_Find_the_Difference_of_Two_Arrays {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> res1 = new ArrayList<>(), res2 = new ArrayList<>();

        for (int i: nums1) map.put(i, 1);
        for (int i: nums2) {
            if (!map.containsKey(i)) map.put(i, 2);
            if (map.get(i) == 1) map.put(i, 3);
        }
        
        for (int i: map.keySet()) {
            if (map.get(i) == 1) res1.add(i);
            else if (map.get(i) == 2) res2.add(i);
        }

        result.add(res1);
        result.add(res2);
        return result;
    }


    // Use array as bucket
    public List<List<Integer>> findDifference2(int[] nums1, int[] nums2) {
        int[] bucket = new int[2001];
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> res1 = new ArrayList<>(), res2 = new ArrayList<>();

        for (int i: nums1) bucket[i + 1000] = 1;
        for (int i: nums2) {
            if (bucket[i + 1000] == 1) bucket[i + 1000] = 3;
            if (bucket[i + 1000] == 0) bucket[i + 1000] = 2;
        }

        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] == 1) res1.add(i - 1000);
            else if (bucket[i] == 2) res2.add(i - 1000);
        }

        result.add(res1);
        result.add(res2);
        return result;
    }
}
