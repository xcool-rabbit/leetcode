// 143. 重排链表
/*
  综合题
  第一步找到中点
  第二步把后面的翻转
  第三步两个链表插入融合
  妙啊
  这个题也收获了不少经验
  连续两个next的时候就要注意null的情况
  变量名起的越多，循环越好写
  执行用时：1 ms, 在所有 Java 提交中击败了99.93%的用户
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
    public void reorderList(ListNode head) {
        // find middle
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast == slow) {
            return;
        }
        ListNode mid = slow;
        // reverse
        ListNode pre = mid;
        ListNode cur = mid.next;
        if (cur == null) {
            return;
        }
        ListNode next = cur.next;
        cur.next = pre;
        pre.next = null;
        while (next != null) {
            pre = cur;
            cur = next;
            next = cur.next;
            cur.next = pre;
        }
        // fusion
        ListNode newHead = cur;
        ListNode cur1 = head, cur2;
        while (newHead != null) {
            cur2 = newHead;
            newHead = newHead.next;
            cur2.next = cur1.next;
            cur1.next = cur2;
            cur1 = cur1.next.next;
        }
        cur1.next = null;
    }
}
