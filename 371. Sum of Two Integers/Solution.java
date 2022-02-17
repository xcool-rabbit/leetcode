// 371. 两整数之和
/*
  异或是核心运算
  异或可以模拟加法运算，唯一的缺陷就是不知道进位
  哪里应该进位，可以通过&找出来，然后左移一位
  下一次迭代就算上面这俩东西就行
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int getSum(int a, int b) {
        while ((a & b) != 0) {
            a ^= b;
            b = ((a ^ b) & b) << 1;
        }
        return a ^ b;
    }
}
