// 34. 在排序数组中查找元素的第一个和最后一个位置
/*
  二分
  但是一次二分只能找左边的或者右边的，好像没办法同时找
  于是我，在mid匹配之后就break出来，把left和right都暂存起来
  这样找right的时候就可以直接恢复了，不需要重新找
  之前2ms，现在0ms，所以这种方法很有效
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        int[] ret = {-1, -1};
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                break;
            } else {
                left = mid + 1;
            }
        }
        int[] tmp = {left, right};
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                if (mid - 1 < 0 || nums[mid - 1] < nums[mid]) {
                    ret[0] = mid;
                    break;
                } else {
                    right = mid - 1;
                }
            } else {
                left = mid + 1;
            }
        }
        left = tmp[0];
        right = tmp[1];
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                if (mid + 1 >= nums.length || nums[mid] < nums[mid + 1]) {
                    ret[1] = mid;
                    break;
                } else {
                    left = mid + 1;
                }
            } else {
                left = mid + 1;
            }
        }
        return ret;
    }
}
