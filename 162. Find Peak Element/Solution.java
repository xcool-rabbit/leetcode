// 162. 寻找峰值
/*
  看似荒唐的题，无序查找怎么还能在小于O(n)的情况下实现？
  这里面的条件很重要，相邻两数不相等，数组左端和右端视为负无穷
  如果我们朝着O(logn)去想的话，能想到是二分，但是具体要怎么实现呢
  首先，整个数组已知的是左右两端都是最低的，中间的值高，必然存在一个峰值
  所以我们在二分的时候，也要维持这个标准，两端低
  接下来我们研究mid怎么才能决定是往左还是往右
  mid左右两边的数字一共就四种形状，分别处理就ok
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid = left + (right - left) / 2;
        while (left <= right) {
            boolean leftSmall = mid - 1 < 0 ? true : nums[mid - 1] < nums[mid];
            boolean rightSmall = mid + 1 < nums.length ? nums[mid + 1] < nums[mid] : true;
            if (leftSmall && !rightSmall) {
                left = mid + 1;
            } else if (leftSmall && rightSmall) {
                return mid;
            } else if (!leftSmall && !rightSmall) {
                left = mid + 1;
            } else if (!leftSmall && rightSmall){
                right = mid - 1;
            } else {
                System.out.println("impossible");
            }
            mid = left + (right - left) / 2;
        }
        return mid;
    }
}
