// 69. Sqrt(x)
/*
  通过二分的方式，在0-x之间，找到他的平方根
  注意平方运算后溢出的可能性，所以限制了right的最大值
  说一下优化，右端的起点可以不是x的，比如x / 2就能大大缩小范围
  但是，对于二分来讲，这也不过就是一次循环的事儿，影响并不大，所以没必要这样优化
  执行用时：1 ms, 在所有 Java 提交中击败了97.81%的用户
*/
class Solution {
    public int mySqrt(int x) {
        int left = 0;
        int right = x > Math.pow(2, 15.5) ? (int)Math.pow(2, 15.5) : x;
        int mid = left + (right - left) / 2;
        int ret = mid;
        while (left <= right) {
            int tmp = mid * mid;
            if (tmp < x) {
                ret = mid;
                left = mid + 1;
                mid = left + (right - left) / 2;
            } else if (tmp == x) {
                return mid;
            } else {
                right = mid - 1;
                mid = left + (right - left) / 2;
            }
        }
        return ret;
    }
}
