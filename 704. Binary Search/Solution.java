// 704. 二分查找
/*
  非常粗制滥造的一个递归二分查找
  递归写起来很容易也很易懂
  为什么说这个粗制滥造呢，因为还可以优化，比如说快速返回
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int[] nums;
    public int target;
    public int answer = -1;
    public int search(int[] nums, int target) {
        this.nums = nums;
        this.target = target;
        binarySearch(0, nums.length - 1);
        return answer;
    }

    public void binarySearch(int start, int end) {
        if (start > end) {
            return;
        }
        int mid = (start + end) / 2;
        if (nums[mid] > target) {
            binarySearch(start, mid - 1);
        } else if (nums[mid] < target) {
            binarySearch(mid + 1, end);
        } else {
            answer = mid;
        }
    }
}
/*
  从开销来看，肯定是循环更加的节省，后面慢慢补吧
*/