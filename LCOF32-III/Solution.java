// 剑指 Offer 32 - III. 从上到下打印二叉树 III
/*
  一行正一行反，用个变量标记一下就可以了
  执行用时：1 ms, 在所有 Java 提交中击败了96.73%的用户
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
        LinkedList<Integer> levelList = new LinkedList<>();
        if (root == null) {
            return result;
        }
        boolean reverse = false;
        while (!(deque.isEmpty() && bufferDeque.isEmpty())) {
            TreeNode current = deque.poll();
            if (reverse) {
                levelList.addFirst(current.val);
            } else {
                levelList.add(current.val);
            }
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
                levelList = new LinkedList<>();
                reverse = !reverse;
            }
        }
        return result;
    }
}
