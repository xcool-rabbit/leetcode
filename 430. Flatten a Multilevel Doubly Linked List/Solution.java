//430. 扁平化多级双向链表
/*
  一开始没读懂题让干什么，有点绕
  深度遍历，把多级链表按照图示变成普通的链表
  深度遍历没遍历到的结点存起来放到栈里
  深度到底了之后再拼上
  执行用时：0 ms 已经战胜 100.00 % 的 java 提交记录
*/
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    public Node flatten(Node head) {
        Node answer = head;
        Stack<Node> stack = new Stack<>();
        while (head != null) {
            if (head.child != null) {
                Node tmp = head.next;
                if (tmp != null) {
                    stack.push(tmp);
                }
                head.next = head.child;
                head.child = null;
                head.next.prev = head;
            }
            if (head.next == null && head.child == null) {
                break;
            }
            head = head.next;
        }
        while (!stack.empty()) {
            Node tmp = stack.pop();
            head.next = tmp;
            tmp.prev = head;
            while (head.next != null) {
                head = head.next;
            }
        }
        return answer;
    }
}
