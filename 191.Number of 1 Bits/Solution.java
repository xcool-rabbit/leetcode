//191. 位1的个数
/*
  取模除以二
  要注意的是Java是没有无符号数的，所以需要将补码转化为原码
  将负数的值加上两个2 ^ 30，就可以将补码的符号位去掉
  为什么不加2 ^ 31，因为超int了
  细节上还可以优化的点比如取模可以& 1，/= 2可以>>> 1
  不过我相信现代编译器应该能帮我做这件事，嘻嘻（说白了就是懒）
  执行用时：1 ms 已经战胜 99.79 % 的 java 提交记录
*/
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        if (n < 0) {
            n += (int) Math.pow(2, 30);
            n += (int) Math.pow(2, 30);
            count++;
        }
        while (n > 0) {
            count += n % 2;
            n /= 2;
        }
        return count;
    }
}
