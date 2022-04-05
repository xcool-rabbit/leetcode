// 剑指 Offer 57. 和为s的两个数字
/*
  这不就是两数之和？
  区别就是这个题的数组是递增的，我确实没有利用上这个条件
  递增就说明可以二分，可是我用set查找，明明可以O(1)，比递增要快的啊
  执行用时：23 ms, 在所有 Java 提交中击败了15.68%的用户
*/
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (int n : nums) {
            if (set.contains(target - n)) {
                return new int[]{n, target - n};
            }
            set.add(n);
        }
        return null;
    }
}
/*
  啊好蠢啊，忘了从两头夹了
  三数之和里面用到的小技巧，排序之后，两数之和问题可以变成O(n)
  就在我看到这个解法的时候，我内心第一次萌生出了这个疑问：
  从两头夹，不会矫枉过正吗？
  比如发现和小，左边往右，然后发现大了，右边往左，正好错过了正解
  后来想了一下，不会的
  这个移动，只有四种情况，左一右一，左一右二，左二右一，左二右二
  左一右二是初始状态，根据刚才的描述，左一右二不符合，然后变成左二右一，也不符合，然后左二右二，错过正解
  那么正解只能是左一右一，但是呢，左一右一是比左一右二要小的，一开始就不是同一条录，是不可能错过的
  执行用时：1 ms, 在所有 Java 提交中击败了99.44%的用户
*/
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum > target) {
                right--;
            } else if (sum < target) {
                left++;
            } else {
                return new int[]{nums[left], nums[right]};
            }
        }
        return null;
    }
}
