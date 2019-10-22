//198. 打家劫舍
/*
  又是一道动态规划问题，但是我明明知道要运用那个思想，可是就是想不对
  我们在累加时，只有两种选择：
  1. 如果选择了抢劫上一个屋子，那么就不能抢劫当前的屋子，所以最大收益就是抢劫上一个屋子的收益
  2. 如果选择抢劫当前屋子，就不能抢劫上一个屋子，所以最大收益是到上上个屋子为止的最大收益，加上当前屋子里有的钱
  执行用时：0 ms 已经战胜 100.00 % 的 java 提交记录
*/
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }
        int[] total = new int[nums.length];
        total[0] = nums[0];
        total[1] = nums[0] > nums[1] ? nums[0] : nums[1];
        for (int i = 2; i < nums.length; i++) {
            total[i] = total[i - 1] > total[i - 2] + nums[i] ? total[i - 1] : total[i - 2] + nums[i];
        }
        return total[total.length - 1];
    }
}
