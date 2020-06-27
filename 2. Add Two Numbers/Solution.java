//2. 两数相加
/*
  最直白的方法当然是把两个数全读出来，然后算数相加，再生成一个链表
  据说这种方法会超出算数范围，我也没试过
  正常方法应该是诸位相加，需要考虑进位和两个链表长短的问题
  执行用时：2 ms 已经战胜 99.86 % 的 java 提交记录
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode i = l1;
        ListNode j = l2;
        while (i != null && j != null) {
            i = i.next;
            j = j.next;
        }
        ListNode longList = i == null ? l2 : l1;
        ListNode shortList = l1 == longList ? l2 : l1;
        ListNode answer = longList;
        boolean carry = false;
        
        while (true) {
            int longVal = longList.val;
            int shortVal = shortList == null ? 0 : shortList.val;
            int sum = carry ? (longVal + shortVal + 1) : (longVal + shortVal);
            longList.val = sum % 10;
            carry = sum > 9;
            if (longList.next == null) {
                if (carry) {
                    longList.next = new ListNode(1);
                }
                break;
            }
            longList = longList.next;
            shortList = shortList == null ? shortList : shortList.next;
        }
        return answer;
    }
}
