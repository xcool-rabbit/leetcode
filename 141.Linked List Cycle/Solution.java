//141. 环形链表
/*
  需要注意的一点是，虽然java没有指针的概念，但是它也可以像C打印指针的地址值一样，引用的值也是可以比较的。
  基于这样的认识，就可以通过引用的值来判断结点是否相同。
  判断环形链表就是判断遍历过程当中是不是会有重复的结点，那么问题就转化为了如果判断重复。
  记录一下遍历的结点，再利用上集合这个数据结构，即可解决。
  另外一种思路，是我在做双指针分类问题的时候遇到的这道题。
  如果是环形链表，那也就是形成了一个圈。
  这里需要提到Floyd判圈算法。可以想象在一个环形跑道上，一个人速度比另一个人快，那么势必有一个时刻，快的人会追上慢的人。
  运用到环形链表上来，就是，设置两个指针，如果存在环形链表，那么快的指针终究会等于慢的指针；否则快的指针会先到达链表终点。
  执行用时：1 ms 已经战胜 92.34 % 的 java 提交记录
*/
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (true) {
            if (fast.next == null || fast.next.next == null) {
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
    }
}
