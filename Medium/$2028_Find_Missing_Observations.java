package Medium;

// https://leetcode.com/problems/find-missing-observations

// This is a Math, simulation problem.
// The idea is to first calculate the target value that was to be achieved using n dices.
// Then, see if target value can be achieved using n dices.
// The method that I used to construct the result dices is to calculate the average value that is needed to be added to each dice to achieve the target value.
// Then, ceil the average value to get the value that needs to be added to the dice.

public class $2028_Find_Missing_Observations {
    public int[] missingRolls(int[] rolls, int mean, int n) {
        // First, calculate the target value that was to be achieved using n dices
        int nDices = rolls.length + n;
        int partialSum = 0;
        for (int i: rolls) partialSum += i;
        int target = (mean * nDices) - partialSum;

        // See if target value can be achieved using n dices
        int min = n * 1; int max = n * 6;
        if (target < min || target > max) return new int[]{};

        // Construct the result dices.
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            double avg = target / (n - i);
            int d = (int)Math.ceil(avg);
            res[i] = d;
            target -= d;
        }

        return res;
    }
}
