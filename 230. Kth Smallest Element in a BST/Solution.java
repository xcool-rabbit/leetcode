// 230. 二叉搜索树中第K小的元素
/*
  为了图方便，搞了全局变量，不然的话就传参，也是一样的
  深度遍历的时候，计数，就知道谁是第k小的了
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
    int count = 0;
    int k;
    int ret;
    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return ret;
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        count++;
        if (count == k) {
            ret = root.val;
            return;
        }
        dfs(root.right);
    }
}
