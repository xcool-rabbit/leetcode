// 剑指 Offer 65. 不用加减乘除做加法
/*
  需要两个额外变量，这我有点不爽
  疑惑表示不进位的
  &然后左移，表示进位的
  循环直到不需要进位为止
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int add(int a, int b) {
        int x;
        int y;
        while (b != 0) {
            x = a ^ b;
            y = (a & b) << 1;
            a = x;
            b = y;
        }
        return a;
    }
}
