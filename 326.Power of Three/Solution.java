//326. 3的幂
/*
  利用循环，如果能整除3的，就一直除，除到最后剩下1的就是3的幂，否则不是
  执行用时：16 ms 已经战胜 80.19 % 的 java 提交记录
*/
class Solution {
  public boolean isPowerOfThree(int n) {
      if (n <= 0) {
          return false;
      }
      while (n % 3 == 0) {
          n /= 3;
      }
      return n == 1;
  }
}
/*
  进阶要求是不用循环或递归，我简直蒙了
  于是就上网搜，方法还是有很多，但是我觉得比较正常比较好理解的方法就是下面这种
  在int范围内，3的最大的幂就是3 ^ 19，只有3的幂才能整除这个数
  执行用时：15 ms 已经战胜 93.35 % 的 java 提交记录
*/
class Solution {
    public boolean isPowerOfThree(int n) {
        return n <= 0 ? false : (int) Math.pow(3, 19) % n == 0;
    }
}
