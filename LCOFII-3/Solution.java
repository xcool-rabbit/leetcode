// 剑指 Offer II 003. 前 n 个数字二进制中 1 的个数
/*
  每一个数去计算1的个数，谁都会
  这个题的进阶要求一次遍历
  我找到了规律：4-7的结果就是0-3的结果，然后再+1
  用二进制想也很容易想明白
  实现的话，就用两个指针
  执行用时：1 ms, 在所有 Java 提交中击败了99.90%的用户
*/
class Solution {
    public int[] countBits(int n) {
        int head = 0;
        int tail = 1;
        int[] ret = new int[n + 1];
        ret[0] = 0;
        int headEnd = 0;
        while (tail <= n) {
            while (head < headEnd && tail <= n) {
                ret[tail++] = ret[head++] + 1;
            }
            head = 0;
            headEnd = tail;
        }
        return ret;
    }
}
