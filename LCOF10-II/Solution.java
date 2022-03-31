// 剑指 Offer 10- II. 青蛙跳台阶问题
/*
  这个题，就跟LCOF10-I一模一样，还说跟主站一样呢。。。主站不需要取模
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%S的用户
*/
class Solution {
    public int numWays(int n) {
        switch (n) {
            case 0:
                return 1;
            case 1:
                return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }
        return dp[dp.length - 1];
    }
}
