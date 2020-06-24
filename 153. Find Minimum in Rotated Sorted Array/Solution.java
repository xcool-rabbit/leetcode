//153. 寻找旋转排序数组中的最小值
/*
  emmmm一言难尽，不知道这个题想干什么
  O(N)遍历，就能找到最小值
  执行用时：0 ms 已经战胜 100.00 % 的 java 提交记录
*/
class Solution {
    public int findMin(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return nums[i + 1];
            }
        }
        return nums[0];
    }
}
