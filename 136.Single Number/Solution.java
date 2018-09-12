//136. 只出现一次的数字
/*
  利用异或的特性，相同的数异或为0，所以对数组中所有元素异或，每两个相同的元素异或为0，所以最终若有只出现一次的数字，则结果不为0
  执行用时：1 ms 已经战胜 99.85 % 的 java 提交记录
*/
class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i : nums) {
            res ^= i;
        }
        return res;
    }
}
