//652. 寻找重复的子树
/*
  第一个问题是如何判断两个树是不是重复的
  由前序遍历+中序遍历或者前序遍历+后序遍历可以唯一确定一棵树
  （这里需要强调一下，必须各个结点互不相同）
  （这道题遇到过一个槛就是因为本题根据结点的val来区分的，而val值就有重复的可能
    这种情况下需要将null也构建到树里面，否则的话并不能唯一确定一棵树）
  那么对每个结点，都能描摹出一幅这样的画像
  寻找重复的问题，这次我记住了，用Map来存储，可以避免嵌套循环
  但是性能仍然不高，问题在于我那个遍历的那一块儿是分开做的，思路很清晰但明显有冗余
  Main遍历过一遍，然后Sub还要去遍历
  执行用时：363 ms 慢的望不到边际
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Boolean> map = new HashMap<>();
        List<TreeNode> ans = new ArrayList<>();
        preOrderMain(map, root, ans);
        return ans;
    }
    
    private void preOrderSub(StringBuilder sb, TreeNode node) {
        if (node == null) {
            sb.append("null");
            sb.append('*');
            return;
        }
        
        sb.append(node.val);
        sb.append('*');
        preOrderSub(sb, node.left);
        preOrderSub(sb, node.right);
    }
    
    private void inOrderSub(StringBuilder sb, TreeNode node) {
        if (node == null) {
            sb.append("null");
            sb.append('*');
            return;
        }
        
        inOrderSub(sb, node.left);
        sb.append(node.val);
        sb.append('*');
        inOrderSub(sb, node.right);
    }
    
    private void preOrderMain(Map<String, Boolean> map, TreeNode node, List<TreeNode> list) {
        if (node == null) {
            return;
        }
        
        StringBuilder pre = new StringBuilder();
        StringBuilder in = new StringBuilder();
        preOrderSub(pre, node);
        inOrderSub(in, node);
        String key = pre.append('#').append(in).toString();
        Boolean value = map.get(key);
        if (value == null) {
            map.put(key, false);
        } else if (!value) {
            list.add(node);
            map.put(key, true);
        }
        
        preOrderMain(map, node.left, list);
        preOrderMain(map, node.right, list);
    }
}
/*
  第一种方法慢那当然要考虑精简遍历过程，减少冗余
  首先可以将前序遍历和中序遍历结合到一起，在一个递归里完成两个遍历
  因为树的三种DFS方式，访问的结点顺序是一样的，区别在于什么时候输出val
  确实有了显著提升，但是还排不上名次
  执行用时：231 ms 慢的望不到边际
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Boolean> map = new HashMap<>();
        List<TreeNode> ans = new ArrayList<>();
        preOrderMain(map, root, ans);
        return ans;
    }
    
    private void preAndInOrderSub(StringBuilder pre, StringBuilder in, TreeNode node) {
        if (node == null) {
            pre.append("null");
            pre.append('*');
            in.append("null");
            in.append('*');
            return;
        }
        
        pre.append(node.val);
        pre.append('*');
        preAndInOrderSub(pre, in, node.left);
        in.append(node.val);
        in.append('*');
        preAndInOrderSub(pre, in, node.right);
    }
    
    private void preOrderMain(Map<String, Boolean> map, TreeNode node, List<TreeNode> list) {
        if (node == null) {
            return;
        }
        
        StringBuilder pre = new StringBuilder();
        StringBuilder in = new StringBuilder();
        preAndInOrderSub(pre, in, node);
        String key = pre.append('#').append(in).toString();
        Boolean value = map.get(key);
        if (value == null) {
            map.put(key, false);
        } else if (!value) {
            list.add(node);
            map.put(key, true);
        }
        
        preOrderMain(map, node.left, list);
        preOrderMain(map, node.right, list);
    }
}
/*
  我自己做出来的终极优化版本
  以前是O(N ^ 2)的复杂度，现在将所有处理都糅合在了一次递归当中
  总的记录放在了后序遍历的位置，因为那是pre和in的遍历都已经完成，可以放到Map里做key了
  StringBuilder处理的那块儿有点复杂是因为不这么处理的话会OOM
  执行用时：50 ms 已经战胜 7.87 % 的 java 提交记录
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Boolean> map = new HashMap<>();
        List<TreeNode> ans = new ArrayList<>();
        // preOrderMain(map, root, ans);
        StringBuilder pre = new StringBuilder();
        StringBuilder in = new StringBuilder();
        dfs(pre, in, map, root, ans);
        return ans;
    }
    
    private void dfs(StringBuilder pre, StringBuilder in, Map<String, Boolean> map, TreeNode node, List<TreeNode> list) {
        if (node == null) {
            pre.append("null");
            pre.append('*');
            in.append("null");
            in.append('*');
            return;
        }
        
        int curPre = pre.length();
        pre.append(node.val);
        pre.append('*');
        dfs(pre, in, map, node.left, list);
        int curIn = in.length();
        in.append(node.val);
        in.append('*');
        dfs(pre, in, map, node.right, list);
        StringBuilder tmp = new StringBuilder();
        tmp.append(pre.substring(curPre)).append('#').append(in.substring(curIn));
        String key = tmp.toString();
        Boolean value = map.get(key);
        if (value == null) {
            map.put(key, false);
        } else if (!value) {
            list.add(node);
            map.put(key, true);
        }
    }
}
/*
  其实我也大概能感觉的到是hash的开销
  首先是我对于null值的处理，用了4个字符串，改成一个之后就大幅缩减了运行时间
  当然还能继续优化，在考虑了null值的情况下，（好像）随便一种DFS都可以唯一确定一棵树
  学校讲的方法应该是根据遍历结果来确定，遍历的结果里面肯定是没有null的
  有null的话随便一种DFS都能确定
  就因为这个，我的key比别人长一倍
  优秀，还能说什么？
  执行用时：14 ms 已经战胜 98.03 % 的 java 提交记录
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Boolean> map = new HashMap<>();
        List<TreeNode> ans = new ArrayList<>();
        StringBuilder pre = new StringBuilder();
        dfs(pre, map, root, ans);
        return ans;
    }
    
    private void dfs(StringBuilder pre, Map<String, Boolean> map, TreeNode node, List<TreeNode> list) {
        if (node == null) {
            pre.append("n");
            pre.append('*');
            return;
        }
        
        int curPre = pre.length();
        pre.append(node.val);
        pre.append('*');
        dfs(pre, map, node.left, list);
        dfs(pre, map, node.right, list);
        String key = pre.substring(curPre);
        Boolean value = map.get(key);
        if (value == null) {
            map.put(key, false);
        } else if (!value) {
            list.add(node);
            map.put(key, true);
        }
    }
}
