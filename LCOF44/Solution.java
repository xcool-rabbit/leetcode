// 剑指 Offer 44. 数字序列中某一位的数字
/*
  照着减
  然后定位到那个数字
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int findNthDigit(int n) {
        int num = 9;
        int len = 1;
        while (len <= 8 && n - num * len > 0) {
            // System.out.println(n + " " + len);
            n -= num * len;
            num *= 10;
            len++;
        }
        int offset = (n - 1) / len;
        return String.valueOf(num / 9 + offset).charAt((n - 1) % len) - '0';
    }
}
