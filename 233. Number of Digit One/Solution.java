// 233. 数字 1 的个数
/*
  同LCOF43
  执行用时：6 ms, 在所有 Java 提交中击败了100.00%的用户
*/
/*
1-9 1
10-99 19
*/
class Solution {
    public int countDigitOne(int n) {
        System.out.println("n: " + n);
        if (n == 0) {
             return 0;
        }
        String s = String.valueOf(n);
        int length = s.length();
        if (length == 1) {
            return 1;
        }
        int[] dp = new int[length - 1];
        dp[0] = 1;
        int tmp = 10;
        for (int i = 1; i < dp.length; i++) {
            if (n / 10 < tmp) {
                break;
            }
            dp[i] = dp[i - 1] * 10 + tmp;
            tmp *= 10;
        }
        // System.out.println(Arrays.toString(dp));
        int ret = dp[length - 2];
        int next = Integer.parseInt(s.substring(1));
        if (s.charAt(0) == '1') {
            ret += (next + 1);
        } else {
            ret *= (s.charAt(0) - '0');
            ret += tmp;
        }
        ret += countDigitOne(next);
        return ret;
    }
}
