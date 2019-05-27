//27. 移除元素
/*
  坐这道题的时候，它被归纳在了双指针分类当中。
  如果以我以前的思维的话，我会遍历，遇到需要删除的元素，就把后面的元素往前挪。
  后来引入了双指针的思想，这道题变得更加清晰明了。
  最近连着做了好几道简单题，很没意思。
  执行用时：1 ms 已经战胜 99.59 % 的 java 提交记录
*/
class Solution {
    public int removeElement(int[] nums, int val) {
        int newIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[newIndex] = nums[i];
                newIndex++;
            }
        }
        return newIndex;
    }
}
