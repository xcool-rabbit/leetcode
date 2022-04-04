// 剑指 Offer 18. 删除链表的节点
/*
  引入一个dummyHead，大大方便了对头节点的处理
  删除节点需要保存prev节点
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
    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode prev = dummyHead;
        while (head != null) {
            if (head.val == val) {
                prev.next = head.next;
                return dummyHead.next;
            }
            prev = head;
            head = head.next;
        }
        return null;
    }
}
