// 240. 搜索二维矩阵 II
/*
  最粗暴的做法，往右以及往下找
  往下之后需要往左回溯一下
  执行用时：5 ms, 在所有 Java 提交中击败了98.59%的用户
*/
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int i = 0;
        int j = 0;
        while (true) {
            if (matrix[i][j] < target) {
                if (j + 1 < matrix[i].length) {
                    j++;
                } else {
                    if (i + 1 < matrix.length) {
                        i++;
                    } else {
                        return false;
                    }
                    while (j > 0 && matrix[i][j] > target) {
                        j--;
                    }
                }
            } else if (matrix[i][j] == target){
                return true;
            } else {
                if (i + 1 < matrix.length) {
                    i++;
                } else {
                    return false;
                }
                while (j > 0 && matrix[i][j] > target) {
                    j--;
                }
            }
        }
    }
}
/*
  可以添加一些快速判断的东西，比如起点可以快一点，如果这一行的最后一个元素都小于target，那么这一行就可以直接跳过了
  可能是由于测试集并不明显的缘故，这样的优化没有快多少，但是我做了，它就会快的！
  你可以不明显，但我一定做到完美
  执行用时：5 ms, 在所有 Java 提交中击败了98.59%的用户
*/
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int i = 0;
        int j = 0;
        while (matrix[i][matrix[i].length  -1] < target) {
            if (i + 1 < matrix.length) {
                i++;
            } else {
                return false;
            }
        }
        while (true) {
            if (matrix[i][j] < target) {
                if (j + 1 < matrix[i].length) {
                    j++;
                } else {
                    if (i + 1 < matrix.length) {
                        i++;
                    } else {
                        return false;
                    }
                    while (j > 0 && matrix[i][j] > target) {
                        j--;
                    }
                }
            } else if (matrix[i][j] == target){
                return true;
            } else {
                if (i + 1 < matrix.length) {
                    i++;
                } else {
                    return false;
                }
                while (j > 0 && matrix[i][j] > target) {
                    j--;
                }
            }
        }
    }
}
