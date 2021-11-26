// 231. 2 的幂
/*
  二进制是1后面若干个0的才行
  执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        while (n != 1) {
            if (n % 2 == 1) {
                return false;
            } else {
                n = n >> 1;
            }
        }
        return true;
    }
}
/*
  进阶方法是真滴牛批！这就是美妙的位运算吗
  这种方法的功能是将最低位的0去掉
*/
class Solution {
    public boolean isPowerOfTwo(int n) {
        return (n & (n - 1)) == 0;
    }
}
/*
  进阶方法2：
  提出最低位的1
*/
class Solution {
    public boolean isPowerOfTwo(int n) {
        return (n & -n) == n;
    }
}
