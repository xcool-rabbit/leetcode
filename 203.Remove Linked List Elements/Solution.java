//203. 移除链表元素
/*
  考察删除链表结点
  涉及到头结点的关系，删除头结点和删除中间结点的步骤不一样，需要区别对待，那么这样其实很麻烦
  不如，在头结点前面自己声明一个新的头，这样以前的头结点也就变成了中间结点
  执行用时：1 ms 已经战胜 100.00 % 的 java 提交记录
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
    public ListNode removeElements(ListNode head, int val) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode newHead = pre;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = pre.next;
            }
            cur = cur.next;
        }
        return newHead.next;
    }
}
