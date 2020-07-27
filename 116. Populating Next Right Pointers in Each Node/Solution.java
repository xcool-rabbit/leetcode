//116. 填充每个节点的下一个右侧节点指针
/*
  第一个想到的是层序遍历啊
  但是层序遍历需要的空间是最底下一层的数量，也就是2 / N
  不太符合题目要求
  但是也不应该慢啊
  如果说慢的话，应该就是队列操作的开销
  执行用时：5 ms 已经战胜 11.90 % 的 java 提交记录
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
        Queue<Node> queue = new LinkedList<>();
        Queue<Node> level = new LinkedList<>();
        queue.offer(root);
        while (!(queue.isEmpty() && level.isEmpty())) {
            if (!queue.isEmpty()) {
                Node node = queue.poll();
                node.next = queue.peek();
                if (node.left != null) {
                    level.offer(node.left);                    
                }
                if (node.right != null) {
                    level.offer(node.right);                    
                }
            } else {
                queue = level;
                level = new LinkedList<>();
            }
        }
        return root;
    }
}
/*
  经过分析，一个结点的左孩子的next就是右孩子
  右孩子的next就是该结点的next的左孩子
  递归遍历，快上加快
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
        dfs(root);
        return root;
    }
    
    private void dfs(Node node) {
        if (node.left == null) {
            return;
        }
        node.left.next = node.right;
        node.right.next = node.next == null ? null : node.next.left;
        
        dfs(node.left);
        dfs(node.right);
    }
}
