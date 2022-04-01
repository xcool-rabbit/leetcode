// 剑指 Offer 46. 把数字翻译成字符串
/*
  动态规划
  dp[i]等于dp[i - 1] + dp[i - 2]
  不过dp[i - 2]是有条件的
  dp[i - 1]表示，前面的方案都ok，在最后补一个单独的字符
  dp[i - 2]表示，i - 2的方案都ok，但是s[i - 1]和s[i - 2]能组合在一起
  要注意的是0，s[i - 1]如果是0的话，是没有办法跟后面的组合的
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int translateNum(int num) {
        String s = String.valueOf(num);
        int[] dp = new int[s.length()];
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = dp[i - 1];
            if (s.charAt(i - 1) != '0' && Integer.parseInt(s.substring(i - 1, i + 1)) <= 25) {
                if (i > 1) {
                    dp[i] += dp[i - 2];
                } else {
                    dp[i]++;
                }
            }
        }
        return dp[dp.length - 1];
    }
}
