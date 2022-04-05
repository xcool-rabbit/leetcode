// 剑指 Offer 52. 两个链表的第一个公共节点
/*
  这题写过，当时就是统计的长度，然后遍历判断相等
  这样其实就是O(2n)，跟我接下来要说的看似巧妙的方法没什么区别
  双指针，a到头切到b，b到头切到a，判断相等
  这样不需要统计长度，但是本质上，还是遍历了两遍
  执行用时：1 ms, 在所有 Java 提交中击败了99.00%的用户
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
