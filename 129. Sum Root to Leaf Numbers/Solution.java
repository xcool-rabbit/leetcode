// 129. 求根节点到叶节点数字之和
/*
  遍历，注意判断该节点是不是叶节点
  遍历还是正常的遍历，只需要额外加一个判断用以计算，不要搞一些投机取巧的东西，这题很简单的
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
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return sum;
    }

    public void dfs(TreeNode node, int val) {
        if (node == null) {
            return;
        }
        val += node.val;
        if (node.left == null && node.right == null) {
            sum += val;
        }
        dfs(node.left, val * 10);
        dfs(node.right, val * 10);
    }
}
