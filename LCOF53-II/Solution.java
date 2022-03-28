// 剑指 Offer 53 - II. 0～n-1中缺失的数字
/*
  虽然O(n)能做，但是这个简单的二分，还是练一下叭
  需要注意的用例就是长度为1的时候，[0]和[1]没有办法分辨
  因为根本没有进循环
  解决方法是return之前再判断一下
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int missingNumber(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left < right) {
            mid = (left + right) >>> 1;
            if (nums[mid] == mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left] == left ? left + 1 : left;
    }
}
