// 238. 除自身以外数组的乘积
/*
  再一次被这种方法戏耍！数组题！要求O(n)的，以后可以记着点这个方法
  从左到右遍历，从右到左遍历
  优化就是不用额外空间，可以把right数组省略，没有必要存着，一边推一边算
  执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] ret = new int[nums.length];
        for (int i = 0 ; i < nums.length; i++) {
            if (i == 0) {
                ret[i] = 1;
            } else {
                ret[i] = ret[i - 1] * nums[i - 1];
            }
        }
        int cur = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            ret[i] = ret[i] * cur;
            cur *= nums[i];
        }
        return ret;
    }
}
