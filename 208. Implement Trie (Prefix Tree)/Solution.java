//208. 实现 Trie (前缀树)
/*
  设计题，需要设计一个内部类表示树的结点
  结点中还有一个布尔变量，用来标记截止到当前结点，是否是一个单词：比如bed和bedroom
  43 ms,在所有Java提交中击败了70.39%的用户
*/
class Trie {
    class TreeNode {
        public TreeNode[] children = new TreeNode[26];
        public boolean isWord = false;
    }
    private TreeNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TreeNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TreeNode point = root;
        char cur;
        for (int i = 0; i < word.length(); i++) {
            cur = word.charAt(i);
            if (getChild(point, cur) == null) {
                point.children[cur - 'a'] = new TreeNode();
            }
            point = getChild(point, cur);
        }
        point.isWord = true;
    }

    private TreeNode getChild(TreeNode node, char c) {
        return node.children[c - 'a'];
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TreeNode point = root;
        char cur;
        for (int i = 0; i < word.length(); i++) {
            cur = word.charAt(i);
            if (getChild(point, cur) != null) {
                point = getChild(point, cur);
            } else {
                return false;
            }
        }
        return point.isWord;
    }

    private boolean hasChild(TreeNode node) {
        for (TreeNode child : node.children) {
            if (child != null) {
                System.out.println("child");
                return true;
            }
        }
        return false;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TreeNode point = root;
        char cur;
        for (int i = 0; i < prefix.length(); i++) {
            cur = prefix.charAt(i);
            if (getChild(point, cur) != null) {
                point = getChild(point, cur);
            } else {
                return false;
            }
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */