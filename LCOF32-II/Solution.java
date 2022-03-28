// 剑指 Offer 32 - II. 从上到下打印二叉树 II
/*
  与主站102题相同
  队列
  分层就用cur和next记录当前层和下一层
  当前层空了，就把下一层挪上来
  执行用时：1 ms, 在所有 Java 提交中击败了75.59%的用户
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        Deque<TreeNode> bufferDeque = new LinkedList<>();
        deque.offer(root);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> levelList = new ArrayList<>();
        if (root == null) {
            return result;
        }
        while (!(deque.isEmpty() && bufferDeque.isEmpty())) {
            TreeNode current = deque.poll();
            levelList.add(current.val);
            if (current.left != null) {
                bufferDeque.offer(current.left);
            }
            if (current.right != null) {
                bufferDeque.offer(current.right);
            }
            if (deque.isEmpty()) {
                deque.addAll(bufferDeque);
                bufferDeque.clear();
                result.add(levelList);
                levelList = new ArrayList<>();
            }
        }
        return result;
    }
}
