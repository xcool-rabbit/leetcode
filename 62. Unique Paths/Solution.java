// 62. 不同路径
/*
  经典dp
  dp[i][j] = dp[i][j - 1] + dp[i - 1][j]
  然后处理一下越界就可以了
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (j - 1 >= 0) {
                    dp[i][j] += dp[i][j - 1];
                }
                if (i - 1 >= 0) {
                    dp[i][j] += dp[i - 1][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
