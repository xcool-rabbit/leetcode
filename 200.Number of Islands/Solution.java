//200. 岛屿数量
/*
  1通过上下左右相连，就形成了岛屿。问题就是在问有多少块儿这种相连的。
  BFS或者DFS，就能遍历一块儿岛屿。访问一个点过后就将它标记为0，保证不会重复访问。
  性能优化：在将节点入栈的时候就将它标记为0。
  执行用时：18 ms 已经战胜 9.99 % 的 java 提交记录
*/
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int numIslands(char[][] grid) {
        int num = 0;
        int[] start;
        Queue<int[]> queue = new LinkedList<>();
        while ((start = findFirstOne(grid)) != null) {
            queue.offer(start);
            grid[start[0]][start[1]] = '0';
            while (!queue.isEmpty()) {
                int[] tmp = queue.poll();
                int tmpX = tmp[0];
                int tmpY = tmp[1];
                if (!isOut(tmpX - 1, tmpY, grid) && grid[tmpX - 1][tmpY] == '1') {
                    queue.offer(new int[]{tmpX - 1, tmpY});
                    grid[tmpX - 1][tmpY] = '0';
                }
                if (!isOut(tmpX, tmpY - 1, grid) && grid[tmpX][tmpY - 1] == '1') {
                    queue.offer(new int[]{tmpX, tmpY - 1});
                    grid[tmpX][tmpY - 1] = '0';
                }
                if (!isOut(tmpX, tmpY + 1, grid) && grid[tmpX][tmpY + 1] == '1') {
                    queue.offer(new int[]{tmpX, tmpY + 1});
                    grid[tmpX][tmpY + 1] = '0';
                }
                if (!isOut(tmpX + 1, tmpY, grid) && grid[tmpX + 1][tmpY] == '1') {
                    queue.offer(new int[]{tmpX + 1, tmpY});
                    grid[tmpX + 1][tmpY] = '0';
                }
            }
            num++;
        }
        return num;
    }

    private int[] findFirstOne(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    private boolean isOut(int x, int y, char[][] grid) {
        if (0 <= x && x < grid.length && 0 <= y && y < grid[0].length) {
            return false;
        }
        return true;
    }
}
