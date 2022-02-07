// 154. 寻找旋转排序数组中的最小值 II
/*
  这个方法实在是圆不下去了，对于这道题，根本不管用
  这个方法找的二分的参考，不够简洁
  再加上数列可以有等于的条件后，实在难以应对，例外情况太多，没法修补
  但是费了不少心思，留念
  WA
*/
class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        int offset = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (mid + 1 < nums.length && nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            } else if (mid == nums.length - 1) {
                return nums[0 + offset];
            }
            if (nums[left] < nums[mid]) {
                left = mid + 1;
            } else if (nums[left] > nums[mid]) {
                right = mid - 1;
            } else {
                if (nums[left] < nums[right]) { // 2 2 2 3 4
                    left = mid + 1;
                } else if (nums[left] == nums[right]) { // 2 x 2 x 2
                    left++;
                    offset++;
                } else { // 2 2 2 2 1
                    left = mid + 1;

                }
            }
        }
        return nums[0];
    }
}
/*
  在复盘153题的时候，有了感悟，简化了判定
  瞬间这个题就好写了
  自己想出来的hard题！
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left < right) {
            while (left < right && nums[left] == nums[right]) {
                left++;
            }
            if (left >= right) {
                break;
            }
            mid = left + (right - left) / 2;
            if (nums[left] < nums[right]) {
                return nums[left];
            }
            if (nums[left] <= nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}
/*
  终极优化完153之后，顺便把154也优化一下
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left < right) {
            while (left < right && nums[left] == nums[right]) {
                left++;
            }
            if (left >= right) {
                break;
            }
            mid = left + (right - left) / 2;
            if (nums[right] < nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}