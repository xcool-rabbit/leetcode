// 剑指 Offer II 001. 整数除法
/*
  同主站29
  我采用了加速法，除数每次翻一倍
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int divide(int a, int b) {
        int dividend = a;
        int divisor = b;
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
