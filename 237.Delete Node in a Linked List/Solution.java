//237. 删除链表中的节点
/*
  这是一道很诡异的题，诡异到我根本没有看懂这道题，十分绝望。它的输入实际上只有一个节点，就是要被删除的节点。
  至于链表，根本是不给你的。就是要在没有链表头的前提下，做到删除节点。
  方法也很独特，我认为即使我理解了提议，我也不一定能做的出来。
  借刀杀人借尸还魂：想删除node结点，结果把它后面的结点删了，但是后面结点的值赋给前面，相当于，删除了后面结点的身体，但是保留了他的灵魂。
  从整个链表来看，数值和序列都没有任何的变化，达到了删除的目的。
  执行用时：0 ms 已经战胜 100.00 % 的 java 提交记录
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
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
