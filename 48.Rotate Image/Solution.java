//48. 旋转图像
/*
  通过观察，可以通过两步操作达到旋转的目的：
  1.转置
  2.以垂直中轴线镜面对换
  执行用时：1 ms 已经战胜 100.00 % 的 java 提交记录
*/
class Solution {
    public void rotate(int[][] matrix) {
        int tmp = 0;
        
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length / 2; j++) {
                tmp = matrix[i][matrix[0].length - 1 - j];
                matrix[i][matrix[0].length - 1 - j] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
    }
}
