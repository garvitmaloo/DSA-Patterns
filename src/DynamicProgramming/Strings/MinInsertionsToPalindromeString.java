package DynamicProgramming.Strings;

import java.util.Arrays;

// The trick in this question is find out the length of the largest palindromic subsequence and subtract it from the length of the string. That is going to give the min number of operations to make string palindrome.
// This is because we have to keep the longest palindrome string and copy paste the remaining string length in or around the palindrome subsequence.

public class MinInsertionsToPalindromeString {
    public static void main(String[] args) {
        String str = "mbadm";
        System.out.println(minInsertions(str));
    }

    public static int minInsertions(String s){
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        String reverseStr = sb.toString();

        int index1 = s.length();
        int index2 = reverseStr.length();

        int[][] dp = new int[index1][index2];

        for(int[] arr : dp){
            Arrays.fill(arr, -1);
        }

        int longestPalindromicSubsequenceLength = longestPalindromicSubsequence(s, reverseStr, index1 - 1, index2 - 1, dp);
        return s.length() - longestPalindromicSubsequenceLength;
    }

    private static int longestPalindromicSubsequence(String str, String reverseStr, int index1, int index2, int[][] dp){
        if(index1 < 0 || index2 < 0) return 0;

        if(dp[index1][index2] != -1) return dp[index1][index2];

        if(str.charAt(index1) == reverseStr.charAt(index2)){
            return dp[index1][index2] = 1 + longestPalindromicSubsequence(str, reverseStr, index1 - 1, index2 - 1, dp);
        }

        return dp[index1][index2] = 0 + Math.max(longestPalindromicSubsequence(str, reverseStr, index1 - 1, index2, dp), longestPalindromicSubsequence(str, reverseStr, index1, index2 - 1, dp));
    }
}
