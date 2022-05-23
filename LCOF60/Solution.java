// 剑指 Offer 60. n个骰子的点数
/*
  一开始想用回溯来枚举所有可能的情况
  后来发现，回溯重复太多了，不如用dp
  然后就掌握了转移方程，取上一行的左边6个之和
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public double[] dicesProbability(int n) {
        double[] ret = new double[5 * n + 1];

        int[][] dp = new int[n + 1][6 * n + 1];
        dp[0][0] = 1;
        for (int i = 1; i < dp.length; i++) {
            int sum = 0;
            for (int j = 1; j < dp[i].length; j++) {
                if (j > 6) {
                    sum -= dp[i - 1][j - 7];
                }
                sum += dp[i - 1][j - 1];
                dp[i][j] = sum;
            }
        }

        int total = (int)Math.pow(6, n);
        for (int i = 0; i < ret.length; i++) {
            ret[i] = (double)dp[n][i + n] / total;
        }
        return ret;
    }
}
