// 64. 最小路径和
/*
  该点最小路径和等于，min(从上面来的， 从左面来的)
  一开始写的超时了，原因是没有存储运算的中间值
  其实这个题正着做也能做，但是我觉得有点麻烦，倒着好写
  执行用时：1 ms, 在所有 Java 提交中击败了99.34%的用户
*/
class Solution {
    int[][] dp;
    public int minPathSum(int[][] grid) {
        dp = new int[grid.length][grid[0].length];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return minPathSum(grid, grid.length - 1, grid[0].length - 1);
    }

    public int minPathSum(int[][] grid, int row, int col) {
        if (row == 0 && col == 0) {
            dp[row][col] = grid[row][col];
            return grid[row][col];
        }
        int left = Integer.MAX_VALUE;
        if (col - 1 >= 0) {
            left = dp[row][col - 1];
            if (left == -1) {
                left = minPathSum(grid, row, col - 1);
            }
        }
        int top = Integer.MAX_VALUE;
        if (row - 1 >= 0) {
            top = dp[row - 1][col];
            if (top == -1) {
                top = minPathSum(grid, row - 1, col);
            }
        }
        dp[row][col] = grid[row][col] + Math.min(left, top);
        return grid[row][col] + Math.min(left, top);
    }
}
