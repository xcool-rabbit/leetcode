// 剑指 Offer 22. 链表中倒数第k个节点
/*
  双指针，让快的那个先走k步
  然后再一起走，快的到头了，慢的就正好指向倒数第k个节点
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
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            head = head.next;
        }
        return head;
    }
}
