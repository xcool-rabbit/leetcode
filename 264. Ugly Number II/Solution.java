// 264. 丑数 II
/*
  同LCOF49
  执行用时：1 ms, 在所有 Java 提交中击败了99.73%的用户
*/
class Solution {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int two = 0;
        int three = 0;
        int five = 0;
        int twotwo = dp[two] * 2;
        int threethree = dp[three] * 3;
        int fivefive = dp[five] * 5;
        for (int i = 1; i < n; i++) {
            int min = Math.min(twotwo, threethree);
            min = Math.min(min, fivefive);
            dp[i] = min;
            if (twotwo == min) {
                two++;
                twotwo = dp[two] * 2;
            }
            if (threethree == min) {
                three++;
                threethree = dp[three] * 3;
            }
            if (fivefive == min) {
                five++;
                fivefive = dp[five] * 5;
            }
        }
        return dp[dp.length - 1];
    }
}
