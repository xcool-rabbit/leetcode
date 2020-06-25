//234. 回文链表
/*
  题目很简单，可以用额外空间存储
  但是进阶要求时间复杂度O(N)空间复杂度O(1)
  也就是需要原地判断是否回文
  第一次快慢指针，将链表均分
  第二次原地反转链表，将头链表后面的结点丢到最前面去
  第三次从头开始比较
  执行用时：1 ms 已经战胜 99.66 % 的 java 提交记录
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
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;
        ListNode fast = head.next;
        ListNode low = head;
        while (fast != null && fast.next != null) {
            low = low.next;
            fast = fast.next.next;
        }
        ListNode cur = low.next;
        low.next = null;
        ListNode newHead = cur;
        while (cur != null && cur.next != null) {
            ListNode rest = cur.next.next;
            cur.next.next = newHead;
            newHead = cur.next;
            cur.next = rest;
        }
        while (head != null && newHead != null) {
            if (head.val != newHead.val) {
                return false;
            }
            head = head.next;
            newHead = newHead.next;
        }
        return true;
    }
}
