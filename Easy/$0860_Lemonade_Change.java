package Easy;

// https://leetcode.com/problems/lemonade-change

// This is an Array, Greedy problem.
//
// Customers can only pay with 5, 10, 20 dollar bills.
// We need to return true if we can provide change for every customer.
// The direct approach is to keep track of the number of 5, 10, 20 dollar bills we currently have.
// Then, for every customer, we can check if we can provide the change or not, greedily (i.e. give the highest denomination first).
// If we can't provide the change, we return false.

public class $0860_Lemonade_Change {
    public boolean lemonadeChange(int[] bills) {
        int[] counts = {0, 0, 0};
        int[] values = {20, 10, 5};

        for (int b: bills) {
            int changeRequired = b - 5;

            for (int i = 0; i < 3; ++i) {
                int value = values[i], count = counts[i];
                int changeCount = Math.min(count, changeRequired / value);
                changeRequired -= (changeCount * value);
                counts[i] -= changeCount;
            }
            if (changeRequired != 0) return false;

            if (b == 5) ++counts[2];
            else if (b == 10) ++counts[1];
            else ++counts[0];
        }

        return true;
    }
}
