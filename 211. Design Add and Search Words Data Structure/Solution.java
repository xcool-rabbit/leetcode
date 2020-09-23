//211. 添加与搜索单词 - 数据结构设计
/*
  经典的前缀树问题，特色就是有一个通配符
  通配符的匹配需要递归处理
  执行用时: 71 ms，超过了 29 %的Java提交记录
*/
class WordDictionary {
    TreeNode root = new TreeNode();

    /** Initialize your data structure here. */
    public WordDictionary() {

    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TreeNode cur = root;
        char c;        
        for (int i = 0; i < word.length(); i++) {
            c = word.charAt(i);
            if (!cur.hasChar(c)) {
                cur.addChar(c);
            }
            cur = cur.children[c - 'a'];
        }
        cur.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return searchChild(word, root);
    }

    private boolean searchChild(String word, TreeNode cur) {
        if (word == null || word.equals("")) {
            return cur.isWord;
        }
        char c = word.charAt(0);
        if (c != '.') {
            if (!cur.hasChar(c)) {
                return false;
            }
            return searchChild(word.substring(1, word.length()), cur.children[c - 'a']);
        } else {
            boolean exist = false;
            for (TreeNode node : cur.children) {
                if (node != null) {
                    exist = exist || searchChild(word.substring(1, word.length()), node);
                }
            }
            return exist;
        }
    }

    class TreeNode {
        public TreeNode[] children = new TreeNode[26];
        public boolean isWord = false;

        public boolean hasChar(char c) {
            return children[c - 'a'] != null;
        }

        public void addChar(char c) {
            children[c - 'a'] = new TreeNode();
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
/*
  每次递归传输的都是子字符串，有一定的开销
  改进成了字符数组 + 标定位置
  执行用时: 64 ms，超过了 39 %的Java提交记录
*/
class WordDictionary {
    TreeNode root = new TreeNode();

    /** Initialize your data structure here. */
    public WordDictionary() {

    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TreeNode cur = root;
        char c;        
        for (int i = 0; i < word.length(); i++) {
            c = word.charAt(i);
            if (!cur.hasChar(c)) {
                cur.addChar(c);
            }
            cur = cur.children[c - 'a'];
        }
        cur.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        char[] str = word.toCharArray();
        return searchChild(str, 0, root);
    }

    private boolean searchChild(char[] word, int index, TreeNode cur) {
        if (index >= word.length) {
            return cur.isWord;
        }
        char c = word[index];
        if (c != '.') {
            if (!cur.hasChar(c)) {
                return false;
            }
            return searchChild(word, index + 1, cur.children[c - 'a']);
        } else {
            boolean exist = false;
            for (TreeNode node : cur.children) {
                if (node != null) {
                    exist = exist || searchChild(word, index + 1, node);
                }
            }
            return exist;
        }
    }

    class TreeNode {
        public TreeNode[] children = new TreeNode[26];
        public boolean isWord = false;

        public boolean hasChar(char c) {
            return children[c - 'a'] != null;
        }

        public void addChar(char c) {
            children[c - 'a'] = new TreeNode();
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
/*
  上述改进仍然没有很快，我愚钝了
  后来看了别人的题解才想起来通配符那个地方是可以提前返回的
  改了之后果然变快很多
  执行用时: 51 ms，超过了 88 %的Java提交记录
*/
class WordDictionary {
    TreeNode root = new TreeNode();

    /** Initialize your data structure here. */
    public WordDictionary() {

    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TreeNode cur = root;
        char c;        
        for (int i = 0; i < word.length(); i++) {
            c = word.charAt(i);
            if (!cur.hasChar(c)) {
                cur.addChar(c);
            }
            cur = cur.children[c - 'a'];
        }
        cur.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        char[] str = word.toCharArray();
        return searchChild(str, 0, root);
    }

    private boolean searchChild(char[] word, int index, TreeNode cur) {
        if (index >= word.length) {
            return cur.isWord;
        }
        char c = word[index];
        if (c != '.') {
            if (!cur.hasChar(c)) {
                return false;
            }
            return searchChild(word, index + 1, cur.children[c - 'a']);
        } else {
            boolean exist = false;
            for (TreeNode node : cur.children) {
                if (node != null) {
                    exist = exist || searchChild(word, index + 1, node);
                }
                if (exist) {
                    return exist;
                }
            }
            return false;
        }
    }

    class TreeNode {
        public TreeNode[] children = new TreeNode[26];
        public boolean isWord = false;

        public boolean hasChar(char c) {
            return children[c - 'a'] != null;
        }

        public void addChar(char c) {
            children[c - 'a'] = new TreeNode();
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */