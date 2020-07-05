//133. 克隆图
/*
  DFS
  visited需要用一个Map来存储
  耗时不必太过在意，跟我写的一模一样的有很快的，运气问题，解法没问题，就不纠结了
  执行用时：38 ms 已经战胜 36.57 % 的 java 提交记录
*/
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Map<Integer, Node> map = new HashMap<>(100);
        return dfs(node, map);
    }
    
    private Node dfs(Node node, Map<Integer, Node> map) {
        Node tmp = map.get(node.val);
        if (tmp != null) {
            return tmp;
        }
        Node newNode = new Node(node.val, new ArrayList<>(node.neighbors.size()));
        map.put(node.val, newNode);
        for (Node n : node.neighbors) {
            newNode.neighbors.add(dfs(n, map));
        }
        return newNode;
    }
}
