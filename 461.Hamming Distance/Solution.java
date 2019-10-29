//461. 汉明距离
/*
  异或，然后Integer.bitCount
  由于昨天刚刚用模2的方法计算过一个数的二进制表示中所含1的个数，
  所以今天就不愿意写了，直接调API，并且人家这个API用的是位操作，理论上应该比我模2要快吧，要不然也不可能进库啊
  执行用时：1 ms 已经战胜 82.34 % 的 java 提交记录
*/
class Solution {
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}
