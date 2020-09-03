//648. 单词替换
/*
  如果是以前，我想到的办法肯定是双层循环
  但是现在，在我掌握了前缀树的知识了以后，我知道用前缀树才是降维打击
  空间换时间永远的神
  10 ms,在所有Java提交中击败了91.50%的用户
*/
class Solution {
    TreeNode root = new TreeNode();

    public String replaceWords(List<String> dictionary, String sentence) {
        for (String s : dictionary) {
            insert(s);
        }
        String[] src = sentence.split(" ");
        for (int i = 0; i < src.length; i++) {
            String replace = search(src[i]);
            if (replace != null) {
                src[i] = replace;
            }
        }
        return String.join(" ", src);
    }

    private void insert(String dict) {
        TreeNode cur = root;
        char c;
        for (int i = 0; i < dict.length(); i++) {
            c = dict.charAt(i);
            if (!cur.hasChar(c)) {
                cur.addChar(c);
            }
            cur = cur.children[c - 'a'];
        }
        cur.isWord = true;
    }

    private String search(String s) {
        StringBuilder sb = new StringBuilder();
        TreeNode cur = root;
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (!cur.hasChar(c)) {
                return null;
            }
            sb.append(c);
            cur = cur.children[c - 'a'];
            if (cur.isWord) {
                break;
            }
        }
        return sb.toString();
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