//121.买卖股票的最佳时机
/*
  以为似曾相识，这题做过，事实上并不是一道题，很迷惑
  我按照跟之前一模一样的思想，找到最低点和最高点们，存储到一个数组里
  放到数组里是避免重复的计算
  其实我觉得这样只是稍微处理了一下，n^2变成了m^2
  这道题被放在了动态规划里面，并且我这种做法很慢
  我觉得需要去看一下评论里的做法
  执行用时：154 ms 已经战胜 24.55 % 的 java 提交记录
*/
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        List<Integer> buySellPoint = new ArrayList<>(prices.length);
        for (int i = 0; i < prices.length; i++) {
            if (isBreak(prices, i)) {
                buySellPoint.add(prices[i]);
            }
        }
        int max = 0;
        for (int i = 0; i < buySellPoint.size(); i++) {
            int buy = buySellPoint.get(i);
            for (int j = i + 1; j < buySellPoint.size(); j++) {
                if (buySellPoint.get(j) - buy > max) {
                    max = buySellPoint.get(j) - buy;
                }
            }
        }
        return max;
    }
    
    private boolean isBreak(int[] prices, int index) {
        if (index == 0) {
            return prices[index] <= prices[index + 1];
        } else if (index == prices.length - 1) {
            return prices[index] >= prices[index - 1];
        }
        
        if (prices[index - 1] <= prices[index] && prices[index] >= prices[index + 1]) {
            return true;
        }
        if (prices[index - 1] >= prices[index] && prices[index] <= prices[index + 1]) {
            return true;
        }
        return false;
    }
}
/*
  我明知道这个题被放在了动态规划的分类里面，但是我就是不知道应该怎么用
  但当我看到评论的第一条，简直是豁然开朗：
  前i天的最大收益 = max{前i-1天的最大收益，第i天的价格-前i-1天中的最小价格}
  执行用时：2 ms 已经战胜 88.47 % 的 java 提交记录
*/
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int[] profit = new int[prices.length];
        profit[0] = 0;
        int minIndex = 0;
        for (int i = 1; i < profit.length; i++) {
            profit[i] = profit[i - 1] > prices[i] - prices[minIndex] ? profit[i - 1] : prices[i] - prices[minIndex];
            minIndex = prices[i] < prices[minIndex] ? i : minIndex;
        }
        return profit[profit.length - 1];
    }
}
