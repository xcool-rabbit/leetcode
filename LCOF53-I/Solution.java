// 剑指 Offer 53 - I. 在排序数组中查找数字 I
/*
  二分，找到target之后左右扩展
  其实啊，应该是做两次二分的
  把两边的边界都给你整的明明白白
  偷懒了（溜
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int search(int[] nums, int target) {
        int index = Arrays.binarySearch(nums, target);
        if (index < 0) {
            return 0;
        }
        int start = index;
        while (start >= 0 && nums[start] == target) {
            start--;
        }
        int end = index;
        while (end < nums.length && nums[end] == target) {
            end++;
        }
        return end - start - 1;
    }
}
