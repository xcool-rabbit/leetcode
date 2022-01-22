// 50. Pow(x, n)
/*
  递归，然后发现栈溢出了
  更改递归策略，每次递归，n / 2
  还有一个要注意的策略，就是取反的时候int的值的问题
  -2 ^ 31取反变成2 ^ 31会超int，做了特殊处理
  执行用时：0 ms 已经战胜 100.00 % 的 java 提交记录
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
