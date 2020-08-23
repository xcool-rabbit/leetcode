//173. 二叉搜索树迭代器
/*
  作为一个迭代器，就不能用栈的方式遍历了
  因为迭代器都是一个一个遍历的，栈没法一个一个进行
  所以选用深度遍历的非递归算法
  执行用时：23 ms 已经战胜 95.43 % 的 java 提交记录
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
class BSTIterator {
    TreeNode cur;
    Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        cur = root;
        stack = new Stack<>();
    }
    
    /** @return the next smallest number */
    public int next() {
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        cur = stack.pop();
        int ret = cur.val;
        cur = cur.right;
        return ret;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !(stack.empty() && cur == null);
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
 