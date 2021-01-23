// 212. 单词搜索 II
/*
  先找到起点，然后四个方向dfs
  需要设置visited数组
  递归的时候注意控制越界、判断visited
  注意更新visited的时机
  执行用时:2ms,在所有Java提交中击败了84%的用户
*/
class Solution {
    char[][] board;
    boolean[][] visited;
    List<String> list;
    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        list = new ArrayList<>();
        for (String s : words) {
            boolean find = false;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == s.charAt(0)) {
                        visited = new boolean[board.length][board[i].length];
                        find = dfs(i, j, s, 0);
                    }
                    if (find) {
                        list.add(s);
                        break;
                    }
                }
                if (find) {
                    break;
                }
            }
        }
        return list;
    }

    private boolean dfs(int i, int j, String s, int target) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || visited[i][j] || board[i][j] != s.charAt(target)) {
            return false;
        }
        visited[i][j] = true;
        target++;
        if (target >= s.length()) {
            return true;
        }

        return dfs(i - 1, j, s, target) || dfs(i + 1, j, s, target) || dfs(i, j - 1, s, target) || dfs(i, j + 1, s, target);
    }
}
/*
  前缀树优化版本
  很郁闷，费了好半天劲写出来的，居然这么慢
  不过这里面有很多的思想还是值得学习的
  之前我是根据字典，遍历board
  改成把字典存起来，遍历一次board用HashMap去查找，肯定是要更快的
  另外，处理遍历的问题，不需要用到visited数组，而是通过改写的方式标记，然后再恢复
  用上了这么多很快的技巧，为什么还是变慢了许多呢？合理怀疑是构建前缀树的开销太大了
  235ms, 在所有Java提交中击败了21.86%的用户
*/
class Solution {
    char[][] board;
    List<String> result = new ArrayList<>();
    class TreeNode {
        public Map<Character, TreeNode> children = new HashMap<>();
        String word;
    }
    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        TreeNode root = new TreeNode();
        TreeNode cur;
        for (String s : words) {
            cur = root;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!cur.children.containsKey(c)) {
                    cur.children.put(c, new TreeNode());
                }
                cur = cur.children.get(c);
            }
            cur.word = s;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                dfs(i, j, root);
            }
        }
        return result;
    }

    private void dfs(int i, int j, TreeNode node) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length) {
            return;
        }
        if (!node.children.containsKey(board[i][j])) {
            return;
        }
        
        char visited = board[i][j];
        board[i][j] = '#';
        node = node.children.get(visited);
        if (node.word != null) {
            result.add(node.word);
            node.word = null;
        }

        dfs(i - 1, j, node);
        dfs(i + 1, j, node);
        dfs(i, j - 1, node);
        dfs(i, j + 1, node);

        board[i][j] = visited;
    }
}
/*
  号外：思考了一下上一个解法的问题
  第一种解法里，找到一个单词就会停止对这个单词的搜索
  而第二种解法，同一个单词可能被检索很多遍，这就是问题所在
  1ms,在所有Java提交中击败了97.54%的用户
*/
class Solution {
    char[][] board;
    List<String> result = new ArrayList<>();
    class TreeNode {
        public Map<Character, TreeNode> children = new HashMap<>();
        String word;
    }
    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        TreeNode root = new TreeNode();
        TreeNode cur;
        for (String s : words) {
            cur = root;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!cur.children.containsKey(c)) {
                    cur.children.put(c, new TreeNode());
                }
                cur = cur.children.get(c);
            }
            cur.word = s;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                dfs(i, j, root);
            }
        }
        return result;
    }

    private void dfs(int i, int j, TreeNode node) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length) {
            return;
        }
        if (!node.children.containsKey(board[i][j])) {
            return;
        }
        
        char visited = board[i][j];
        board[i][j] = '#';
        TreeNode child = node.children.get(visited);
        if (child.word != null) {
            result.add(child.word);
            child.word = null;
        }

        dfs(i - 1, j, child);
        dfs(i + 1, j, child);
        dfs(i, j - 1, child);
        dfs(i, j + 1, child);

        board[i][j] = visited;
        if (child.children.isEmpty()) {
            node.children.remove(visited);
        }
    }
}