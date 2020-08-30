//429. N叉树的层序遍历
/*
  含有分层的层序遍历
  5 ms,在所有Java提交中击败了16.64%的用户
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
    public List<List<Integer>> levelOrder(Node root) {
        Queue<Node> queue = new LinkedList<>();
        Queue<Node> nextLevel = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> level = new ArrayList<>();
        if (root == null) {
            return result;
        }
        queue.offer(root);
        Node cur;
        while (!queue.isEmpty() || !nextLevel.isEmpty()) {
            if (!queue.isEmpty()) {
                cur = queue.poll();
                level.add(cur.val);
                for (Node n : cur.children) {
                    nextLevel.offer(n);
                }
            } else {
                queue = nextLevel;
                nextLevel = new LinkedList<>();
                result.add(level);
                level = new ArrayList<>();
            }
        }
        result.add(level);
        return result;
    }
}
/*
  看了各种实现方法，感觉没有什么思想上的创新，没有必要纠结了
  关于如何分层遍历，看到了一种思路是记录当前层元素的数量，理论上来讲应当是比我这种声明了两个队列的要好
  唯一收获的一点是addAll()比循环add()要快
  4 ms,在所有Java提交中击败了55.71%的用户
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
    public List<List<Integer>> levelOrder(Node root) {
        Queue<Node> queue = new LinkedList<>();
        Queue<Node> nextLevel = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> level = new ArrayList<>();
        if (root == null) {
            return result;
        }
        queue.offer(root);
        Node cur;
        while (!queue.isEmpty() || !nextLevel.isEmpty()) {
            if (!queue.isEmpty()) {
                cur = queue.poll();
                level.add(cur.val);
                nextLevel.addAll(cur.children);
            } else {
                queue = nextLevel;
                nextLevel = new LinkedList<>();
                result.add(level);
                level = new ArrayList<>();
            }
        }
        result.add(level);
        return result;
    }
}
/*
  还有一种解法，是分层遍历时特有的递归解法
  在有层数标注的情况下，递归的结构也能正确的遍历
  但是我实在不想再写了
*/