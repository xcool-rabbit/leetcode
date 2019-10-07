//108. 将有序数组转换为二叉搜索树
/*
  父节点就是数组的中间值，利用递归
  执行用时：1 ms 已经战胜 99.76 % 的 java 提交记录
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
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(nums[nums.length / 2]);
        root.left = getChild(nums, 0, nums.length / 2);
        root.right = getChild(nums, nums.length / 2 + 1, nums.length);
        return root;
    }
    
    private TreeNode getChild(int[] nums, int start, int end) {
        if (start == end) {
            return null;
        }
        TreeNode child = new TreeNode(nums[(start + end) / 2]);
        child.left = getChild(nums, start, (start + end) / 2);
        child.right = getChild(nums, (start + end) / 2 + 1, end);
        return child;
    }
}