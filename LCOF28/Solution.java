// 剑指 Offer 28. 对称的二叉树
/*
  左右两个子树同时遍历，左子树先左后右，右子树先右后左
  可优化的点是dfs可以提前结束
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
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
    boolean ret = true;
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        dfs(root.left, root.right);
        return ret;
    }

    public void dfs(TreeNode l, TreeNode r) {
        if (l == null || r == null) {
            if (l == r) {
                return;
            }
            ret = false;
            return;
        }
        if (l.val != r.val) {
            ret = false;
            return;
        }
        dfs(l.left, r.right);
        dfs(l.right, r.left);
    }
}
