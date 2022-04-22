// 343. 整数拆分
/*
  dp
  看代码就看明白了
  执行用时：1 ms, 在所有 Java 提交中击败了76.71%的用户
*/
class Solution {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], j * Math.max(i - j, dp[i - j]));
            }
        }
        return dp[n];
    }
}
