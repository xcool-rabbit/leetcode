// 剑指 Offer 34. 二叉树中和为某一值的路径
/*
  遍历，回溯
  注意一定要是叶子结点，才是答案
  执行用时：1 ms, 在所有 Java 提交中击败了99.98%的用户
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
    List<List<Integer>> ret;
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        ret = new LinkedList<>();
        List<Integer> cur = new ArrayList<>();
        dfs(root, cur, 0, target);
        return ret;
    }

    public void dfs(TreeNode node, List<Integer> list, int sum, int target) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        sum += node.val;
        if (node.left == null && node.right == null) {
            if (sum == target) {
                ret.add(new ArrayList<>(list));
            }
        } else {
            dfs(node.left, list, sum, target);
            dfs(node.right, list, sum, target);
        }
        list.remove(list.size() - 1);
        sum -= node.val;
    }
}
