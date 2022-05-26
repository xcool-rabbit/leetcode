// 剑指 Offer II 006. 排序数组中两个数字之和
/*
  同主站167，但是看了一下当时写的代码，是真的脑子也不清楚，写的也垃圾
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (true) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            } 
        }
    }
}
