//19. 删除链表的倒数第N个节点
/*
  第一遍的做法，真的蠢，没什么好说的，直接看第二个吧
  遍历第一遍求得链表长度，第二遍实现定位
  这个题也怪恶心的，要删除头结点的时候，需要特殊考虑；另外链表长度为1的时候也是特殊情况
  执行用时：32 ms 已经战胜 12.80 % 的 java 提交记录
  最近可能LeetCode服务器不太稳定，这套代码最快能跑到 执行用时：11 ms 已经战胜 93.87 % 的 java 提交记录
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p = head;
        int length = 0;
        while (p != null) {
            length++;
            p = p.next;
        }
        
        if (length == n) //[1] 1 的情况也被算在这个里面
            return head.next;
        
        p = head;
        for (int i = length; i > n + 1; i--)
            p = p.next;
        p.next = p.next.next;
        return head;
    }
}
/*
  两个指针，尾指针先向后移动n，然后，头尾指针再一起向后移动。
  这样的话，头指针的下个结点就是倒数第n个结点。
  需要注意的是，如果尾指针移到最后是null了，那么就说明，头结点要被删除（这是一个简单的数量关系），直接返回头结点的下个结点即可。
  执行用时：15 ms 已经战胜 63.81 % 的 java 提交记录
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p = head;
        ListNode t = head;
        
        for (int i = 0; i < n && t != null; i++)
            t = t.next;
        
        if (t == null) //这种情况就是删除头结点
            return head.next;
        
        while (t.next != null) {
            t = t.next;
            p = p.next;
        }
        p.next = p.next.next;
        return head;
    }
}
