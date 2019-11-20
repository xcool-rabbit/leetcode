//160. 相交链表
/*
  求出两个链表的长度
  让长的那个先走几步
  然后两个链表一起从头走，相等的时候就是相交
  执行用时：1 ms 已经战胜 100.00 % 的 java 提交记录
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lengthA = getLinkedListLength(headA);
        int lengthB = getLinkedListLength(headB);
        if (lengthA >= lengthB) {
            for (int i = lengthA - lengthB; i > 0; i--) {
                headA = headA.next;
            }
        } else {
            for (int i = lengthB - lengthA; i > 0; i--) {
                headB = headB.next;
            }
        }
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }
    
    private int getLinkedListLength(ListNode head) {
        int length = 1;
        if (head == null) {
            return 0;
        }
        while (head.next != null) {
            length++;
            head = head.next;
        }
        return length;
    }
}
