// 剑指 Offer 07. 重建二叉树
/*
  与主站LCOF105相同，不再赘述
  执行用时：1 ms, 在所有 Java 提交中击败了99.95%的用户
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
    int preIndex;
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preIndex = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return parse(preorder, 0, preorder.length - 1, map);
    }
    
    private TreeNode parse(int[] preorder, int left, int right, Map<Integer, Integer> map) {
        if (left > right) {
            return null;
        }
        
        if (preIndex >= preorder.length) {
            return null;
        }
        int val = preorder[preIndex++];
        TreeNode node = new TreeNode(val);
        int split = map.get(val);
        node.left = parse(preorder, left, split - 1, map);
        node.right = parse(preorder, split + 1, right, map);
        return node;
    }
}
