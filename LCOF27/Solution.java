// 剑指 Offer 27. 二叉树的镜像
/*
  所有的节点都是左右子树交换
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
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode ret = new TreeNode(root.val);
        ret.right = mirrorTree(root.left);
        ret.left = mirrorTree(root.right);
        return ret;
    }
}
