// 92. 反转链表 II
/*
  链表题，一生之敌！
  自己感悟吧，这种东西根本讲不清楚，很无语
  参考反转链表I写的
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode beforeHead = new ListNode();
        beforeHead.next = head;
        ListNode opBeforeHead = beforeHead;
        ListNode opTail = head;
        for (int i = right -left; i > 0; i--) {
            opTail = opTail.next;
        }
        for (int i = 1; i < left; i++) {
            opBeforeHead = opBeforeHead.next;
            opTail = opTail.next;
        }
        ListNode opLeft = opBeforeHead.next;
        if (opLeft.next == null) {
            return head;
        }
        ListNode rest;
        ListNode end = opTail.next;
        while (opLeft.next != end) {
            rest = opLeft.next.next;
            opLeft.next.next = opBeforeHead.next;
            opBeforeHead.next = opLeft.next;
            opLeft.next = rest;
        }
        return beforeHead.next;
    }
}
