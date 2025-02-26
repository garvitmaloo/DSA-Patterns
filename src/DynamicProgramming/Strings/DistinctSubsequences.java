package DynamicProgramming.Strings;

import java.util.Arrays;

// This is an example of String matching algorithm. In such cases, you can have 2 scenarios - either the character matches or it does not.

public class DistinctSubsequences {
    public static void main(String[] args) {
        String s = "babgbag"; 
        String t = "bag";

        System.out.println(distinctSubsequences(s, t));
    }

    public static int distinctSubsequences(String s, String t){
        int index1 = s.length();
        int index2 = t.length();

        return distinctSubsequencesHelper(s, t, index1 - 1, index2 - 1);
    }

    private static int distinctSubsequencesHelper(String s, String t, int index1, int index2){
        // Base cases
        if(index1 < 0) return 0;
        if(index2 < 0) return 1;

        if(s.charAt(index1) == t.charAt(index2)){
            return distinctSubsequencesHelper(s, t, index1 - 1, index2 - 1) + distinctSubsequencesHelper(s, t, index1 - 1, index2);
        }

        return distinctSubsequencesHelper(s, t, index1 - 1, index2);
    }

    static int prime = (int) (Math.pow(10, 9) + 7);

    // Function to count the number of distinct subsequences of s1 that are equal to s2
    static int countUtil(String s1, String s2, int ind1, int ind2, int[][] dp) {
        // If we have exhausted s2, there's one valid subsequence (empty string) in s1.
        if (ind2 < 0)
            return 1;
        // If we have exhausted s1 but not s2, there are no valid subsequences.
        if (ind1 < 0)
            return 0;

        // If the result is already computed, return it.
        if (dp[ind1][ind2] != -1)
            return dp[ind1][ind2];

        // If the characters at the current positions match, we can either leave one character from s1
        // or continue to the next character in s1 while staying at the same character in s2.
        if (s1.charAt(ind1) == s2.charAt(ind2)) {
            int leaveOne = countUtil(s1, s2, ind1 - 1, ind2 - 1, dp);
            int stay = countUtil(s1, s2, ind1 - 1, ind2, dp);

            // Add the two possibilities and take modulo prime to avoid integer overflow.
            return dp[ind1][ind2] = (leaveOne + stay) % prime;
        } else {
            // If the characters don't match, we can only continue to the next character in s1.
            return dp[ind1][ind2] = countUtil(s1, s2, ind1 - 1, ind2, dp);
        }
    }

    // Function to calculate the count of distinct subsequences of s1 equal to s2
    static int subsequenceCounting(String s1, String s2, int lt, int ls) {
        // Initialize a DP array to store intermediate results
        int dp[][] = new int[lt][ls];
        for (int rows[] : dp)
            Arrays.fill(rows, -1);

        // Call the recursive helper function to compute the count
        return countUtil(s1, s2, lt - 1, ls - 1, dp);
    }
}
