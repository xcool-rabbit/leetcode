// 152. 乘积最大子数组
/*
  由于数据的特点，数越多，乘积越大
  阻碍它成为最大的就是负号
  如果所有数的乘积是正数，那么它就是最大的
  如果是负数，只需要把最左边或者最右边的一个负数剥离，至于是选左还是选右，看哪边绝对值小就剥离哪边
  如果是0，就把这个问题以0位分界线，分成两部分
  答案用的dp，我沉默了
  简单问题复杂化？我用策略能求出来的题，还用dp？
  尤其是这个对0的处理，堪称绝了
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int ret = 1;
        for (int n : nums) {
            ret *= n;
        }
        if (ret > 0) {
            return ret;
        } else if (ret < 0) {
            int left = 1;
            for (int i = 0; i < nums.length; i++) {
                left *= nums[i];
                if (left < 0) {
                    break;
                }
            }
            int right = 1;
            for (int i = nums.length - 1; i >= 0; i--) {
                right *= nums[i];
                if (right < 0) {
                    break;
                }
            }
            return ret / Math.max(left, right);
        } else {
            int zeroIndex = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) {
                    zeroIndex = i;
                    break;
                }
            }
            int[] left = Arrays.copyOfRange(nums, 0, zeroIndex);
            int[] right = Arrays.copyOfRange(nums, zeroIndex + 1, nums.length);
            return Math.max(0, Math.max(maxProduct(left), maxProduct(right)));
        }
    }
}
