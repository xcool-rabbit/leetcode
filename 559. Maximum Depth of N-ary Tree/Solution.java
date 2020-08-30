//559. N叉树的最大深度
/*
  写的时候挺灵性，凭直觉写的，没多想，就过了
  通常会再写一个函数用来递归，但是这个题可以直接写
  0 ms,在所有Java提交中击败了100.00%的用户
*/
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int max = 0;
        for (Node n : root.children) {
            max = Math.max(max, maxDepth(n));
        }
        return max + 1;
    }
}