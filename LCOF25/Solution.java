// 剑指 Offer 25. 合并两个排序的链表
/*
  谁小就用谁呗，懒得写了，跟主站21题一样，那个题已经写过了
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
