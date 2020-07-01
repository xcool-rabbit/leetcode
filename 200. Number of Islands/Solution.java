//200. 岛屿数量
/*
  遇到岛屿，扩散遍历
  遍历的方式，深度和广度都是可以的
  由于我遇见这道题的时候被分到了广度的专栏里，于是就用的广度遍历做的
  广度遍历个人更喜欢的还是用队列
  自身有可优化的地方，不过最重要的一点是递归比自己用数据结构要快
  执行用时：8 ms 已经战胜 9.38 % 的 java 提交记录
*/
class Solution {
    public int numIslands(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        class Coordinate {
            int x;
            int y;
            
            Coordinate(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
        Queue<Coordinate> queue = new LinkedList<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int count = 0;
        boolean finish = false;
        while (true) {
            finish = true;
            boolean exitLoop = false;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (!visited[i][j]) {
                        visited[i][j] = true;
                        finish = false;
                        if (grid[i][j] == '1') {
                            queue.add(new Coordinate(i, j));
                            count++;
                            exitLoop = true;
                            break;
                        }
                    }
                }
                if (exitLoop) {
                    break;
                }
            }
            if (finish) {
                break;
            }
            while (!queue.isEmpty()) {
                Coordinate c = queue.poll();
                int x = c.x;
                int y = c.y;
                if (!hasVisited(visited, x - 1, y) && grid[x - 1][y] == '1') {
                    queue.offer(new Coordinate(x - 1, y));
                    visited[x - 1][y] = true;
                }
                if (!hasVisited(visited, x + 1, y) && grid[x + 1][y] == '1') {
                    queue.offer(new Coordinate(x + 1, y));
                    visited[x + 1][y] = true;
                }
                if (!hasVisited(visited, x, y - 1) && grid[x][y - 1] == '1') {
                    queue.offer(new Coordinate(x, y - 1));
                    visited[x][y - 1] = true;
                }
                if (!hasVisited(visited, x, y + 1) && grid[x][y + 1] == '1') {
                    queue.offer(new Coordinate(x, y + 1));
                    visited[x][y + 1] = true;
                }
            }
        }
        return count;
    }
    
    boolean hasVisited(boolean[][] visited, int x, int y) {
        return x < 0 || x >= visited.length || y < 0 || y >= visited[x].length || visited[x][y];
    }
}
/*
  当然，上面那种方法除了“硬伤”之外，也不是没有可以优化的地方
  visited数组完全不需要，遍历过的置0即可
  也不需要break，顺着遍历就可以
  经过了一些小的优化之后，性能提升依然不是很明显（绝望）（我还试着把内部类删了，还是很慢）
  执行用时：7 ms 已经战胜 12.72 % 的 java 提交记录
*/
class Solution {
    public int numIslands(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        class Coordinate {
            int x;
            int y;
            
            Coordinate(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
        Queue<Coordinate> queue = new LinkedList<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    queue.add(new Coordinate(i, j));
                    count++;
                    while (!queue.isEmpty()) {
                        Coordinate c = queue.poll();
                        int x = c.x;
                        int y = c.y;
                        if (!notIsland(grid, x - 1, y)) {
                            queue.offer(new Coordinate(x - 1, y));
                            grid[x - 1][y] = '0';
                        }
                        if (!notIsland(grid, x + 1, y)) {
                            queue.offer(new Coordinate(x + 1, y));
                            grid[x + 1][y] = '0';
                        }
                        if (!notIsland(grid, x, y - 1)) {
                            queue.offer(new Coordinate(x, y - 1));
                            grid[x][y - 1] = '0';
                        }
                        if (!notIsland(grid, x, y + 1)) {
                            queue.offer(new Coordinate(x, y + 1));
                            grid[x][y + 1] = '0';
                        }
                    }
                }
            }
        }
        return count;
    }
    
    boolean notIsland(char[][] grid, int x, int y) {
        return x < 0 || x >= grid.length || y < 0 || y >= grid[x].length || grid[x][y] == '0';
    }
}
/*
  终极解法终于来了：
  DFS，递归实现
  执行用时：2 ms 已经战胜 97.43 % 的 java 提交记录
*/
class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }
    
    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == '0') {
            return;
        }
        
        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
}
