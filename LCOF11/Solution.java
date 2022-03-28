// 剑指 Offer 11. 旋转数组的最小数字
/*
  跟主站154题相同，不过那个题难度被标位了hard
  因为真的很难通过，因为可能有重复元素
  重复元素非常影响对于二分的判断
  如果我们的二分分到了一堆连续的重复元素上
  根据我的判断逻辑，会一直往左缩，如果target在右边，就找不到了
  所以要有一个过滤重复元素的操作
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int minArray(int[] numbers) {
        int[] nums = numbers;
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
