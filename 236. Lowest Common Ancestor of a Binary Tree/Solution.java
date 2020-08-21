//236. 二叉树的最近公共祖先
/*
  思路是深度遍历的思路，在深度遍历中判断该结点是否已经是一个/两个结点的父结点
  用到了成员变量，可以使函数传参更加简洁
  执行用时：7 ms 已经战胜 99.88 % 的 java 提交记录
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
