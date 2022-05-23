// 剑指 Offer 17. 打印从1到最大的n位数
/*
  。。。这题没啥意思
  执行用时：1 ms, 在所有 Java 提交中击败了74.30%的用户
*/
class Solution {
    public int[] printNumbers(int n) {
        int[] ret = new int[(int)Math.pow(10, n) - 1];
        for (int i = 0 ; i < ret.length; i++) {
            ret[i] = i + 1;
        }
        return ret;
    }
}
