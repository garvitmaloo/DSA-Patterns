# How to identify this pattern?

You'll be given an array and you'll be asked to find the longest increasing subsequence in it. in general, just watchout for these two words in the problem statement - Longest and increasing.

# How to solve this pattern?

Here's the general approach to solve this pattern:

We move from index 0 to n - 1 and see whether to take or not take the current index element.

1. Represent the problem in the form of indices. You'll need two indices here, one for the current index and one for the previous index (the last index from where the element was picked in the subsequence).
2. Explore all the possibilities that can happen at the current index. There are two possibilities - pick and not pick.
3. Take the best scenario, like take max or sum or whatever is required.
4. The base case will be when the current index becomes equal to the length of the array. In that case, we return 0.

Here's the pseudo code for the recursive approach -

```
f(ind, prev_index){

    if(ind == n)
        return 0

    notTake = 0 + f(ind + 1, prev_index)

    if(prev_index == -1 || arr[ind]>arr[prev_index]){
        take = 1 + f(ind + 1, ind)
    }

    return max(notTake, take)
}
```

NOTE: We will begin with the previous index as -1 because we are not considering any element as the previous index in the beginning.

## Time and Space Complexity of the recursive approach

- Time complexity: O(2^n)
- Space complexity: O(n)

# Using memoization to optimize the recursive approach

In order to memoize the recursive solution, we'll need a two-dimensional array. If the array has length N, then we'll need a 2D array of size N x (N + 1). Here, N represents the current index and (N + 1) represents the previous index. Because previous index involves -1, so we need to right shift the array by 1. Hence, what was -1 is now 0, what was 0 is now 1, and so on.

Here's the code for the memoized approach -

```
// Function to find the length of the longest increasing subsequence
    static int getAns(int arr[], int n, int ind, int prev_index, int[][] dp) {
        // Base condition
        if (ind == n) {
            return 0;
        }

        if (dp[ind][prev_index + 1] != -1) {
            return dp[ind][prev_index + 1];
        }

        int notTake = 0 + getAns(arr, n, ind + 1, prev_index, dp);

        int take = 0;

        if (prev_index == -1 || arr[ind] > arr[prev_index]) {
            take = 1 + getAns(arr, n, ind + 1, ind, dp);
        }

        dp[ind][prev_index + 1] = Math.max(notTake, take);

        return dp[ind][prev_index + 1];
    }
```

NOTE: Do not forget to fill all the array elements with -1.

## Time and Space Complexity of the memoized approach

- Time complexity: O(n^2)
- Space complexity: O(n^2) + O(n)
