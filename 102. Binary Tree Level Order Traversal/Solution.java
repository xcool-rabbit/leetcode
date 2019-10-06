//102. 二叉树的层次遍历
/*
  队列实现，非常的经典。不同的地方在于这个要一层一层的输出。
  我想到的办法就是弄两个队列，一个当做buffer，一个当前的，然后当前的遍历光了就将buffer拷过来
  但是居然，是最慢的做法。所以省去copy的过程应该会快一点。不愿意改了，问题不大。
  执行用时：3 ms 可能是超过了0%的人吧，所以那个图上都不写我超过了多少人
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
