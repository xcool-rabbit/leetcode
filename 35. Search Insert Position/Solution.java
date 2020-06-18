//35. 搜索插入位置
/*
  有序数组，查找插入的位置，还没有重复元素
  这就是给二分查找创造的条件
  然而我还是偷懒使用了顺序查找，结果还是100%
  二分查找肯定是要快的
  执行用时：0 ms 已经战胜 100.00 % 的 java 提交记录
*/
class Solution {
    public int searchInsert(int[] nums, int target) {
        int index = 0;
        while (index < nums.length && nums[index] < target) {
            index++;
        }
        return index;
    }
}
