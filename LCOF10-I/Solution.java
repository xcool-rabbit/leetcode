// 剑指 Offer 10- I. 斐波那契数列
/*
  dp
  不过这个题要求取模
  就是因为太大了
  当然你可以精准的只取几次模
  不过我都取了
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int fib(int n) {
        switch (n) {
            case 0:
                return 0;
            case 1:
                return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }
        return dp[dp.length - 1];
    }
}
