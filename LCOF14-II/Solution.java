// 剑指 Offer 14- II. 剪绳子 II
/*
  我嗯不用那个乘以3的结论
  原来Java还有BigInteger
  这题用long都不行
  执行用时：168 ms, 在所有 Java 提交中击败了5.04%的用户
*/
import java.math.*;
class Solution {
    public int cuttingRope(int n) {
        BigInteger[] dp = new BigInteger[n + 1];
        Arrays.fill(dp, BigInteger.valueOf(0));
        dp[1] = BigInteger.valueOf(1);
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = dp[i].max(BigInteger.valueOf(i - j).max(dp[i - j]).multiply(BigInteger.valueOf(j)));
            }
        }
        return dp[n].mod(BigInteger.valueOf(1000000007)).intValue();
    }
}
