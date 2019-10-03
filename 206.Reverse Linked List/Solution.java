//206. 
/*
  反转链表的方式有很多，正是因为方式很多，才让我感到手足无措。
  直觉来讲，第一个想到的方式一定是，把链表顺序遍历，存到数组里，然后新建一个链表，顺序遍历数组，头插法将结点插入链表。
  这种方式会额外占用O(N)的空间。那如何不占用空间呢？那就节俭一点。
  按住初始链表的头结点，每一次都将头结点的后一个结点，挪到链表的开头，即可将链表反转。代码即用的这种方式。
  执行用时：0 ms 已经战胜 100.00 % 的 java 提交记录
*/
class Solution {
  public ListNode reverseList(ListNode head) {
      ListNode origin = head;
      ListNode rest;
      
      if (head == null || head.next == null) {
          return head;
      }
      while (origin.next != null) {
          rest = origin.next.next;
          origin.next.next = head;
          head = origin.next;
          origin.next = rest;
      }
      return head;
      
  }
}
/*
  还有一种想法就是，将链表当中的每个箭头的指向反过来，就相当于反转了链表。
  这种想法就是递归的思路。从后往前，一个个将箭头的指向反过来。
  因为递归具有栈的特性，所以可以直接压栈访问到最后一个结点。
  需要注意要将head的next置为null，否则的话OJ在验证的时候会遍历到一个循环链表。
  执行用时：0 ms 已经战胜 100.00 % 的 java 提交记录
*/
class Solution {
  public ListNode reverseList(ListNode head) {
      if (head == null || head.next == null) {
          return head;
      }
      ListNode newHead = reverseList(head.next);
      head.next.next = head;
      head.next = null;
      return newHead;
  }
}
