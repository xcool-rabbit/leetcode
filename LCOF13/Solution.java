// 剑指 Offer 13. 机器人的运动范围
/*
  一开始把题意理解错了
  以为只需要遍历，然后看坐标数位相加
  其实吧，两片区域之间，必须是联通的，否则机器人过不去
  所以必须真实的去遍历，否则连通性这里很难确定
  执行用时：1 ms, 在所有 Java 提交中击败了60.82%的用户
*/
class Solution {
    boolean[][] visited;
    int[][] steps = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int m = 0;
    int n = 0;
    int k = 0;
    int ret = 0;
    public int movingCount(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        visited = new boolean[m][n];
        dfs(0, 0);
        return ret;
    }

    public void dfs(int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return;
        }
        if (visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        if (digitSum(i) + digitSum(j) <= k) {
            ret++;
            for (int[] step : steps) {
                dfs(i + step[0], j + step[1]);
            }
        }
    }

    public int digitSum(int n) {
        int ret = 0;
        while (n != 0) {
            ret += n % 10;
            n /= 10;
        }
        return ret;
    }
}
/*
  进行了一点小优化，由于递增的特性，实际上遍历的时候只需要向右向下扩张即可
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    boolean[][] visited;
    int[][] steps = {{1, 0}, {0, 1}};
    int m = 0;
    int n = 0;
    int k = 0;
    int ret = 0;
    public int movingCount(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        visited = new boolean[m][n];
        dfs(0, 0);
        return ret;
    }

    public void dfs(int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return;
        }
        if (visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        if (digitSum(i) + digitSum(j) <= k) {
            ret++;
            for (int[] step : steps) {
                dfs(i + step[0], j + step[1]);
            }
        }
    }

    public int digitSum(int n) {
        int ret = 0;
        while (n != 0) {
            ret += n % 10;
            n /= 10;
        }
        return ret;
    }
}
