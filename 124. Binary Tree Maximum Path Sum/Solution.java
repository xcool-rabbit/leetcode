// 124. 二叉树中的最大路径和
/*
  首先，这道题看似路径非常的诡异，但是我们从单个节点的角度来分析
  从子节点往上走到父节点再走到右边的子节点，跟中序遍历是对应的，所以不用担心子节点回溯到父节点的操作问题
  继续从单个节点的角度来看，需要考虑左中右三部分，最大值的组成可以是单独的一方，也可以是两个相邻的，或者是三个都有
  递归遍历，只需要返回每个节点能达到的最大值即可
  但是这里忽略了一个问题，有些最大值是不能被父节点利用的，所以还需要分开考虑
  局部最大值，我们用一个全局变量来跟踪
  能被父节点利用的最大值，会被返回
  这是一道hard题，是一道我自己想出来的hard题，嘻嘻，还是跟ybb在酒店里一起遇到的，很开心
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
    int maxLocal = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        return Math.max(dfs(root), maxLocal);
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return -1001;
        }
        int left = dfs(root.left);
        int middle = root.val;
        int right = dfs(root.right);
        int max = Math.max(left, right);
        max = Math.max(max, left + middle + right);
        maxLocal = Math.max(maxLocal, max);
        int maxInherit = Math.max(middle, left + middle);
        maxInherit = Math.max(maxInherit, middle + right);
        return maxInherit;
    }
}
