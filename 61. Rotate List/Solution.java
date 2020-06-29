//61. 旋转链表
/*
  旋转一次就是将最后的挪到第一个
  面临的问题就是每次旋转都要去找最后一个结点，未免也太费事了
  所以需要想一些奇淫巧技
  干脆让最后一个结点连到头结点，成环！这样就好找了
  后来发现可以对k取模，避免了转很多圈，算是一个小优化
  执行用时：1 ms 已经战胜 84.10 % 的 java 提交记录
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
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        int length = 1;
        while (cur.next != null) {
            cur = cur.next;
            length++;
        }
        cur.next = head;
        cur = head;
        k = length - (k % length);
        while (k > 1) {
            k--;
            cur = cur.next;
        }
        head = cur.next;
        cur.next = null;
        return head;
    }
}
