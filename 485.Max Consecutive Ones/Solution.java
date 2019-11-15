//485. 最大连续1的个数
/*
  这道题被归为了双指针，不是很懂
  明明顺序遍历就可以，我实在想不到这种题不O(n)还能怎么快
  执行用时：2 ms 已经战胜 100.00 % 的 java 提交记录
*/
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int result = 0;
        int count = 0;
        for (int n : nums) {
            if (n == 1) {
                count++;
            } else {
                result = count > result ? count : result;
                count = 0;
            }
        }
        return count > result ? count : result;
    }
}
