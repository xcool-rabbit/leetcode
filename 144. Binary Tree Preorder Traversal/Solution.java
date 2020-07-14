//144. 二叉树的前序遍历
/*
  递归方法
  执行用时：0 ms 已经战胜 100.00 % 的 java 提交记录
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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(result, root);
        return result;
    }
    
    private void dfs(List<Integer> list, TreeNode node) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        dfs(list, node.left);
        dfs(list, node.right);
    }
}
/*
  非递归，模拟递归时栈的操作
  执行用时：1 ms 已经战胜 47.48 % 的 java 提交记录
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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!(stack.empty() && root == null)) {
            if (root != null) {
                result.add(root.val);
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                root = root.right;
            }
        }
        return result;
    }
}
/*
  还有一种自创方法
  这种非主流的方法我不想记住，自己看吧
  取出一个放进两个，有点BFS那个思想在里面
  执行用时：1 ms 已经战胜 46.84 % 的 java 提交记录
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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode tmp = stack.pop();
            if (tmp == null) {
                continue;
            }
            result.add(tmp.val);
            stack.push(tmp.right);
            stack.push(tmp.left);
        }
        return result;
    }
}
