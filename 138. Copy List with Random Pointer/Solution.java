//138. 复制带随机指针的链表
/*
  觉得没什么操作空间，就是一点一点复制
  先把链表复制了，然后逐个复制random
  执行用时：4 ms 已经战胜 19.41 % 的 java 提交记录
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
        Node answer = new Node(0);
        Node dst = answer;
        Node src = head;
        while (src != null) {
            dst.next = new Node(src.val);
            src = src.next;
            dst = dst.next;
        }
        Node index;
        src = head;
        dst = answer.next;
        while (src != null) {
            if (src.random != null) {
                int count = 0;
                index = head;
                while (index != src.random) {
                    index = index.next;
                    count++;
                }
                index = answer.next;
                while (count > 0) {
                    index = index.next;
                    count--;
                }
                dst.random = index;
            }
            src = src.next;
            dst = dst.next;
        }
        return answer.next;
    }
}
/*
  可以看到性能不是很理想，很大一部分时间花在了找random指针的位置
  有两种巧妙的解法都可以解决这个问题：
  1. 存储。这个思想我也想到了，但是没想好应该怎么存。random指针很大程度上需要依赖数组式的查询，但是random的指向并不自带位置信息。
     所以不应该是将新链表按照数组形式存储。这时一个巧妙的解决方案横空出世：map存储旧链表与新链表的KV关系。
     第一遍遍历旧链表，复制新链表，建立map。第二遍遍历两个链表同时遍历，开始复制random，以旧链表的random作为key，可以直接找到新链表应该指向的新random
  2. 还是存储。这次不借助map了，但是还是能快速的找到random。本质上跟上一个方法的思路是一样的，让新旧链表之间产生关系。
     在每个旧结点后面复制一下，然后第二次遍历复制random，新的random也很好找，就在旧random的后面。
     最后将新链表跟旧链表解开。
  选用第一种方案实现
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
