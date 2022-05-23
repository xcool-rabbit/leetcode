// 剑指 Offer 59 - I. 滑动窗口的最大值
/*
  同主站239
  执行用时：10 ms, 在所有 Java 提交中击败了83.02%的用户
*/
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return nums;
        }
        int[] ret = new int[nums.length - k + 1];
        LinkedList<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (!deque.isEmpty() && deque.peek() <= i - k) {
                deque.poll();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.add(i);
            if (i - k + 1 >= 0) {
                ret[i - k + 1] = nums[deque.peek()];
            }
        }
        return ret;
    }
}
