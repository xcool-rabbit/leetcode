// 25. K 个一组翻转链表
/*
  还是翻转链表，还是给你划定一个区域翻转，只不过要从头搞到尾
  至于为什么没有达到0秒，难道有人耍赖只换value？
  执行用时：1 ms, 在所有 Java 提交中击败了35.96%的用户
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
    public ListNode reverseKGroup(ListNode head, int k) {
        boolean lessThanK = false;
        ListNode beforeHead = new ListNode();
        beforeHead.next = head;
        ListNode opBeforeHead = beforeHead;
        ListNode opHead;
        ListNode opTail = head;
        for (int i = 1; i < k; i++) {
            if (opTail == null) {
                lessThanK = true;
                break;
            } else {
                opTail = opTail.next;
            }
        }

        while (!lessThanK) {
            opHead = opBeforeHead.next;
            ListNode rest;
            ListNode end = opTail.next;
            while (opHead.next != end) {
                if (opTail == null) {
                    lessThanK = true;
                } else {
                    opTail = opTail.next;
                }
                rest = opHead.next.next;
                opHead.next.next = opBeforeHead.next;
                opBeforeHead.next = opHead.next;
                opHead.next = rest;
            }
            opBeforeHead = opHead;
            if (opTail == null || opTail.next == null) {
                lessThanK = true;
            } else {
                opTail = opTail.next;
            }
        }
        
        return beforeHead.next;
    }
}
