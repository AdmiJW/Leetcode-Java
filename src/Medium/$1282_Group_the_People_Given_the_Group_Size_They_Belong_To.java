
package Medium;

import java.util.*;

// https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to/description
/*
 * This is a greedy, hashmap problem.
 * 
 * We want to group people with same group size together. Once we have enough people, we add them to the result list.
 * Therefore, if we encountered an individual with say group size 3, we add them to the list of group size 3.
 * The next time we encounter another individual with group size 3, we add them to the same list, until the list size
 * is 3, then the group is complete, and we add the list to the result list.
 */


class Solution {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        final int len = groupSizes.length;

        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < len; ++i) {
            int size = groupSizes[i];

            map.putIfAbsent(size, new ArrayList<>());
            map.get(size).add(i);

            if (map.get(size).size() == size) {
                res.add(map.get(size));
                map.remove(size);
            }
        }

        return res;
    }
}