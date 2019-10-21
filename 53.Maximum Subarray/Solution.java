//53. 最大子序和
/*
  这道题看起来很简单，但是我一开始脑子昏了，怎么做的缺一点，无奈之下看了答案
  思路跟我想的是一样的，但是细节处理是真的好，重点就是for循环里面的语句
  简洁的代码，就是看着舒服
  执行用时：1 ms 已经战胜 99.97 % 的 java 提交记录
*/
class Solution {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum <= 0) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }
}
/*
  第一次遇到这道题被放在了动态规划里面，所以用动态规划的思想再做一遍
  设sum[i]为以第i个元素结尾且和最大的连续子数组。
  假设对于元素i，所有以它前面的元素结尾的子数组的长度都已经求得，
  那么以第i个元素结尾且和最大的连续子数组实际上，
  要么是以第i-1个元素结尾且和最大的连续子数组加上这个元素，要么是只包含第i个元素，
  即sum[i] = max(sum[i - 1] + a[i], a[i])。可以通过判断sum[i - 1] + a[i]是否大于a[i]来做选择
  写代码的时候发现，原来上面那种大体是一样的，能改进的地方在于打表可以不占用那么多的空间，只需要记录sum[i - 1]
  执行用时：1 ms 已经战胜 99.97 % 的 java 提交记录
*/
class Solution {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum <= 0) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }
}
