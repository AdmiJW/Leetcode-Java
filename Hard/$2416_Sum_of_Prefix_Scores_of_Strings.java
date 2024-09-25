package Hard;

// https://leetcode.com/problems/sum-of-prefix-scores-of-strings

// This is a Trie problem.
// The question asks that for each string in array `words`, we have to consider all of its prefixes, and calculate how many strings in the array have that prefix.
// For example, if the array is ["abc", "ab", "ab"], then the answer for the first string "abc" will be 7, since:
//      - 'a' is a prefix of 3 strings: "abc", "ab", "ab"
//      - 'ab' is a prefix of 3 strings: "abc", "ab", "ab"
//      - 'abc' is a prefix of 1 string: "abc"
// 
// To solve this problem, we can use a Trie data structure.
// Each of the Trie node will have a score, which will be incremented whenever a string is added to the Trie.
// For each string, we will traverse the Trie and calculate the score of the prefix.

public class $2416_Sum_of_Prefix_Scores_of_Strings {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int score = 0;
    }
    
    public int[] sumPrefixScores(String[] words) {
        int l = words.length;
        TrieNode root = new TrieNode();
        int[] res = new int[l];

        // Construct Trie
        for (String s: words) {
            TrieNode curr = root;

            for (char c: s.toCharArray()) {
                int i = c - 'a';
                if (curr.children[i] == null) curr.children[i] = new TrieNode();
                ++curr.children[i].score;
                curr = curr.children[i];
            }
        }

        // Calculate score
        for (int i = 0; i < l; ++i) {
            int sum = 0;
            String s = words[i];
            TrieNode curr = root;

            for (char c: s.toCharArray()) {
                int j = c - 'a';
                curr = curr.children[j];
                sum += curr.score;
            }

            res[i] = sum;
        }

        return res;
    }
}
