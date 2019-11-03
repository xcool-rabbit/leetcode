//747. 至少是其他数字两倍的最大数
/*
  只要是第二大数字的两倍即可
  本来想把最大和第二大放到一个循环里面去做，但好像总有一些细节上的问题
  于是就把它们拆成了两次循环去做这样一件事情O(n)变成了O(2n)
  不过从复杂度的角度来考虑并不在意n前面的系数，四舍五入没有变慢哈哈
  执行用时：1 ms 已经战胜 99.82 % 的 java 提交记录
*/
class Solution {
    public int dominantIndex(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        int max = nums[0];
        int maxIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }
        }
        int smallerMax = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (i != maxIndex && nums[i] > smallerMax) {
                smallerMax = nums[i];
            }
        }
        return max >= smallerMax * 2 ? maxIndex : -1;
    }
}
