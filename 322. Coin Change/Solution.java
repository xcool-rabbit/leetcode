// 322. 零钱兑换
/*
  动态规划问题
  最优解来自于前面的最优解再加一个硬币
  看了一下题解，思路跟我的是一样的，可能是我代码写矬了？
  执行用时：17 ms, 在所有 Java 提交中击败了50.37%的用户
*/
class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        int[] dp = new int[amount + 1];
        for (int i = 1; i < dp.length; i++) {
            int cur = Integer.MAX_VALUE;
            for (int c : coins) {
                if (i == c) {
                    cur = 1;
                    break;
                } else if (i > c){
                    if (dp[i - c] != -1) {
                        cur = dp[i - c] + 1 < cur ? dp[i - c] + 1 : cur;
                    }
                }
            }
            dp[i] = cur == Integer.MAX_VALUE ? -1 : cur;
        }
        return dp[amount];
    }
}
