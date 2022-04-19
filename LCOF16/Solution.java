// 剑指 Offer 16. 数值的整数次方
/*
  同主站50题
  递归，用n / 2加速
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public double myPow(double x, int n) {
        if (n > 0) {
            double tmp = myPow(x, n / 2);
            return n % 2 == 0 ? tmp * tmp : tmp * tmp * x;
        } else if (n == 0) {
            return 1;
        } else {
            return 1 / x * myPow(1 / x, -(n + 1));
        }
    }
}
