package DynamicProgramming.Strings;

// This question has 2 parts - finding the length of the shortest common super sequence and printing the shortest common super sequence itself.
// The trick fro length part is very simple - Length of str 1 + Length of str 2 - Length (LCS)
// For printing the super sequence itself, you should watch this video - https://www.youtube.com/watch?v=xElxAuBcvsU 

public class ShortestCommonSuperSequence {
    public static void main(String[] args) {
        String str1 = "brute";
        String str2 = "groot";

        System.out.println(shortestCommonSuperSequence(str1, str2));
    }

    public static String shortestCommonSuperSequence(String str1, String str2){
        int index1 = str1.length();
        int index2 = str2.length();

        int[][] dpTable = new int[index1 + 1][index2 + 1];

        for(int i = 0; i <= index1; i++){
            dpTable[i][0] = 0;
        }

        for(int i = 0; i <= index2; i++){
            dpTable[0][i] = 0;
        }

        for(int i = 1; i <= index1; i++){
            for(int j = 1; j <= index2; j++){
                if(str1.charAt(i - 1) == str2.charAt(j - 1)){
                    dpTable[i][j] = 1 + dpTable[i - 1][j - 1];
                }else{
                    dpTable[i][j] = 0 + Math.max(dpTable[i - 1][j], dpTable[i][j - 1]);
                }
            }
        }

        return shortestCommonSuperSequenceHelper(dpTable, str1, str2);
    }

    private static String shortestCommonSuperSequenceHelper(int[][] dp, String s1, String s2){
        int n = s1.length();
        int m = s2.length();

        int i = n;
        int j = m;

        String ans = "";

        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            ans += s1.charAt(i-1);
            i--;
            j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                ans += s1.charAt(i-1);
                i--;
            } else {
                ans += s2.charAt(j-1);
                j--;
            }
        }
        
        //Adding Remaining Characters - Only one of the below two while loops will run 
        
        while(i>0){
            ans += s1.charAt(i-1);
            i--;
        }
        while(j>0){
            ans += s2.charAt(j-1);
            j--;
        }

        return new StringBuilder(ans).reverse().toString();
    }
}
