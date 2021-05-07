// 1720. 解码异或后的数组
/*
  基本功嗷，要推导一下如何才能算出来
  用到了两个性质，异或的交换性和逆运算，逆运算还是异或
  有了这两个性质，列个式子，就明白是怎么算的了
  程序还有一点点的优化空间，就是没有必要重新开一个数组，在原数组上也能操作，不过Java的数组不能变长，所以没法这样优化
  题是简单题，前提是你要知道这个性质，难不在编码上，在基本功上
  执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int[] decode(int[] encoded, int first) {
        int[] answer = new int[encoded.length + 1];
        answer[0] = first;
        for (int i = 0; i < encoded.length; i++) {
            answer[i + 1] = encoded[i] ^ answer[i];
        }
        return answer;
    }
}
