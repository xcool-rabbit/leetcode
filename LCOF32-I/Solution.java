// 剑指 Offer 32 - I. 从上到下打印二叉树
/*
  刚才还以为，这题要分层，结果。。。甚至连层都不用分
  执行用时：1 ms, 在所有 Java 提交中击败了97.79%的用户
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
    public int[] levelOrder(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        Queue<TreeNode> cur = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        cur.add(root);
        while (!cur.isEmpty()) {
            TreeNode tmp = cur.poll();
            list.add(tmp.val);
            if (tmp.left != null) {
                cur.offer(tmp.left);
            }
            if (tmp.right != null) {
                cur.offer(tmp.right);
            }
        }
        int[] ret = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }
}
