//590. N叉树的后序遍历
/*
  递归
  1 ms,在所有Java提交中击败了96.34%的用户
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
    List<Integer> result;
    public List<Integer> postorder(Node root) {
        result = new ArrayList<>();
        dfs(root);
        return result;
    }

    private void dfs(Node root) {
        if (root == null) {
            return;
        }

        for (Node child : root.children) {
            dfs(child);
        }
        result.add(root.val);
    }
}
/*
  虚假的迭代也无法按照后序的方式遍历
  经过分析，后序遍历，倒过来，然后子结点顺序入栈，就跟前序遍历是一样的模式
  4 ms,在所有Java提交中击败了40.15%的用户
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
    public List<Integer> postorder(Node root) {
        LinkedList<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node cur;
        while (!stack.empty()) {
            cur = stack.pop();
            result.addFirst(cur.val);
            for (Node n : cur.children) {
                stack.push(n);
            }
        }
        return result;
    }
}