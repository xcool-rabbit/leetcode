//145. 二叉树的后序遍历
/*
  万万没想到这是我做过的第一道困难题
  就这？
  递归永远滴神！不解释
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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(list, root);
        return list;
    }
    
    private void dfs(List<Integer> list, TreeNode root) {
        if (root == null) {
            return;
        }
        
        dfs(list, root.left);
        dfs(list, root.right);
        list.add(root.val);
    }
}
/*
  虽然我知道非递归慢，但是还是要锻炼一下自己的思维
  不然的话配不上这道困难题
  不做不知道，一做吓一跳，后序遍历的非递归果然不一样
  非递归是模拟递归时的程序行为的
  但是非递归需要在两次node == null之后才会访问当前节点
  那么怎么在循环中辨识这两次null呢
  用一个集合记录
  在访问右子树之后，将该结点记录到集合中
  等到右子树访问完毕，再次回到该结点的时候
  由于集合中已经存在该结点，所以直接访问
  并将该结点置null
  null的意思就是这一级访问完毕，该访问上一级
  当然区分两次node == null的方法不止这一种
  记录前一个访问的结点，也可以区分这两次node == null
  前一个访问的结点如果是该结点的右子节点，那么该结点该输出了
  执行用时：1 ms 已经战胜 56.13 % 的 java 提交记录
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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> set = new HashSet<>();
        while (!(stack.empty() && root == null)) {
            if (root == null) {
                root = stack.pop();
                if (!set.contains(root)) {
                    set.add(root);
                    stack.push(root);
                    root = root.right;
                } else {
                    list.add(root.val);
                    root = null;
                }
            } else {
                stack.push(root);
                root = root.left;    
            }
        }
        return list;
    }
}
/*
  第三种方法并不是模拟的递归操作
  后序遍历“左右根”，之前我们提到了先访问左右存在区分的问题
  那我们可以按照“根右左”的顺序访问，然后再将结果倒过来，就可以得到后序遍历的顺序
  这种方式非常类似于前序遍历，只是将左右的顺序颠倒
*/
