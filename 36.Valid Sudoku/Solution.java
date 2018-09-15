//36. 有效的数独
/*
  很简单的检查是否有重复元素，利用Set，遍历行、列和九宫格。
  执行用时：42 ms 已经战胜 20.88 % 的 java 提交记录
  wor好慢。。。找找为什么
  大体思想是一样的，无非是空间换时间，导致快一些，研究起来没什么意义。
*/
class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<Character> set = new HashSet<Character>();
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.' && set.add(board[i][j]) != true)
                    return false;
            }
            set.clear();
        }
        
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                if (board[i][j] != '.' && set.add(board[i][j]) != true)
                    return false;
            }
            set.clear();
        }
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                
                for (int m = 0; m < 3; m++) {
                    for (int n = 0; n < 3; n++) {
                        if (board[m + i * 3][n + j * 3] != '.' && set.add(board[m + i * 3][n + j * 3]) != true)
                            return false;
                    }
                }
                set.clear();
                
            }
        }
        
        return true;
    }
}
