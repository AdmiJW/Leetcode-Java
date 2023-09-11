package Easy;

// https://leetcode.com/problems/make-the-string-great/
/*
 * A Stack String problem.
 * 
 * For each character, look at the top of the stack.
 *  > If stack empty, simply push
 *  > If stack peek is not the opposite of current character, simply push
 *  > Otherwise pop.
 */


public class $1544_Make_The_String_Great {

    public String makeGood(String s) {
        StringBuilder sb = new StringBuilder();

        for (char c: s.toCharArray()) {
            if (sb.length() == 0 || sb.charAt(sb.length() - 1) != opposite(c) ) {
                sb.append(c);
            } else {
                sb.deleteCharAt(sb.length() - 1);
            }
        }

        return sb.toString();
    }


    private char opposite(char c) {
        if (c >= 'a' && c <= 'z') return (char) (c - 'a' + 'A');
        return (char) (c - 'A' + 'a');
    }

}
