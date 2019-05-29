//206. 反转链表
/*
  题目要求的是迭代和递归两种方法进行解答，鉴于这道题面试当中十分的常见，所以需要花费时间搞明白。
  递归方法，十分的头疼。递归是一个缩减问题规模的思想，所以先要写好递归的截止条件。然后进行倒推。
  具体到这道题上，假如某一中间时刻1->2->3<-4，注意递归每次开始的时候，head指针都指向原有序链表当中最后一个结点。将2->3改成2<-3即可。
  执行用时：1 ms 已经战胜 60.49 % 的 java 提交记录
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
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode reverseHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return reverseHead;
    }
}
