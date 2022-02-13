// 29. 两数相除
/*
  每次减divisor，然后答案++
  这是最初级的做法
  但是这样太慢了，会超时，我们得想办法加速
  divisor无限翻倍！
  但是需要记录余数
  另外一个以前看到的小技巧是开头的这些递归，可以很轻松的改变符号，顺序什么的，省去了很多的判断
  最后一个麻烦的地方是溢出
  这里可以复习一下补码
  一个数的相反数是各位取反加一
  有符合32位int的范围是-2 ^ 31 ~ 2 ^ 31 - 1
  -2 ^ 31是一个很难处理的数，它取反加一还是它自己，导致我的递归无限进行，需要针对它单独处理
  小case是先保证divisor是正的，这样在dividend处理-2 ^ 31的时候省去了很多判断
  还有一个题目规定的是如果溢出，则返回2 ^ 31 - 1,
  溢出只有一种可能，就是-2 ^ 31 / -1
  可以针对这种情况单独处理
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == -dividend && divisor == -1) {
            return dividend - 1;
        }
        if (divisor < 0) {
            if (divisor == -divisor) {
                return dividend == -dividend ? 1 : 0;
            }
            return -divide(dividend, -divisor);
        }

        if (dividend < 0) {
            if (dividend == -dividend) {
                return -1 + divide(dividend + divisor, divisor);
            }
            return -divide(-dividend, divisor);
        }
        return divideCalc(dividend, divisor)[0];
    }
    public int[] divideCalc(int dividend, int divisor) {
        int[] ret = new int[2];
        if (divisor + divisor > divisor && dividend >= divisor + divisor) {
            int[] ancestor = divideCalc(dividend, divisor + divisor);
            ret[0] = ancestor[0] + ancestor[0];
            ret[1] = ancestor[1]; // ret[1] is remainder
        } else {
            ret[1] = dividend;
        }
        while (ret[1] >= divisor) {
            ret[1] -= divisor;
            ret[0]++;
        }
        return ret;
    }
}
