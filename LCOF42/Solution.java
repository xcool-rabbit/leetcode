// 剑指 Offer 42. 连续子数组的最大和
/*
  求前缀和，然后就转变成了股票买卖时机的问题
  不同点在于，首先单独的最大值是成立的，可以不跟别人减
  sum[i] < min的时候，仍然得先作差跟ret去比，原因就是上面那一行，ret可以不跟别人减
  执行用时：1 ms, 在所有 Java 提交中击败了55.70%的用户
*/
class Solution {
    public int maxSubArray(int[] nums) {
        int[] sum = new int[nums.length];
        int cur = 0;
        int ret = nums[0];
        for (int i = 0; i < nums.length; i++) {
            cur += nums[i];
            sum[i] = cur;
            ret = Math.max(cur, ret);
        }
        int min = sum[0];
        for (int i = 1; i < sum.length; i++) {
            if (sum[i] - min > ret) {
                ret = sum[i] - min;
            }
            if (sum[i] < min) {
                min = sum[i];
            }
        }
        return ret;
    }
}
/*
  鉴于第一个方法虽然也是O(n)，但是没有超过及格的人数
  及格的应该是一遍遍历
  那就只能用dp了
  这个题的dp我觉得正常人想不到，但是我对这种dp印象极深
  dp[i]表明，以nums[i]结尾的，最大的，连续子数组的和
  遍历一遍都不一定是0 ms，dp[i]计算那里还是做了优化才0 ms的
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int ret = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] < 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = dp[i - 1] + nums[i];
            }
            ret = Math.max(ret, dp[i]);
        }
        return ret;
    }
}
