// 剑指 Offer 63. 股票的最大利润
/*
  讲道理是用dp的
  O(n)的方法就是max和min数组
  max是倒着遍历并且赋值的，意为从今天往后的最大值
  然后我再正着遍历，维护一个变量，表示能买入的最小值
  遍历时，因为我们有max数组，所以我们知道未来的最大值，所以我们就能知道，如果今天买入，最大能赚多少钱
  也是O(n)，挺好的，没必要dp
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int maxProfit(int[] prices) {
        int[] min = new int[prices.length];
        int[] max = new int[prices.length];
        int m = 0;
        for (int i = prices.length - 1; i >= 0; i--) {
            m = Math.max(m, prices[i]);
            max[i] = m;
        }
        int ret = -m;
        for (int i = 0; i < prices.length; i++) {
            m = Math.min(m, prices[i]);
            ret = Math.max(ret, max[i] - m);
        }
        return ret;
    }
}
