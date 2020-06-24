//328. 奇偶链表
/*
  快慢指针
  执行用时：0 ms 已经战胜 93.35 % 的 java 提交记录
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
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        ListNode cur;
        while (fast != null && fast.next != null) {
            cur = fast.next;
            fast.next = fast.next.next;
            cur.next = slow.next;
            slow.next = cur;
            slow = slow.next;
            fast = fast.next;
        }
        return head;
    }
}
