// 剑指 Offer 14- I. 剪绳子
/*
  根据一些不成文的数学推导，有说绳长为3是最优的
  但是我还是觉得我的dp是令人信服的
  不过它是O(n ^ 2)
  就这样吧，没办法优化
  执行用时：1 ms, 在所有 Java 提交中击败了43.30%的用户
*/
class Solution {
    public int cuttingRope(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (i != n) {
                dp[i] = i;
            }
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], j * dp[i - j]);
            }
        }
        return dp[n];
    }
}
