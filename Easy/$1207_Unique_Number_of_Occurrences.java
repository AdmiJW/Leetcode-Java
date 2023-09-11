package Easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/unique-number-of-occurrences/
/*
 * Given an array of integers arr, write a function that returns true if and only 
 * if the number of occurrences of each value in the array is unique.
 * 
 * Get the frequency of each number, then check if the frequency is unique using a HashSet.
 * 
 * Optionally, the problem can be solved using counting sort, but it is more complex for small input size like this problem
 */



public class $1207_Unique_Number_of_Occurrences {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        Set<Integer> occurrences = new HashSet<>();

        for (int num: arr)
            freq.put(num, freq.getOrDefault(num, 0) + 1);

        for (int num: freq.values()) {
            if (occurrences.contains(num)) return false;
            occurrences.add(num);
        }
        return true;
    }
}
