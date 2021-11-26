// 1909. 删除一个元素使数组严格递增
/*
  这是一道逻辑很累的题
  删除一个元素后严格递增，首先这个数组应该是由两段严格递增的序列组成的，否则怎么删也删不成
  现在开始设想两段线段，第一段的尾和第二段的头是我们讨论的重点
  删除的元素，要么是低谷，要么是低谷前面一个位置
  最后再讨论一下两个边界情况，就ok了
  我觉得这是最清晰的思路了
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public boolean canBeIncreasing(int[] nums) {
        int index = -1;
        for (int i = 1; i < nums.length; i++) {
            if (!(nums[i] > nums[i - 1])) {
                if (index != -1) {
                    return false;
                }
                index = i;
            }
        }
        if (index == -1) {
            return true;
        } else {
            if (index - 2 < 0 || index == nums.length - 1) {
                return true;
            }
            if (!(nums[index + 1] > nums[index - 1] || nums[index] > nums[index - 2])) {
                return false;
            }
        }
        return true;
    }
}