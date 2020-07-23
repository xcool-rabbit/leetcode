//106. 从中序与后序遍历序列构造二叉树
/*
  后序遍历可以确定根节点
  有了根节点从中序遍历中划分出左右结点
  左子树和右子树递归
  执行用时：58 ms 这种方法很憨，不知道超过了多少人
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        List<Integer> inorderList = new ArrayList<>(inorder.length);
        List<Integer> postorderList = new ArrayList<>(postorder.length);
        for (int n : inorder) {
            inorderList.add(n);
        }
        for (int n : postorder) {
            postorderList.add(n);
        }
        return parse(inorderList, postorderList);
    }
    
    private TreeNode parse(List<Integer> inorder, List<Integer> postorder) {
        if (inorder.size() == 0) {
            return null;
        }
        int root = postorder.get(postorder.size() - 1);
        List<Integer> leftInorder = inorder.subList(0, inorder.indexOf((Integer)root));
        List<Integer> rightInorder = inorder.subList(inorder.indexOf((Integer)root) + 1, inorder.size());
        List<Integer> leftPostorder = postorder.subList(0, leftInorder.size());
        List<Integer> rightPostorder = postorder.subList(leftInorder.size(), postorder.size() - 1);
        TreeNode node = new TreeNode(root);
        node.left = parse(leftInorder, leftPostorder);
        node.right = parse(rightInorder, rightPostorder);
        return node;
    }
}
/*
  不太理解慢在哪里，跟优秀的解法用时差距悬殊
  我分析的提速可能出现在subList的操作，可以用两个变量定义子树的起止点，但是觉得这样挺麻烦的，并且不应该差这么多
  我以为是原理性的差距，就直接看了题解
  没想到居然是......缓存！
  递归里面有一项indexOf
  我将indexOf缓存了一下，递归里面少了一次indexOf，居然......接近翻倍的执行效率！
  执行用时：31 ms 依然没有排名
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        List<Integer> inorderList = new ArrayList<>(inorder.length);
        List<Integer> postorderList = new ArrayList<>(postorder.length);
        for (int n : inorder) {
            inorderList.add(n);
        }
        for (int n : postorder) {
            postorderList.add(n);
        }
        return parse(inorderList, postorderList);
    }
    
    private TreeNode parse(List<Integer> inorder, List<Integer> postorder) {
        if (inorder.size() == 0) {
            return null;
        }
        int root = postorder.get(postorder.size() - 1);
        int index = inorder.indexOf((Integer)root);
        List<Integer> leftInorder = inorder.subList(0, index);
        List<Integer> rightInorder = inorder.subList(index + 1, inorder.size());
        List<Integer> leftPostorder = postorder.subList(0, leftInorder.size());
        List<Integer> rightPostorder = postorder.subList(leftInorder.size(), postorder.size() - 1);
        TreeNode node = new TreeNode(root);
        node.left = parse(leftInorder, leftPostorder);
        node.right = parse(rightInorder, rightPostorder);
        return node;
    }
}
/*
  所以官方解法把每个值都放到Map里面缓存了，并且用边界来标记树的范围
  最值得借鉴的地方是它把中序遍历完全当工具人，核心是围绕着后序遍历做的
  这一点我没有看透
  后面还有类似的题，这一道就不再重构了
*/
