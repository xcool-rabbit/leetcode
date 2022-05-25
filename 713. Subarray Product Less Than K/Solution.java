// 713. 乘积小于 K 的子数组
/*
  同LCOFII-9
  执行用时：5 ms, 在所有 Java 提交中击败了38.44%的用户
*/
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0 || k == 1) {
            return 0;
        }
        int ret = 0;
        int product = 1;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0) {
                product /= nums[i - 1];
            } else {
                product = nums[0];
            }
            // j = Math.max(i, j);
            // while (j > i && product >= k) {
            //     product /= nums[j--];
            // }
            while (j + 1 < nums.length && product < k) {
                product *= nums[++j];
            }
            // System.out.println("i: " + i);
            // System.out.println("j: " + j);
            if (product < k) {
                ret++;
            }
            ret += (j - i);
            // System.out.println("product: " + product);
            // System.out.println(j - i);
        }
        return ret;
    }
}
