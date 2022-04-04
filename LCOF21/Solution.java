// 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
/*
  这个跟快排原理一样的，把一些数放到左边，另一些放到右边
  但是我忘记了如何优雅的实现了，自己现想的
  执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int[] exchange(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] % 2 == 1) {
                left++;
                continue;
            }
            if (nums[right] % 2 == 0) {
                right--;
                continue;
            }
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            left++;
            right--;
        }
        return nums;
    }
}
