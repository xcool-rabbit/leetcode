// 169. 多数元素
/*
  我的这个做法很有意思，可以叫做极限一换一
  因为要求的数，是个数最多的，所以，当遇到敌军的时候，就跟它一换一，最后剩下来的，就是所求的
  那你可能会问了，如果是敌军换敌军呢？
  那岂不更好？敌军内讧，所求的更容易赢了
  执行用时：1 ms, 在所有 Java 提交中击败了99.92%的用户
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
