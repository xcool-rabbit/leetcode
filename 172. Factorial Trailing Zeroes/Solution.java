// 172. 阶乘后的零
/*
  这题也能当中等啊，LeetCode就这数学水平？还是说，，，美国站就这数学水平？
  答案有0是因为有2 * 5
  然而，2显然比5要多的多，所以只需要统计5的数量就可以了
  需要注意的是25有两个5
  所以一直除以5就行了
  除一次加一次
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int trailingZeroes(int n) {
        int ret = 0;
        while (n > 0) {
            ret += n / 5;
            n /= 5;
        }
        return ret;
    }
}
