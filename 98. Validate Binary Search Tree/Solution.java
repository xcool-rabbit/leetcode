//98. 验证二叉搜索树
/*
  呕呕呕，智商低属实恶心到了自己
  本来以为就是个递归，判断子树是二叉搜索树而且自己比左大右小就行了，
  然后发现要保证比子树的所有结点都要小，所以还要对子树的最大最小值做标记
  测试用例当中涉及到了int的边界值，所以最好是将最大最小值用long存储
  执行用时：1 ms 已经战胜 99.50 % 的 java 提交记录
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
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return validChild(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validChild(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (!validChild(root.left, min, root.val) || !validChild(root.right, root.val, max)) {
            return false;
        }
        if (root.left != null) {
            if ((!(root.left.val < root.val) || !(min < root.left.val && root.left.val < max))) {
                return false;
            }
        }
        if (root.right != null) {
            if (!(root.right.val > root.val) || !(min < root.right.val && root.right.val < max)) {
                return false;
            }
        }
        return true;
    }
}
/*
  第二种思路，如果你是一个二叉搜索树的话，那么我中序遍历之后的结果应该是一个有序的
  不过很意外的是，这种做法居然要慢很多，应该是第一种方法有快速判断的功能吧
  执行用时：3 ms 已经战胜 45.59 % 的 java 提交记录
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
    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        DFS(root, list);
        for (int i = 0; i < list.size() - 1; i++) {
            if (!(list.get(i) < list.get(i + 1))) {
                return false;
            }
        }
        return true;
    }
    
    private void DFS(TreeNode root, List list) {
        if (root == null) {
            return;
        }
        DFS(root.left, list);
        list.add(root.val);
        DFS(root.right, list);
    }
}
