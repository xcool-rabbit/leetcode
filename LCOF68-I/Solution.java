// 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
/*
  这题同主站235，但是那个题，是接着一道普通树的题做的，虽然也有优化，但是并没有从根本上，从二叉搜索树的角度考虑问题
  二叉搜索树，左边小，右边大，很容易就能确定pq两个点的分布
  执行用时：5 ms, 在所有 Java 提交中击败了100.00%的用户
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
    TreeNode p;
    TreeNode q;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > q.val) {
            return lowestCommonAncestor(root, q, p);
        }
        this.p = p;
        this.q = q;
        return dfs(root);
    }

    public TreeNode dfs(TreeNode node) {
        if (p.val < node.val && q.val > node.val) {
            return node;
        }
        if (p.val == node.val || q.val == node.val) {
            return node;
        }
        if (q.val < node.val) {
            return dfs(node.left);
        }
        if (p.val > node.val) {
            return dfs(node.right);
        }
        return null;
    }
}
