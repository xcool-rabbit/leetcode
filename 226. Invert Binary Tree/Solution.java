// 226. 翻转二叉树
/*
  跟LCOF27题一样，我先做的那个，就把这边也粘贴一下，挺简单的，没有必要留着以后遇到再做一遍
  并且，主站不会注明与LCOF的题是重复的，所以到时候，得重新写
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
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
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode ret = new TreeNode(root.val);
        ret.right = invertTree(root.left);
        ret.left = invertTree(root.right);
        return ret;
    }
}
