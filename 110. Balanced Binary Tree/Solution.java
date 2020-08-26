//110. 平衡二叉树
/*
  很简单，递归算左右子树的深度
  一开始理解错了，按照满树算的
  1 ms,在所有Java提交中击败了99.85%的用户
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
    boolean result;
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        result = true;
        dfs(root);
        return result;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = dfs(node.left);
        int right = dfs(node.right);
        if (Math.abs(left - right) > 1) {
            result = false;
        }
        return Math.max(left, right) + 1;
    }
}