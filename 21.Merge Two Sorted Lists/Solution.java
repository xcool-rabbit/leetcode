//21. 合并两个有序链表
/*
  尾插法，谁小插谁
  执行用时：2 ms 已经战胜 96.93 % 的 java 提交记录
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode merge = null;
        ListNode tail = null;
        
        if (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                merge = l1;
                l1 = l1.next;
            } else {
                merge = l2;
                l2 = l2.next;
            }
            merge.next = null;
            tail = merge;
        } else if (l1 == null && l2 == null) {
            return null;
        } else if (l1 == null && l2 != null) {
            return l2;
        } else if (l1 != null && l2 == null) {
            return l1;
        }
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
            tail.next = null;
        }
        if (l1 != null) {
            tail.next = l1;
        }
        if (l2 != null) {
            tail.next = l2;
        }
        
        return merge;
    }
}
