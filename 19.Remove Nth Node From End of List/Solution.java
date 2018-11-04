//19. 删除链表的倒数第N个节点
/*
  第一遍的做法，真的蠢，没什么好说的，直接看第二个吧
  遍历第一遍求得链表长度，第二遍实现定位
  这个题也怪恶心的，要删除头结点的时候，需要特殊考虑；另外链表长度为1的时候也是特殊情况
  执行用时：32 ms 已经战胜 12.80 % 的 java 提交记录
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
        
        if (length == 1)
            return null;
        if (length == n) {
            head = head.next;
            return head;
        }
        
        p = head;
        for (int i = length; i > n + 1; i--)
            p = p.next;
        p.next = p.next.next;
        return head;
    }
}
