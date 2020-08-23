//701. 二叉搜索树中的插入操作
/*
  找到位置就插
  要注意的是插空树的时候的操作
  执行用时：0 ms 已经战胜 100.00 % 的 java 提交记录
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
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode src = root;
        while (root != null) {
            if (val < root.val) {
                if (root.left == null) {
                    break;
                } else {
                    root = root.left;
                }
            } else {
                if (root.right == null) {
                    break;
                } else {
                    root = root.right;
                }
            }
        }
        TreeNode newNode = new TreeNode(val);
        if (root == null) {
            return newNode;
        }
        if (val < root.val) {
            root.left = newNode;
        } else {
            root.right = newNode;
        }
        return src;
    }
}