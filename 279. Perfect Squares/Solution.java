//279. 完全平方数
/*
  很像一个背包问题，但是一开始没敢想，毕竟还从来没有独立使用过
  这个题被放在了BFS里面，潜意识里面会去往那个方向去想
  用完全平方数去凑一个指定的和，一开始想的是加的方法，但是发现不太好表达，于是改成了减法，变得自然多了
  以12为例：12入队，12出队，11,8,3入队（用1,4,9）减，直到遇到0为止
  LTE
  想想优化的思路，队列里面存在重复的情况。比如有一个一直用1去减的，在他的那条路径就会遇见8，会重复的计算之前已经得出的结果
  于是增加了一个去重的逻辑
  执行用时：85 ms 已经战胜 23.42 % 的 java 提交记录
*/
class Solution {
    public int numSquares(int n) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);
        Queue<Integer> level = new LinkedList<>();
        Set<Integer> hasArrived = new HashSet<>();
        int count = 0;
        while (!(queue.isEmpty() && level.isEmpty())) {
            if (!queue.isEmpty()) {
                int cur = queue.poll();
                List<Integer> list = getPerfectSquare(cur);
                for (Integer i : list) {
                    if (cur == i) {
                        return count + 1;
                    }
                    if (!hasArrived.contains(cur - i)) {
                        level.offer(cur - i);
                        hasArrived.add(cur - i);
                    }
                }
            } else {
                queue = level;
                level = new LinkedList<>();
                count++;
            }
        }
        return n;
    }
    
    private List<Integer> getPerfectSquare(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            list.add(i * i);
        }
        return list;
    }
}
/*
  dp
  f(n) = min(f(i * i) + f(n - i * i)) i循环（f(i *i)就是1）
  执行用时：40 ms 已经战胜 79.13 % 的 java 提交记录
*/
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = i;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = dp[i - j * j] + 1 < dp[i] ? dp[i - j * j] + 1 : dp[i];
            }
        }
        return dp[n];
    }
}
