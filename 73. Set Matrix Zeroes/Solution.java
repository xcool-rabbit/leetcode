// 73. 矩阵置零
/*
  找到所有0的位置，都记录下来，然后下一遍遍历的时候全改掉，这是我最初的想法
  但是这样仍然不能满足O(1)的额外空间消耗
  我已经在想能不能在数字上动手脚了，但是这个题nums[i][j]是整个int范围，没办法利用
  然后我就寄了
  看了题解发现我呆呆的
  题解把第一行第一列用于记录这一行/列的0的状况
  （前提是先确定第一行/列有没有0）
  然后再根据第一行和第一列的信息去置0
  把额外的记录空间放到了数组里
  执行用时：1 ms, 在所有 Java 提交中击败了96.18%的用户
*/
class Solution {
    public void setZeroes(int[][] matrix) {
        boolean firstRow = false;
        boolean firstCol = false;
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                firstRow = true;
                break;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                firstCol = true;
                break;
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for (int i = 1; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 1; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < matrix[i].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (firstRow) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }
        if (firstCol) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
