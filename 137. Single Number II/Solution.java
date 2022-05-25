// 137. 只出现一次的数字 II
/*
  同LCOFII-4
  位运算，无敌！
  执行用时：1 ms, 在所有 Java 提交中击败了89.88%的用户
*/
class Solution {
    public int singleNumber(int[] nums) {
        int a = 0;
        int b = 0;
        for (int n : nums) {
            int tmp = ~a & b & n | a & ~b & ~n;
            b = ~a & ~b & n | ~a & b & ~n;
            a = tmp;
        }
        return b;
    }
}
