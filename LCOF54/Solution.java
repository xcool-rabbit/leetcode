// 剑指 Offer 54. 二叉搜索树的第k大节点
/*
  人傻了，一开始写的是遍历，不需要list存储
  后来发现是第k大，然后就觉得要存储，要遍历所有的，才知道第k大
  然后发现慢
  我幡然醒悟，我从右边开始遍历，不就能找到第k大
  执行用时：1 ms, 在所有 Java 提交中击败了29.19%的用户
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
    List<Integer> list;
    public int kthLargest(TreeNode root, int k) {
        list = new ArrayList<>(k);
        dfs(root);
        return list.get(list.size() - k);
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        list.add(node.val);
        dfs(node.right);
    }
}
/*
  不存储，直接遍历
  其实这个还有优化空间，这个遍历了整个树，没有快速返回
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
    int count = 1;
    int k;
    int ret;
    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return ret;
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        dfs(node.right);
        if (count++ == k) {
            ret = node.val;
        }
        dfs(node.left);
    }
}