//面试题 01.08. Zero Matrix LCCI
/*
  找到所有0元素的位置，记录下来
  再跟进位置将0元素的所在行清零
  突发奇想用了个方法内部类，表示坐标
  执行用时：2 ms 已经战胜 49.55 % 的 java 提交记录
*/
class Solution {
    public void setZeroes(int[][] matrix) {
        class Coordinate {
            public int x;
            public int y;
            public Coordinate(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
        List<Coordinate> coordinateList = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    coordinateList.add(new Coordinate(i, j));
                }
            }
        }
        for (Coordinate c : coordinateList) {
            int x = c.x;
            int y = c.y;
            for (int i = 0; i < matrix[x].length; i++) {
                matrix[x][i] = 0;
            }
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][y] = 0;
            }
        }
    }
}
/*
  第一种方法速度不理想，原因可能是内部类有很大的开销
  经过分析，在第一遍找0的时候，就可以直接将这一行变为0
  只需要记录列的情况即可
  速度很快，就是从代码的可读性上要差一点
  执行用时：1 ms 已经战胜 100.00 % 的 java 提交记录
*/
class Solution {
    public void setZeroes(int[][] matrix) {
        List<Integer> colList = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            boolean line = false;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    line = true;
                    colList.add(j);
                }
            }
            if (line) {
                for (int j = 0; j < matrix[i].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (Integer col : colList) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][col] = 0;
            }
        }
    }
}
