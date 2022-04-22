// 剑指 Offer 39. 数组中出现次数超过一半的数字
/*
  一换一的思路，参考主站169
  执行用时：1 ms, 在所有 Java 提交中击败了99.97%的用户
*/
class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        int ret = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                ret = nums[i];
                count++;
            } else {
                if (ret == nums[i]) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        return ret;
    }
}
