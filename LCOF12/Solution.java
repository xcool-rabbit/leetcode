// 剑指 Offer 12. 矩阵中的路径
/*
  不理解嗷，为什么比别人慢1 ms
  没觉得慢在哪里了
  执行用时：5 ms, 在所有 Java 提交中击败了53.73%的用户
*/
class Solution {
    char[][] board;
    boolean[][] visited;
    String word;
    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.visited = new boolean[board.length][board[0].length];
        this.word = word;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(int i, int j, int n) {
        if (n == word.length()) {
            return true;
        }
        if (isOut(i, j) || visited[i][j]) {
            return false;
        }
        if (board[i][j] != word.charAt(n)) {
            return false;
        }
        visited[i][j] = true;
        boolean ret = dfs(i - 1, j, n + 1) || dfs(i, j + 1, n + 1) || dfs(i + 1, j, n + 1) || dfs(i, j - 1, n + 1);
        visited[i][j] = false;
        return ret;
    }

    public boolean isOut(int i, int j) {
        return i < 0 || i >= board.length || j < 0 || j >= board[i].length;
    }
}
