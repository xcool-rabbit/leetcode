//142. 环形链表 II
/*
  环形链表的问题用快慢指针，但是这道题还要求给出入环的结点的相关信息
  一开始我以为要返回的是索引位置，还想着去从头count，但直到写完程序要return的时候才发现，只需要返回入环结点就行了
  首先还是利用快慢指针判断这是不是一个环形链表
  然后呢？从头开始每个节点走一圈看看是不是会跟自己相遇？我一开始只能想到这一个办法了，但我感觉这不会是正确答案，看起来有丶蠢，一定有什么奇淫巧技
  答案一看，豁然开朗（答案当中有一个比我的方法还要聪明一点的方法但是都超时了，这更加安慰了我看答案的犯罪心理）
  在判断是不是环形链表的时候，慢指针走了n步，那么快指针走了2n步，他们之间相差的路程就是环形的长度
  有啥用呢？这时候如果慢指针不动，把快指针放回head，然后也一次走一步
  那么可以预见的是他们再走n步一定会再次回到上一次相遇的位置
  那么在相遇前（包含相遇的位置），他们两个就会一起走
  那么第一次一起走的位置，就是入环的位置
  执行用时：0 ms 已经战胜 100.00 % 的 java 提交记录
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
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode fast = head.next.next;
        ListNode slow = head.next;
        int meet = 1;
        while (fast != slow) {
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            meet++;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
