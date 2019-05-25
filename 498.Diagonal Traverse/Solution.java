//498. 对角线遍历
/*
  打印矩阵的题，就是走来走去就好了，头脑清楚，看到边界以后调整方向。
  执行用时：5 ms 已经战胜 90.99 % 的 java 提交记录
*/
class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        int x = 0;
        int y = 0;
        int m = matrix.length;
        if (m == 0) {
            return new int[0];
        }
        int n = matrix[0].length;
        if (n == 0) {
            return new int[0];
        }
        int[] answer = new int[m * n];
        int i = 0;
        while (true) {
            while (x >= 0 && y <= n - 1) {
                answer[i] = matrix[x][y];
                i++;
                x--;
                y++;
            }
            x++;
            y--;
            
            if (x == m - 1 && y == n - 1) {
                break;
            }
            
            if (y + 1 > n - 1) {
                x++;
            } else {
                y++;
            }
            
            while (x <= m - 1 && y >= 0) {
                answer[i] = matrix[x][y];
                i++;
                x++;
                y--;
            }
            x--;
            y++;
            
            if (x == m - 1 && y == n - 1) {
                break;
            }
            
            if (x + 1 > m - 1) {
                y++;
            } else {
                x++;
            }
        }
        
        return answer;
    }
}
