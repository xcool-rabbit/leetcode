// 剑指 Offer 24. 反转链表
/*
  把老head后面一个，挪到队头
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
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
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        while (head != null && head.next != null) {
            ListNode cur = head.next;
            head.next = cur.next;
            cur.next = dummyHead.next;
            dummyHead.next = cur;
        }
        return dummyHead.next;
    }
}
/*
  奇奇怪怪的思考逻辑，分成了正在操作的部分，和remain的部分
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
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
        if (head == null) {
            return head;
        }
        ListNode remain = head.next;
        head.next = null;
        while (remain != null) {
            ListNode tmp = remain;
            remain = remain.next;
            tmp.next = head;
            head = tmp;
        }
        return head;
    }
}
/*
  针对痛点命名！！！
  翻转链表里面的问题就是，缺个prev
*/
