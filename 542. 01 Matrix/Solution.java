//542. 01 矩阵
/*
  广度遍历即可计算最近的0的距离
  求最近的问题基本上都需要广度遍历
  不是特别快，因为没有visited数组，也差不多，懒得改了
  执行用时：16 ms 已经战胜 61.75 % 的 java 提交记录
*/
class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        int[][] ans = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                ans[i][j] = findZero(matrix, i, j);
            }
        }
        return ans;
    }
    
    private int findZero(int[][] matrix, int i, int j) {
        class Point {
            int i;
            int j;
            
            Point(int i, int j) {
                this.i = i;
                this.j = j;
            }
        }
        Queue<Point> queue = new LinkedList<>();
        Queue<Point> nextLevel = new LinkedList<>();
        int step = 0;
        if (matrix[i][j] == 0) {
            return step;
        }
        queue.offer(new Point(i, j));
        while (true) {
            if (!queue.isEmpty()) {
                Point tmp = queue.poll();
                i = tmp.i;
                j = tmp.j;
                if (!isOutOfBounds(matrix, i - 1, j)) {
                    if (matrix[i - 1][j] == 0) {
                        return step + 1;
                    } else {
                        nextLevel.offer(new Point(i - 1, j));
                    }
                }
                if (!isOutOfBounds(matrix, i + 1, j)) {
                    if (matrix[i + 1][j] == 0) {
                        return step + 1;
                    } else {
                        nextLevel.offer(new Point(i + 1, j));
                    }
                }
                if (!isOutOfBounds(matrix, i, j - 1)) {
                    if (matrix[i][j - 1] == 0) {
                        return step + 1;
                    } else {
                        nextLevel.offer(new Point(i, j - 1));
                    }
                }
                if (!isOutOfBounds(matrix, i, j + 1)) {
                    if (matrix[i][j + 1] == 0) {
                        return step + 1;
                    } else {
                        nextLevel.offer(new Point(i, j + 1));
                    }
                }
            } else {
                queue = nextLevel;
                nextLevel = new LinkedList<>();
                step++;
            }
        }
    }
    
    private boolean isOutOfBounds(int[][] matrix, int i, int j) {
        return i < 0 || i >= matrix.length || j < 0 || j >= matrix[i].length;
    }
}
