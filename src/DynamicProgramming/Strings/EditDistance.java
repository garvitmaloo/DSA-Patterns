package DynamicProgramming.Strings;

import java.util.Arrays;

public class EditDistance {
    public static void main(String[] args) {
        String s1 = "horse";
        String s2 = "ros";

        System.out.println(editDistance(s1, s2));
    }

    public static int editDistance(String s1, String s2){
        int index1 = s1.length();
        int index2 = s2.length();

        return editDistanceHelper(s1, s2, index1 - 1, index2 - 1);
    }

    private static int editDistanceHelper(String s1, String s2, int index1, int index2){
        if(index1 < 0) return index2 + 1;
        if(index2 < 0) return index1 + 1;

        // if characters match, no steps needed, just shrink both the strings.
        if(s1.charAt(index1) == s2.charAt(index2)) return 0 + editDistanceHelper(s1, s2, index1 - 1, index2 - 1);

        // If characters do not match, you can either insert, delete or replace
        int insertSteps = 1 + editDistanceHelper(s1, s2, index1, index2 - 1);
        int deleteSteps = 1 + editDistanceHelper(s1, s2, index1 - 1, index2);
        int replaceSteps = 1 + editDistanceHelper(s1, s2, index1 - 1, index2 - 1);

        return Math.min(insertSteps, Math.min(deleteSteps, replaceSteps));
    }

    static int editDistanceUtil(String S1, String S2, int i, int j, int[][] dp) {
        // Base cases
        if (i < 0)
            return j + 1;
        if (j < 0)
            return i + 1;

        // If the result is already computed, return it
        if (dp[i][j] != -1)
            return dp[i][j];

        // If the characters at the current positions match, no edit is needed
        if (S1.charAt(i) == S2.charAt(j))
            return dp[i][j] = editDistanceUtil(S1, S2, i - 1, j - 1, dp);

        // Minimum of three choices:
        // 1. Replace the character in S1 with the character in S2.
        // 2. Delete the character in S1.
        // 3. Insert the character from S2 into S1.
        else
            return dp[i][j] = 1 + Math.min(editDistanceUtil(S1, S2, i - 1, j - 1, dp),
                    Math.min(editDistanceUtil(S1, S2, i - 1, j, dp), editDistanceUtil(S1, S2, i, j - 1, dp)));
    }

    static int editDistanceMemoized(String S1, String S2) {
        int n = S1.length();
        int m = S2.length();

        int[][] dp = new int[n][m];
        for (int row[] : dp)
            Arrays.fill(row, -1);

        // Call the recursive helper function
        return editDistanceUtil(S1, S2, n - 1, m - 1, dp);
    }

    static int editDistanceTabulation(String S1, String S2) {
        int n = S1.length();
        int m = S2.length();

        // Create a 2D array to store the minimum edit distances
        int[][] dp = new int[n + 1][m + 1];

        // Initialize the first row and column with their respective indices
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }

        // Fill the dp array using a bottom-up approach
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (S1.charAt(i - 1) == S2.charAt(j - 1)) {
                    // If the characters match, no edit is needed, so take the value from the diagonal.
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // If the characters don't match, take the minimum of three possibilities:
                    // 1. Replace the character in S1 with the character in S2 (diagonal).
                    // 2. Delete the character in S1 (left).
                    // 3. Insert the character from S2 into S1 (up).
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }

        return dp[n][m];
    }
}
