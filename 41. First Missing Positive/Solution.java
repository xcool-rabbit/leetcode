// 41. 缺失的第一个正数
/*
  交换的思路，把正确的数放到正确的位置
  再遍历一遍，看看缺谁
  有一点桶排序的意思了，并且并没有用额外空间，就在原数组里操作
  执行用时：2 ms, 在所有 Java 提交中击败了95.35%的用户
*/
class Solution {
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[i] - 1 != i) {
                // swap(i, nums[i] - 1);
                int tmp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = tmp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - 1 != i) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
}
