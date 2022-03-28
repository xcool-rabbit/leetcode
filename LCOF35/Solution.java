// 剑指 Offer 35. 复杂链表的复制
/*
  这道题难就难在random的复制
  正常方法要O(n ^ 2)才能完成random的连接
  可以用map来实现快速查找
  但是又不能按照val来存，这样不严谨
  那怎么存呢
  旧节点当key，对应的新节点当value
  第一遍只复制next
  然后，现在所有节点都有了
  第二遍复制random
  新旧链表同时遍历
  旧链表的random的value，就是新链表当前节点应该指向的random
  妙哇妙哇
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        Node HEAD = head;
        Node ret = new Node(head.val);
        Node tail = ret;
        Map<Node, Node> map = new HashMap<>();
        map.put(head, tail);
        head = head.next;
        while (head != null) {
            Node tmp = new Node(head.val);
            tail.next = tmp;
            tail = tmp;
            map.put(head, tail);
            head = head.next;
        }
        head = HEAD;
        tail = ret;
        while (head != null) {
            tail.random = map.get(head.random);
            head = head.next;
            tail = tail.next;
        }
        return ret;
    }
}
