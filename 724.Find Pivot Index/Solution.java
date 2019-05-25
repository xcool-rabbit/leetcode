//724. 寻找数组的中心索引
/*
  一开始还以为只有加法，所以想用两头逼近的方法找到中心索引，后来发现有加有减，所以行不通。
  就换成了呆呆的从左往右遍历，然后对于每一个点都计算左边的sum和右边的sum。
  可是不幸的是（也许是万幸），这种方法跑到738/741用例的时候，LTE了。
*/
class Solution {
    public int pivotIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int leftSum = sumPart(nums, 0, i);
            int rightSum = sumPart(nums, i + 1, nums.length);
            if (leftSum == rightSum) {
                return i;
            }
        }
        return -1;
    }

    private int sumPart(int[] nums, int start, int end) {
        if (!(0 <= start && start < nums.length)) {
            return 0;
        }
        if (!(0 < end && end <= nums.length)) {
            return 0;
        }
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += nums[i];
        }
        return sum;
    }
}
/*
  后来痛定思痛，开始优化。
  其实很容易发现问题在哪里。就是每一次遍历，都会重新计算左右两边的sum值，造成了大量的浪费。
  我们完全可以利用上一次遍历的得出的sum值，进行修改，直接得出新的sum值。
  执行用时：7 ms 已经战胜 73.47 % 的 java 提交记录
*/
class Solution {
    public int pivotIndex(int[] nums) {
        int leftSum = 0;
        int rightSum = sumPart(nums, 1, nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (leftSum == rightSum) {
                return i;
            }
            leftSum += nums[i];
            if (i + 1 < nums.length) {
                rightSum -= nums[i + 1];
            }
        }
        return -1;
    }

    private int sumPart(int[] nums, int start, int end) {
        if (!(0 <= start && start < nums.length)) {
            return 0;
        }
        if (!(0 < end && end <= nums.length)) {
            return 0;
        }
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += nums[i];
        }
        return sum;
    }
}
