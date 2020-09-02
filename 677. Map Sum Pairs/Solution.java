//677. 键值映射
/*
  这是一个前缀树的实现题
  前缀树的实现方法常用的是数组实现
  这道题额外需要注意的是单词是有对应的值的
  在求和的时候需要用递归求和
  在掌握了递归思想之后，不管什么样结构的问题，都能迎刃而解了呢（厉害！
  15 ms,在所有Java提交中击败了95.36%的用户
*/
class MapSum {
    class TreeNode {
        public TreeNode[] children = new TreeNode[26];
        public int val = 0;

        public boolean hasChar(char c) {
            return children[c - 'a'] != null;
        }

        public void addChar(char c) {
            children[c - 'a'] = new TreeNode();
        }
    }

    private TreeNode root;
    /** Initialize your data structure here. */
    public MapSum() {
        root = new TreeNode();
    }
    
    public void insert(String key, int val) {
        TreeNode cur = root;
        char c;
        for (int i = 0; i < key.length(); i++) {
            c = key.charAt(i);
            if (!cur.hasChar(c)) {
                cur.addChar(c);
            }
            cur = cur.children[c - 'a'];
        }
        cur.val = val;
    }
    
    public int sum(String prefix) {
        TreeNode cur = root;
        char c;
        for (int i = 0; i < prefix.length(); i++) {
            c = prefix.charAt(i);
            if (!cur.hasChar(c)) {
                return 0;
            }
            cur = cur.children[c - 'a'];
        }
        return childrenSum(cur);
    }

    private int childrenSum(TreeNode node) {
        int sum = 0;
        for (TreeNode child : node.children) {
            if (child != null) {
                sum += childrenSum(child);
            }
        }
        return sum + node.val;
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */