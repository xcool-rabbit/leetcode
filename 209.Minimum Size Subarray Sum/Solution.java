//209. 长度最小的子数组
/*
  这种一开始没什么想法的题，我习惯用笨方法做一遍然后逐渐的寻找优化
  执行用时：74 ms 已经战胜 19.24 % 的 java 提交记录
*/
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int result = nums.length + 1;
        for (int i = 0; i < nums.length; i++) {
            int j = i;
            int sum = 0;
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
/*
  那么上一种笨方法的问题出在了哪里呢？
  每次循环都要计算sum，其实只需要根据上一回循环的结果做一定的增减即可
  这样基本上能从n ^ 2变成n
  执行用时：2 ms 已经战胜 94.58 % 的 java 提交记录
*/
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
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
