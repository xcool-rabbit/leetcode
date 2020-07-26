//105. 从前序与中序遍历序列构造二叉树
/*
  官方解法确实不一般嗷，只有真正实现一下才知道其中的精妙
  我最一开始想到的办法是通过前序遍历和中序遍历，来划分左右子树，然后递归
  需要在前序遍历和中序遍历里面都标记出来树的范围
  这种方法是没问题的，也并不慢，缺点就是传的参数有点多
  那么官方解法精妙就精妙在解决了传参过多的问题
  利用了前序遍历（后序遍历）的一个特点：
  以前序遍历为例，先创造出当前结点
  然后递归创造左子树
  在这种递归顺序下，每次递归用到的根节点的访问顺序就是从前往后
  所以在这种递归方式下是不需要前序遍历的参数来辅助确定根节点的
  正常思路能优化到这一步我愿称之为牛逼好吧，简直就是人类智慧的结晶
  执行用时：2 ms 已经战胜 98.28 % 的 java 提交记录
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
