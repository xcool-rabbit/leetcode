//122. 买卖股票的最佳时机 II
/*
  在递减的最低点买入，在递增的最高点卖出
*/
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length > 1) {
            int buy = 0;
            int sell = 1;
            int profit = 0;
            while (sell < prices.length) {
                while (buy + 1 < prices.length && prices[buy] > prices[buy + 1]) {
                    buy++;
                }
                sell = buy;
                while (sell + 1 < prices.length && prices[sell] < prices[sell + 1]) {
                    sell++;
                }
                if (sell < prices.length && buy <= sell) {
                    profit += prices[sell] - prices[buy];
                    buy = sell + 1;
                }
            }
            return profit;
        }
        else
            return 0;
    }
}
