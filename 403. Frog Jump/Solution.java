// 403. 青蛙过河
/*
  回溯剪枝 + 记忆化搜索
  记忆化搜索这个应该成为以后经常用到的优化手段
  回溯里面有一些是重复的检索
  存起来就能避免冗余
  但是这个题的缓存还是有点妙的，step这里究竟要设置多长呢
  因为青蛙的步数每次只会增长一步，所以它的最大步幅也就stone.length这么长
  妙啊
  看到回溯其实我就想起回溯的题基本上都能用dp做，但是我没想出来
  一个关键的因素就是k这里的设置
  知道k是有限的了以后，怎么填dp就很简单了
  不想实现了，我这个提交已经很优秀了
  执行用时：9 ms, 在所有 Java 提交中击败了97.63%的用户
*/
class Solution {
    int[] stones;
    int[] dp;
    boolean[][] visited;
    public boolean canCross(int[] stones) {
        if (stones[1] != 1) {
            return false;
        }
        this.stones = stones;
        this.dp = new int[stones.length];
        this.visited = new boolean[stones.length][stones.length];
        dp[0] = 0;
        dp[1] = 1;
        recursion(1);
        return dp[dp.length - 1] != 0;
    }

    public void recursion(int curIndex) {
        for (int i = 1; i >= -1; i--) {
            int step = dp[curIndex] + i;
            if (step == 0) {
                continue;
            }
            if (step >= stones.length || visited[curIndex][step]) {
                continue;
            }
            int nextIndex = Arrays.binarySearch(stones, stones[curIndex] + step);
            if (nextIndex < 0) {
                continue;
            }
            dp[nextIndex] = step;
            if (dp[dp.length - 1] != 0) {
                return;
            }
            recursion(nextIndex);
            visited[nextIndex][step] = true;
            if (dp[dp.length - 1] != 0) {
                return;
            }
            dp[nextIndex] = 0;
        }
    }
}