// 148. 排序链表
/*
  排序就用快排噻
  但是要用链表写
  链表题对我来讲真的好难，画图都画不明白，然后该声明的变量也不知道有哪些，然后链表摘来摘去的
  倒霉快排，变态测试用例，让我直接超时，退化成O(n ^ 2)
  RTE
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
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return head;
        }
        if (head.next == null) {
            return head;
        }
        int pivot = head.val;
        ListNode left = null;
        ListNode right = null;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.next.val > pivot) {
                ListNode tmp = cur.next;
                cur.next = cur.next.next;
                tmp.next = right;
                right = tmp;
            } else {
                cur = cur.next;
            }
        }
        left = head.next;
        left = sortList(left);
        right = sortList(right);
        if (left != null){
            cur = left;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = head;
        }
        head.next = right;
        if (left == null) {
            left = head;
        }
        return left;
    }
}
/*
  不要气馁，咱快排跟归并，差不多的
  我先去912题做了一下归并排序
  其实这个题，用归并排序是最有优势的，因为不需要额外空间，归并的时候很快
  题解还有一个不用递归的，自底向上的归并，看了下代码量，巨多，就不实现了（逃
  现代计算机，内存大多时候不是问题
  执行用时：5 ms, 在所有 Java 提交中击败了99.39%的用户
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
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode mid = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        ListNode ret = new ListNode();
        ListNode cur = ret;
        while (left != null && right != null) {
            if (left.val < right.val) {
                cur.next = left;
                left = left.next;
                cur = cur.next;
            } else {
                cur.next = right;
                right = right.next;
                cur = cur.next;
            }
        }
        if (left != null) {
            cur.next = left;
        }
        if (right != null) {
            cur.next = right;
        }
        return ret.next;
    }
}