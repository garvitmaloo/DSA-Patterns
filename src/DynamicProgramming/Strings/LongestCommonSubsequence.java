package DynamicProgramming.Strings;

import java.util.Arrays;

public class LongestCommonSubsequence {
    public static void main(String[] args) {
        String s1 = "acd";
        String s2 = "ced";

        System.out.println(LCSTabulation(s1, s2));
    }

    public static int LCS(String text1, String text2){
        int len1 = text1.length();
        int len2 = text2.length();

        return LCSHelper(text1, text2, len1 - 1, len2 - 1);
    }

    private static int LCSHelper(String text1, String text2, int index1, int index2){
        if(index1 < 0 || index2 < 0){
            return 0;
        }

        // Found same char at given index
        if(text1.charAt(index1) == text2.charAt(index2)){
            return 1 + LCSHelper(text1, text2, index1 - 1, index2 - 1);
        }

        return 0 + Math.max(LCSHelper(text1, text2, index1 - 1, index2), LCSHelper(text1, text2, index1, index2 - 1));
    }


    // Memoization approach
    public static int LCSMemoized(String text1, String text2){
        int len1 = text1.length();
        int len2 = text2.length();

        int[][] dp = new int[len1][len2];

        for(int[] arr : dp){
            Arrays.fill(arr, -1);
        }

        return LCSMemoizedHelper(text1, text2, len1 - 1, len2 - 1, dp);
    }

    private static int LCSMemoizedHelper(String text1, String text2, int index1, int index2, int[][] dp){
        if(index1 < 0 || index2 < 0) return 0;

        if(dp[index1][index2] != -1){
            return dp[index1][index2];
        }

        if(text1.charAt(index1) == text2.charAt(index2)){
            return dp[index1][index2] = 1 + LCSMemoizedHelper(text1, text2, index1 - 1, index2 - 1, dp);
        }

        return dp[index1][index2] = 0 + Math.max(LCSMemoizedHelper(text1, text2, index1 - 1, index2, dp), LCSMemoizedHelper(text1, text2, index1, index2 - 1, dp));
    }

    public static int LCSTabulation(String text1, String text2){
        int index1 = text1.length();
        int index2 = text2.length();

        int[][] table = new int[index1 + 1][index2 + 1];

        for(int[] arr : table){
            Arrays.fill(arr, -1);
        }

        for(int i = 0; i <= index1; i++){
            table[i][0] = 0;
        }

        for(int i = 0; i <= index2; i++){
            table[0][i] = 0;
        }

        for(int i = 1; i <= index1; i++){
            for(int j = 1; j <= index2; j++){
                if(text1.charAt(i - 1) == text2.charAt(j - 1)){
                    table[i][j] = 1 + table[i - 1][j - 1];
                }else{
                    table[i][j] = 0 + Math.max(table[i - 1][j], table[i][j - 1]);
                }
            }
        }

        return table[index1][index2];
    }
}