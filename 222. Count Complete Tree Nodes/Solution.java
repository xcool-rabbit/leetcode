// 222. 完全二叉树的节点个数
/*
  遍历，over
  这种遍历显然没有利用上完全二叉树的性质，但是呢，既然已经这么快了，就算了
  不过这里还是提供一种思路：
  完全二叉树很容易得到最大深度
  那么实际上遍历他的倒数第二层，就能算出整个树的节点个数
  题解呢，比我这个更进一步，在遍历倒数第二层的时候还使用了二分查找的思想！
  并且巧妙的将位运算与二叉树的遍历结合到了一起，很牛逼！
  不过就是感觉有一点杀鸡焉用宰牛刀的感觉。。。
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
    public int nodeNum;

    public int countNodes(TreeNode root) {
        nodeNum = 0;
        dfs(root);
        return nodeNum;
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        nodeNum++;
        dfs(node.left);
        dfs(node.right);
    }
}
