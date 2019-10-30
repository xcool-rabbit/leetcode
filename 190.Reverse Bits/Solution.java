//190. 颠倒二进制位
/*
  模2，然后+= 余数，然后*= 2
  需要注意的是Java中只有补码表示的数，没有unsigned
  所以最高位的位权需要注意
  执行用时：1 ms 已经战胜 100.00 % 的 java 提交记录
*/
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int origin = n;
        int reverse = 0;
        if (n < 0) {
            n += (int) Math.pow(2, 30);
            n += (int) Math.pow(2, 30);
        }
        for (int i = 0; i < 30; i++) {
            n /= 2;
            reverse += n % 2;
            reverse *= 2;
        }
        if (Math.abs(origin % 2) == 1) {
            reverse -= (int) Math.pow(2, 30);
            reverse -= (int) Math.pow(2, 30);
        }
        if (origin < 0) {
            reverse++;
        }
        return reverse;
    }
}
