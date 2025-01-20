package DynamicProgramming.LongestIncreasingSubsequence;

import java.util.Arrays;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(LISMemoized(arr));
    }

    public static int LIS(int[] arr){
        return LIS(arr, 0, -1);
    }

    private static int LIS(int[] arr, int currentIndex, int prevIndex){
        if(currentIndex == arr.length) return 0;

        // not pick
        int notPick = LIS(arr, currentIndex + 1, prevIndex);
        int pick = 0;

        // pick
        if(prevIndex == -1 || arr[currentIndex] > arr[prevIndex]){
            pick = 1 + LIS(arr, currentIndex + 1, currentIndex);
        }

        return Math.max(pick, notPick);
    }

    public static int LISMemoized(int[] arr){
        int[][] dp = new int[arr.length][arr.length + 1];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        return LISMemoized(arr, 0, -1, dp);
    }

    private static int LISMemoized(int[] arr, int currentIndex, int prevIndex, int[][] dp){
        if(currentIndex == arr.length) return 0;

        if(dp[currentIndex][prevIndex + 1] != -1) return dp[currentIndex][prevIndex + 1];

        int notPick = LISMemoized(arr, currentIndex + 1, prevIndex, dp);
        int pick = 0;

        if(prevIndex == -1 || arr[currentIndex] > arr[prevIndex]){
            pick = 1 + LISMemoized(arr, currentIndex + 1, currentIndex, dp);
        }

        return dp[currentIndex][prevIndex + 1] = Math.max(pick, notPick);
    }
}
