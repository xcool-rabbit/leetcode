// 103. 二叉树的锯齿形层序遍历
/*
  广度遍历，借助队列
  不一样的是，一行从左到右，下一行从右到左
  我一贯的做法是把每一行都分开
  开两个队列
  到换行的时候需要进行一些操作
  然后再看情况交替reverse，这题就ac啦
  执行用时：1 ms, 在所有 Java 提交中击败了89.64%的用户
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        Queue<TreeNode> nextLine = new LinkedList<>();
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        List<Integer> curLine = new ArrayList<>(queue.size());
        boolean reverse = false;
        while (true) {
            TreeNode tmp = queue.poll();
            if (tmp == null) {
                if (reverse) {
                    int left = 0;
                    int right = curLine.size() - 1;
                    while (left < right) {
                        int swap = curLine.get(left);
                        curLine.set(left, curLine.get(right));
                        curLine.set(right, swap);
                        left++;
                        right--;
                    }
                }
                ret.add(curLine);
                reverse = !reverse;
                if (nextLine.peek() == null) {
                    return ret;
                }
                queue = nextLine;
                nextLine = new LinkedList<>();
                curLine = new ArrayList<>(queue.size());
            } else {
                curLine.add(tmp.val);
                if (tmp.left != null) {
                    nextLine.add(tmp.left);
                }
                if (tmp.right != null) {
                    nextLine.add(tmp.right);
                }
            }
        }
    }
}
