// 剑指 Offer 68 - II. 二叉树的最近公共祖先
/*
  需要分别遍历左右子树，如果左右都有，那么就是要找的最近公共祖先
  否则需要往左或者往右
  想是这么想，但是实现的时候要递归的呀，等你把左右都遍历完一遍，再决定下一个往哪里递归？
  肯定是一边递归一边收集信息
  递归是正常的递归逻辑，只需要记录左右节点的查找情况即可
  细品吧，代码很好理解，讲出来反而很困难
  执行用时：6 ms, 在所有 Java 提交中击败了100.00%的用户
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
    int pVal, qVal;
    TreeNode ancestor;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        pVal = p.val;
        qVal = q.val;
        dfs(root);
        return ancestor;
    }

    private boolean dfs(TreeNode node) {
        if (node == null) {
            return false;
        }
        int times = 0;
        boolean has = false;
        if (isEqual(node.val)) {
            times++;
            has = true;
        }
        if (dfs(node.left)) {
            times++;
            has = true;
        }
        if (dfs(node.right)) {
            times++;
            has = true;
        }
        if (times == 2) {
            ancestor = node;
        }
        return has;
    }

    private boolean isEqual(int val) {
        return val == pVal || val == qVal;
    }
}
