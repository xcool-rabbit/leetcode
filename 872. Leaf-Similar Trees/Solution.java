// 872. 叶子相似的树
/*
  遍历，找到叶子节点，记录下来，最后比较，over
  优化的地方还能做不少，比如遍历root2的时候可以快速退出，但是为了函数功能的通用性可读性，不建议这样写
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
    List<Integer> leafValue;
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        leafValue = new ArrayList<>();
        dfs(root1);
        List<Integer> leafValue1 = new ArrayList<>(leafValue);
        leafValue.clear();
        dfs(root2);
        List<Integer> leafValue2 = new ArrayList<>(leafValue);
        if (leafValue1.size() != leafValue2.size()) {
            return false;
        }
        for (int i = 0; i < leafValue1.size(); i++) {
            if (leafValue1.get(i) != leafValue2.get(i)) {
                return false;
            }
        }
        return true;
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            leafValue.add(node.val);
            return;
        }
        dfs(node.left);
        dfs(node.right);
    }
}
