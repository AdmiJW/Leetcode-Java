package Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/find-players-with-zero-or-one-losses/
/*
 *  This is a HashMap, Optionally Counting sort problem
 * 
 *  For the matches, we simply has to record how many losses each player has.
 *  Then, we return the list of players that has losses = 0 or 1
 * 
 *  To return the list in ascending order, we can sort the list after filtering the players
 *  Or we could use counting sort, which does not have to sort, but may have space redundancy,
 *  done by initializing an array of size O(N), which N is the possible maximum player ID
 */


public class $2225_Find_Players_With_Zero_Or_One_Losses {
    public List<List<Integer>> findWinners(int[][] matches) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> noLosses = new ArrayList<>();
        List<Integer> oneLosses = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        res.add(noLosses);
        res.add(oneLosses);
        

        // Record the team's losses
        for (int[] match: matches) {
            if ( !map.containsKey(match[0]) ) map.put(match[0], 0);
            if ( !map.containsKey(match[1]) ) map.put(match[1], 0);

            map.put(match[1], map.get(match[1]) + 1);
        }


        for (int team: map.keySet()) {
            if (map.get(team) == 0) noLosses.add(team);
            else if (map.get(team) == 1) oneLosses.add(team);
        }

        // Sort
        noLosses.sort(null);
        oneLosses.sort(null);

        return res;
    }
}
