// 338. 比特位计数
/*
  同LCOFII-3
  执行用时：1 ms, 在所有 Java 提交中击败了99.98%的用户
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
