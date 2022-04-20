// 剑指 Offer 15. 二进制中1的个数
/*
  参见主站191
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }
}
