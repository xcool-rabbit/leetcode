// 剑指 Offer 47. 礼物的最大价值
/*
  平平无奇的dp，为什么这么多人比我快呢
  执行用时：4 ms, 在所有 Java 提交中击败了26.84%的用户
*/
class Solution {
    public int[][] grid;
    public int m;
    public int n;
    public int maxValue(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int left = 0;
                if (!outOfBounds(i, j - 1)) {
                    left = dp[i][j - 1];
                }
                int up = 0;
                if (!outOfBounds(i - 1, j)) {
                    up = dp[i - 1][j];
                }
                dp[i][j] = grid[i][j] + Math.max(left, up);
            }
        }
        return dp[m - 1][n - 1];
    }

    public boolean outOfBounds(int i, int j) {
        return i < 0 || i >= m || j < 0 || j >= n;
    }
}
/*
  通过冗余一行一列dp，避免了越界判断，不错
  执行用时：2 ms, 在所有 Java 提交中击败了98.30%的用户
*/
class Solution {
    public int maxValue(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                dp[i][j] = grid[i - 1][j - 1] + Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[m][n];
    }
}
