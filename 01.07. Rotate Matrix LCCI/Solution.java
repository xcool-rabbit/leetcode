//面试题 01.07. 旋转矩阵
/*
  运用到了线性代数知识，涉及到了旋转矩阵
  详见：https://blog.csdn.net/JR_Chan/article/details/105362434
  执行用时：0 ms 已经战胜 100.00 % 的 java 提交记录
*/
class Solution {
    public void rotate(int[][] matrix) {
        for (int i = 0; i <= (matrix.length - 1) / 2; i++) {
            for (int j = 0; j < matrix.length / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[matrix.length - 1 - j][i];
                matrix[matrix.length - 1 - j][i] = matrix[matrix.length - 1 - i][matrix.length - 1 - j];
                matrix[matrix.length - 1 - i][matrix.length - 1 - j] = matrix[j][matrix.length - 1 - i];
                matrix[j][matrix.length - 1 - i] = tmp;
            }
        }
    }
}
