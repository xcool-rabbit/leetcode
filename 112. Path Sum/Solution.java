//112. 路径总和
/*
  递归
  与以往遍历不同的是，这次访问到叶子结点就return
  同样将boolean放到返回值的位置
  执行用时：0 ms 已经战胜 100.00 % 的 java 提交记录
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
    public boolean hasPathSum(TreeNode root, int sum) {
        return root == null ? false : dfs(root, 0, sum);
    }
    
    private boolean dfs(TreeNode root, int sum, int target) {
        if (root == null) {
            return false;
        }
        if (isLeaf(root)) {
            sum += root.val;
            return sum == target;
        }
        
        sum += root.val;
        
        return dfs(root.left, sum, target) || dfs(root.right, sum, target);
    }
    
    private boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }
}
