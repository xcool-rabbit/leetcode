//561. 数组拆分 I
/*
  要想两两组队之后，min最大，也就是要两个数挨在一起，这样较大的数才不会很亏。所以先排序，然后遍历一遍，两个一组，取较小的求和。
  在排序的方法上，由于题目给定了数据范围，所以可以使用桶排序来提速。知道就好，没有必要再写。
  执行用时：33 ms 已经战胜 80.00 % 的 java 提交记录
*/
class Solution {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        return sum;
    }
}
