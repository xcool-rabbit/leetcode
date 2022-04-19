//235. 二叉搜索树的最近公共祖先
/*
  之前做过一个普通树的最近公共祖先，现在回头看一下这种方法还挺巧妙的，自己想的
  但是原样搬过来并不是很快，所以说二叉搜索树对这个问题有些可以利用的特性
  执行用时：7 ms 已经战胜 39.77 % 的 java 提交记录
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    int pVal, qVal;
    TreeNode ancestor;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        pVal = p.val;
        qVal = q.val;
        dfs(root);
        return ancestor;
    }

    private boolean dfs(TreeNode node) {
        if (node == null) {
            return false;
        }
        int times = 0;
        boolean has = false;
        if (isEqual(node.val)) {
            times++;
            has = true;
        }
        if (dfs(node.left)) {
            times++;
            has = true;
        }
        if (dfs(node.right)) {
            times++;
            has = true;
        }
        if (times == 2) {
            ancestor = node;
        }
        return has;
    }

    private boolean isEqual(int val) {
        return val == pVal || val == qVal;
    }
}
/*
  果然不出我所料，利用二叉搜索树的大小关系，节省了很多不必要的遍历，速度得到了提升
  执行用时：6 ms 已经战胜 99.77 % 的 java 提交记录
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    int min, max;
    TreeNode ancestor;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        min = p.val < q.val ? p.val : q.val;
        max = p.val < q.val ? q.val : p.val;
        dfs(root);
        return ancestor;
    }

    private boolean dfs(TreeNode node) {
        if (node == null) {
            return false;
        }
        int times = 0;
        boolean has = false;
        if (isEqual(node.val)) {
            times++;
            has = true;
        }
        if (node.val > min && dfs(node.left)) {
            times++;
            has = true;
        }
        if (node.val < max && dfs(node.right)) {
            times++;
            has = true;
        }
        if (times == 2) {
            ancestor = node;
        }
        return has;
    }

    private boolean isEqual(int val) {
        return val == min || val == max;
    }
}
/*
  这才叫真正利用二叉搜索树的性质！
  执行用时：5 ms, 在所有 Java 提交中击败了100.00%的用户
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    TreeNode p;
    TreeNode q;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > q.val) {
            return lowestCommonAncestor(root, q, p);
        }
        this.p = p;
        this.q = q;
        return dfs(root);
    }

    public TreeNode dfs(TreeNode node) {
        if (p.val < node.val && q.val > node.val) {
            return node;
        }
        if (p.val == node.val || q.val == node.val) {
            return node;
        }
        if (q.val < node.val) {
            return dfs(node.left);
        }
        if (p.val > node.val) {
            return dfs(node.right);
        }
        return null;
    }
}
