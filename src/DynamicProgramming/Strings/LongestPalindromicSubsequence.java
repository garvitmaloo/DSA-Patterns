package DynamicProgramming.Strings;

import java.util.Arrays;

// The only trick in this question is that you need to construct a reverse string and apply LCS for that string
// Original string and reverse string will have longest common subsequence and that will be longest palindromic subsequence.

public class LongestPalindromicSubsequence {
    public static void main(String[] args) {
        System.out.println(LPSTabulation("bbabcbcab"));
    }

    public static int LongestPalindromicSubsequenceMemoized(String s){
        String reverseStr = "";

        for(int i = s.length() - 1; i >= 0; i--){
            reverseStr = reverseStr + s.charAt(i);
        }

        int[][] dp = new int[s.length()][reverseStr.length()];

        for(int[] arr : dp){
            Arrays.fill(arr, -1);
        }

        return LongestPalindromicSubsequenceMemoizedHelper(s, reverseStr, s.length() - 1, reverseStr.length() - 1, dp);
    }

    private static int LongestPalindromicSubsequenceMemoizedHelper(String s1, String s2, int index1, int index2, int[][] dp){
        if(index1 < 0 || index2 < 0){
            return 0;
        }

        if(dp[index1][index2] != -1){
            return dp[index1][index2];
        }

        if(s1.charAt(index1) == s2.charAt(index2)){
            return 1 + LongestPalindromicSubsequenceMemoizedHelper(s1, s2, index1 - 1, index2 - 1, dp);
        }

        return 0 + Math.max(LongestPalindromicSubsequenceMemoizedHelper(s1, s2, index1 - 1, index2, dp), LongestPalindromicSubsequenceMemoizedHelper(s1, s2, index1, index2 - 1, dp));
    }

    public static int LPSTabulation(String s){
        String reverseStr = "";

        for(int i = s.length() - 1; i >= 0; i--){
            reverseStr = reverseStr + s.charAt(i);
        }

        int[][] table = new int[s.length() + 1][reverseStr.length() + 1];

        for(int[] rows : table){
            Arrays.fill(rows, -1);
        }

        for(int i = 0; i <= s.length(); i++){
            table[i][0] = 0;
        }

        for(int i = 0; i <= reverseStr.length(); i++){
            table[0][i] = 0;
        }

        for(int i = 1; i <= s.length(); i++){
            for(int j = 1; j <= reverseStr.length(); j++){
                if(s.charAt(i - 1) == reverseStr.charAt(j - 1)){
                    table[i][j] = 1 + table[i - 1][j - 1];
                }else{
                    table[i][j] = 0 + Math.max(table[i - 1][j], table[i][j - 1]);
                }
            }
        }

        return table[s.length()][reverseStr.length()];
    }
}
