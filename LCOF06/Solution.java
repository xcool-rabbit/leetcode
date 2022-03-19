// 剑指 Offer 06. 从尾到头打印链表
/*
  可能它是想考察反转链表？
  但是不可避免的是get到链表的长度
  那么既然长度都知道了，ret倒着赋值就可以了，甚至没有必要翻转链表
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
    public int[] reversePrint(ListNode head) {
        int length = 0;
        ListNode node = head;
        while (node != null) {
            node = node.next;
            length++;
        }
        int[] ret = new int[length];
        for (int i = length - 1; i >= 0; i--) {
            ret[i] = head.val;
            head = head.next;
        }
        return ret;
    }
}
