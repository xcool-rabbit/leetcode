// 剑指 Offer 55 - II. 平衡二叉树
/*
  这个是真需要通过递归来确定左右子树的深度，然后做出比较了
  可以优化的点是快速返回机制
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
