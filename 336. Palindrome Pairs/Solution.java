// 336. 回文对
/*
  暴力解法过不了
  肯定是要利用上前缀树来高效的寻找字符串
  我的思路是用前缀树来承担一部分的遍历工作，减少遍历量
  首先两个长度相等的字符串非常好判断是不是回文对
  根据第一个字符串，倒序在前缀树中遍历，就能确定
  长度不等的又该怎么处理呢
  长度不等也就是一长一短两个字符串，要么长在前，要么长在后
  长在后的具备一种特征，就是短的是（长的倒序的一部分）
  短在后的具备一种特征，就是长的一部分是（短的倒序）
  对于第一种情况，就是在前缀树寻找的时候，字符串没有找完，在半路中遇到的字符串
  对于第二种情况，就是在前缀树寻找的时候，字符串已经找完了，但是没有到底，剩下的子树里面所有字符串都算上
  589ms,在所有Java提交中击败了8.09%的用户
*/
class Solution {
    class TreeNode {
        public Map<Character, TreeNode> children = new HashMap<>();
        public int index = -1;
        public String word;
    }
    public List<List<Integer>> palindromePairs(String[] words) {
        TreeNode root = new TreeNode();
        for (int k = 0; k < words.length; k++) {
            TreeNode cur = root;
            for (int i = 0; i < words[k].length(); i++) {
                char c = words[k].charAt(i);
                if (!cur.children.containsKey(c)) {
                    cur.children.put(c, new TreeNode());
                }
                cur = cur.children.get(c);
            }
            cur.index = k;
            cur.word = words[k];
        }
        
        Map<Integer, Set<Integer>> resultMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            List<Integer> target = findReverse(root, words[i]);
            for (Integer j : target) {
                if (j == -1 || i == j) { // 防止自己本身就是回文
                    continue;
                }
                if (words[i].length() == words[j].length()) {
                    if (!resultMap.containsKey(i)) {
                        resultMap.put(i, new HashSet<Integer>());
                    }
                    resultMap.get(i).add(j);
                } else {
                    if (isReverse(words[i] + words[j])) {
                        if (!resultMap.containsKey(i)) {
                        resultMap.put(i, new HashSet<Integer>());
                    }
                    resultMap.get(i).add(j);
                    }
                    if (isReverse(words[j] + words[i])) {
                        if (!resultMap.containsKey(j)) {
                        resultMap.put(j, new HashSet<Integer>());
                    }
                    resultMap.get(j).add(i);
                    }
                }
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        for (Map.Entry<Integer, Set<Integer>> entry : resultMap.entrySet()) {
            int i = entry.getKey();
            for (Integer j : entry.getValue()) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(i);
                tmp.add(j);
                result.add(tmp);
            }
        }

        return result;
    }

    public List<Integer> findReverse(TreeNode node, String s) {
        List<Integer> result = new ArrayList<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            System.out.println(c);
            if (node.index != -1) {
                result.add(node.index);
            }
            if (!node.children.containsKey(c)) {
                return result;
            }
            node = node.children.get(c);
        }
        result.addAll(getAllChildren(node));
        return result;
    }

    public List<Integer> getAllChildren(TreeNode node) {
        List<Integer> ret = new ArrayList<>();
        if (node.index != -1) {
            ret.add(node.index);
        }
        for (TreeNode child : node.children.values()) {
            ret.addAll(getAllChildren(child));
        }
        return ret;
    }

    public boolean isReverse(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
/*
  TreeNode结构中，word用不到，删掉
  getAllChildren这一步可以优化一下，在构造TreeNode的时候就能维护该节点的所有子节点对应的index，避免频繁的回溯
  但是结果并不尽如人意，看来我的方法还不是正道的光
  时间短了，但是击败的用户反而少了，看来大家的平均水平都有所提高
  526ms,在所有Java提交中击败了8.06%的用户
*/
class Solution {
    class TreeNode {
        public Map<Character, TreeNode> children = new HashMap<>();
        public int index = -1;
        public List<Integer> subIndex = new ArrayList<>();
    }
    public List<List<Integer>> palindromePairs(String[] words) {
        TreeNode root = new TreeNode();
        for (int k = 0; k < words.length; k++) {
            TreeNode cur = root;
            for (int i = 0; i < words[k].length(); i++) {
                char c = words[k].charAt(i);
                if (!cur.children.containsKey(c)) {
                    cur.children.put(c, new TreeNode());
                }
                cur.subIndex.add(k);
                cur = cur.children.get(c);
            }
            cur.index = k;
        }
        
        Map<Integer, Set<Integer>> resultMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            List<Integer> target = findReverse(root, words[i]);
            for (Integer j : target) {
                if (j == -1 || i == j) { // 防止自己本身就是回文
                    continue;
                }
                if (words[i].length() == words[j].length()) {
                    if (!resultMap.containsKey(i)) {
                        resultMap.put(i, new HashSet<Integer>());
                    }
                    resultMap.get(i).add(j);
                } else {
                    if (isReverse(words[i] + words[j])) {
                        if (!resultMap.containsKey(i)) {
                        resultMap.put(i, new HashSet<Integer>());
                    }
                    resultMap.get(i).add(j);
                    }
                    if (isReverse(words[j] + words[i])) {
                        if (!resultMap.containsKey(j)) {
                        resultMap.put(j, new HashSet<Integer>());
                    }
                    resultMap.get(j).add(i);
                    }
                }
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        for (Map.Entry<Integer, Set<Integer>> entry : resultMap.entrySet()) {
            int i = entry.getKey();
            for (Integer j : entry.getValue()) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(i);
                tmp.add(j);
                result.add(tmp);
            }
        }

        return result;
    }

    public List<Integer> findReverse(TreeNode node, String s) {
        List<Integer> result = new ArrayList<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            System.out.println(c);
            if (node.index != -1) {
                result.add(node.index);
            }
            if (!node.children.containsKey(c)) {
                return result;
            }
            node = node.children.get(c);
        }
        result.add(node.index);
        result.addAll(node.subIndex);
        return result;
    }

    public boolean isReverse(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
/*
  看了一下官方解法，我对我自己的解法还是挺满意的，分析的非常到位，就是在应用前缀树的时候有一些区别
  n是单词数组的长度，m是单词的长度
  暴力解法是O(n ^ 2 * m)
  官方解法是O(n * m ^ 2)
  而上面我的解法是O(n * N * m)，大N表示筛选出的结果
  可见这三种解法数量级是一样的，关键问题在于对于实际问题来讲，谁的指数影响更加大
  对于题目的测试集来讲，m是经常小于甚至远小于n的
  在这种前提下，官方解法是更快的
  但是如果字符串都很长，官方解法的效率会大打折扣，我的解法性能比较中庸

  这个解法真的一言难尽，我跟示例代码对比了一下，没有逻辑上的区别，但是在处理空字符串的时候，我写的就是过不了
  并且示例代码也没有特别快，超过百分之八九十那种
  为了我的方法能过，我把空字符串单独处理
  也就多了一次遍历，理论影响不大
  但是实际上差的还是挺多的

  官方解法也并非一无是处，它用到了字典树，一种把前缀树用List串联起来的结构，大同小异
  还有一点让人费解的是，官方的这个不会重复
  157ms,在所有Java提交中击败了16.09%的用户
*/

class Solution {
    class TreeNode {
        public Map<Character, TreeNode> children = new HashMap<>();
        public int index = -1;
    }
    public List<List<Integer>> palindromePairs(String[] words) {
        TreeNode root = new TreeNode();
        for (int k = 0; k < words.length; k++) {
            TreeNode cur = root;
            for (int i = 0; i < words[k].length(); i++) {
                char c = words[k].charAt(i);
                if (!cur.children.containsKey(c)) {
                    cur.children.put(c, new TreeNode());
                }
                cur = cur.children.get(c);
            }
            cur.index = k;
        }
        root.index = -1;

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String s = words[i];
            if (s.equals("")) {
                for (int tmp = 0; tmp < words.length; tmp++) {
                    if (tmp == i) {
                        continue;
                    }
                    if (isReverse(words[tmp])) {
                        result.add(assembleResult(i, tmp));
                        result.add(assembleResult(tmp, i));
                    }
                }
                continue;
            }
            Set<Integer> pair1 = new HashSet<>();
            Set<Integer> pair2 = new HashSet<>();
            for (int j = 0; j < s.length(); j++) {
                String s1 = s.substring(0, j);
                String s2 = s.substring(j, s.length());
                if (isReverse(s1)) {
                    int reverse = findReverse(root, s2);
                    if (reverse != -1 && reverse != i && !pair1.contains(reverse)) {
                        pair1.add(reverse);
                        result.add(assembleResult(reverse, i));               
                    }
                }
                if (j != 0 && isReverse(s2)) { // 否则j == 0的时候，会重复计算
                    int reverse = findReverse(root, s1);
                    if (reverse != -1 && reverse != i && !pair2.contains(reverse)) {
                        pair2.add(reverse);
                        result.add(assembleResult(i, reverse));
                    }
                }
            }
        }
        
        return result;
    }

    public boolean isReverse(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public int findReverse(TreeNode node, String s) {
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (!node.children.containsKey(c)) {
                return -1;
            }
            node = node.children.get(c);
        }
        return node.index;
    }

    public List<Integer> assembleResult(int i, int j) {
        List<Integer> tmp = new ArrayList<>();
        tmp.add(i);
        tmp.add(j);
        return tmp;
    }
}

