// 剑指 Offer II 008. 和大于等于 target 的最短子数组
/*
  同主站209
  慢的原因是遍历的逻辑，我是一定会减，循环用加
  快的那个遍历是加，循环是减，这样写代码量比我的小好多
  我觉得这个在写的时候很难去判断应该以一个怎样的顺序
  所以我觉得这个无所谓
  （又提交了一次，变快了）
  执行用时：1 ms, 在所有 Java 提交中击败了99.95%的用户
*/
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int s = target;
        int result = nums.length + 1;
        int j = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i != 0) {
                sum -= nums[i - 1];
            }
            while (j < nums.length && sum < s) {
                sum += nums[j++];
            }
            if (sum >= s) {
                result = j - i< result ? j - i : result;            
            }
        }
        return result > nums.length ? 0 : result;
    }
}
