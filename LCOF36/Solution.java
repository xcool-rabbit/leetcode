// 剑指 Offer 36. 二叉搜索树与双向链表
/*
  顶中顶，是真难搞
  总的来讲，就是，先弄好左边的，然后把中间接起来，然后再接右边的
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
class Solution {
    public Node treeToDoublyList(Node root) {
        return dfs(root);
    }

    public Node dfs(Node root) {
        if (root == null) {
            return null;
        }
        Node left = dfs(root.left);
        Node right = root.right;
        if (left != null) {
            root.left = left.left;
            left.left.right = root;
            left.left = root;
            root.right = left;
        } else {
            left = root;
            left.left = root;
            left.right = root;
        }
        right = dfs(right);
        if (right != null) {
            left.left = right.left;
            right.left.right = left;
            root.right = right;
            right.left = root;
        }
        return left;
    }
}
