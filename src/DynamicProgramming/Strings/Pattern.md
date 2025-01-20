# How to identify this pattern?

You'll be given a string and you'll be asked to find the longest common substring or subsequence in it. You might also be asked to find shortest common super sequence or palindromic subsequence.

# How to solve this pattern?

These are the steps to solve this pattern:

1. Represent the problem in the form of indices. You'll need two indices for the two strings.
2. Explore all the possibilities that can happen in the given problem.
3. Take the best scenario, like take max or sum or whatever is required.
4. The base case will be when one of the indices goes lesser than 0.

## A general approach to solve this pattern

- We start from the end index and we keep on reducing the index based on certain conditions
- Like if we find same char at both the indices, we reduce both the indices by one and increase the count by one.
- And if we do not find a match at the given index, we reduce index of one of the strings at a time and take a max of both the possibilities. And count is not increased. Here's the code for finding the longest common subsequence for the two strings -

```
// Recursive function to find the length of the Longest Common Subsequence (LCS)
    static int lcsUtil(String s1, String s2, int ind1, int ind2) {
        // Base case: If either of the strings reaches the end, return 0
        if (ind1 < 0 || ind2 < 0)
            return 0;

        // If the characters at the current indices are the same, increment the LCS length
        if (s1.charAt(ind1) == s2.charAt(ind2))
            return 1 + lcsUtil(s1, s2, ind1 - 1, ind2 - 1);

        // If the characters are different, choose the maximum LCS length by either
        // skipping a character in s1 or skipping a character in s2
        return 0 + Math.max(lcsUtil(s1, s2, ind1, ind2 - 1),
            lcsUtil(s1, s2, ind1 - 1, ind2));
    }
```

## Time and Space Complexity of the recursive approach

- Time complexity: O(2^n)
- Space complexity: O(n)

# Using memoization to optimize the recursive approach

In order to memoize the recursive solution, we'll need a two-dimensional array. If string 1 has length N and string 2 has length M, then we'll need a 2D array of size NxM.

Here's the code for the memoized approach -

```
// Recursive function to find the length of the Longest Common Subsequence (LCS)
    static int lcsUtil(String s1, String s2, int ind1, int ind2, int[][] dp) {
        // Base case: If either of the strings reaches the end, return 0
        if (ind1 < 0 || ind2 < 0)
            return 0;

        // If the result for this subproblem has already been calculated, return it
        if (dp[ind1][ind2] != -1)
            return dp[ind1][ind2];

        // If the characters at the current indices are the same, increment the LCS length
        if (s1.charAt(ind1) == s2.charAt(ind2))
            return dp[ind1][ind2] = 1 + lcsUtil(s1, s2, ind1 - 1, ind2 - 1, dp);

        // If the characters are different, choose the maximum LCS length by either
        // skipping a character in s1 or skipping a character in s2
        return dp[ind1][ind2] = 0 + Math.max(lcsUtil(s1, s2, ind1, ind2 - 1, dp),
            lcsUtil(s1, s2, ind1 - 1, ind2, dp));
    }
```

## Time and Space Complexity of the memoized approach

- Time complexity: O(N x M) (Reason: There are N x M states therefore at max ‘N x M’ new problems will be solved.)
- Space complexity: O(N x M) + O(N + M)

# Using tabulation to optimize the memoized approach

The general approach for tabulation is -

1. Initialize a 2D array
2. Fill the base cases
3. Convert recursion into nested loops
4. Copy the recurrence relation

NOTE: The base cases are the cases when the indices go out of bounds (-1). So, we need to make sure to shift the indices by 1 to the right. So now, i will be treated as i - 1 and j will be treated as j - 1.

Here's the code for the tabulation approach -

```
// Function to find the length of the Longest Common Subsequence (LCS)
    static int lcs(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        // Create a 2D array to store results of sub problems
        int dp[][] = new int[n + 1][m + 1];

        // Initialize the dp array with -1 to indicate that sub problems are not solved yet
        for (int rows[] : dp)
            Arrays.fill(rows, -1);

        // Initialize the first row and first column with zeros since LCS with an empty string is zero
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= m; i++) {
            dp[0][i] = 0;
        }

        // Fill the dp array using dynamic programming
        for (int ind1 = 1; ind1 <= n; ind1++) {
            for (int ind2 = 1; ind2 <= m; ind2++) {
                // If the characters at the current indices are the same, increment the LCS length
                if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1))
                    dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
                // If the characters are different, choose the maximum LCS length by either
                // excluding a character in s1 or excluding a character in s2
                else
                    dp[ind1][ind2] = Math.max(dp[ind1 - 1][ind2], dp[ind1][ind2 - 1]);
            }
        }

        return dp[n][m]; // Return the length of the Longest Common Subsequence (LCS)
    }
```
