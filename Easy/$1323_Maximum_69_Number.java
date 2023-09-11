package Easy;

// https://leetcode.com/problems/maximum-69-number
/*
 * Ezpz and nice problem. Just change the first occurence of 6 to 9, and not do anything if there's no 6
 */


public class $1323_Maximum_69_Number {

    public int maximum69Number (int num) {
        char[] arr = String.valueOf(num).toCharArray();
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '6') {
                arr[i] = '9';
                return Integer.parseInt(new String(arr));
            }
        }
        return Integer.parseInt(new String(arr));    
    }

}