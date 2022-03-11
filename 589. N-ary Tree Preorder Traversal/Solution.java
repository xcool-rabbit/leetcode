//589. N叉树的前序遍历
/*
  递归解法
  1 ms,在所有Java提交中击败了96.75%的用户
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
    public List<Integer> preorder(Node root) {
        result = new ArrayList<>();
        dfs(root);
        return result;
    }

    private void dfs(Node root) {
        if (root == null) {
            return;
        }

        result.add(root.val);
        for (Node child : root.children) {
            dfs(child);
        }
    }
}
/*
  迭代解法
  虽然迭代没有递归快，但是还是需要会写
  这是虚假的迭代解法，真正有水平的迭代写法会按照递归的逻辑来写
  但是迭代没法记录已经遍历过的子节点，所以只能用虚假的迭代
  4 ms,在所有Java提交中击败了38.60%的用户
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
    public List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node cur;
        while (!stack.empty()) {
            cur = stack.pop();
            result.add(cur.val);
            for (int i = cur.children.size() - 1; i >= 0; i--) {
                stack.push(cur.children.get(i));
            }
        }
        return result;
    }
}
/*
  它有幸被选中每日一题
  很简单，我直接复制的之前的代码，但是在新的用例下，它已经不够快了
  还好我对Java的数据结构有一定的理解，ArrayList改成LinkedList就过了
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
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
    public List<Integer> preorder(Node root) {
        result = new LinkedList<>();
        dfs(root);
        return result;
    }

    private void dfs(Node root) {
        if (root == null) {
            return;
        }

        result.add(root.val);
        for (Node child : root.children) {
            dfs(child);
        }
    }
}
