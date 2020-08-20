//117. 填充每个节点的下一个右侧节点指针 II
/*
  收到了I题的影响，思维定式了
  如果沿用I题的思路，通过递归遍历树的话，会出现一个问题就是
  遍历的路径一直从左子树深入，右边有一些next还没有连上
  这种缺陷在I题中没有暴露出来是因为I题是个满树
  被这个缺陷束缚住的话，觉得只能层序遍历了
  然后又觉得层序遍历会利用额外空间，无解
  在才参考题解之后，发现我陷入了I题的思维定式
  为了解决层序遍历占用额外空间的问题，可以利用题目的条件
  next数组穿起来的就是队列
  另外一个改进点在于我之前想的是孩子结点去找同级的下一个结点
  题解给出了更妙的方案，对子结点那一层利用一个变量去跟踪
  妙上加妙就是快
  执行用时：0 ms 已经战胜 100.00 % 的 java 提交记录
*/
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Node cur, head, nextLevel;
        cur = root;
        head = root;
        nextLevel = null;
        while (head != null) {
            cur = head;
            nextLevel = null;
            head = null;
            while (cur != null) {
                if (cur.left != null) {
                    if (nextLevel == null) {
                        head = cur.left;
                    } else {
                        nextLevel.next = cur.left;
                    }
                    nextLevel = cur.left;
                }
                if (cur.right != null) {
                    if (nextLevel == null) {
                        head = cur.right;
                    } else {
                        nextLevel.next = cur.right;
                    }
                    nextLevel = cur.right;
                }
                cur = cur.next;
            }
        }

        return root;
    }
}
