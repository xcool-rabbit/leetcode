//88. 合并两个有序数组
/*
  属实没什么好说的，重新开一个数组，然后同时遍历两个src数组，谁小就往里填
  到最后谁剩下就全填进去，最后把合并好了的数组copy给nums1就可以了
  这里要注意直接nums1 = nums是不可以的哦，只是改变了传过来的引用的值，并没有改变nums1数组本身哦
  但是就在我开始写思路的时候，我又想到了一个不需要另外开数组的方法
  同时遍历两个数组，有nums2比nums1小的时候，就把nums2的这个值跟nums1相应位置swap一下
  遍历到nums1末尾之后，把nums2里的值依次copy过去就可以了
  执行用时：0 ms 已经战胜 100.00 % 的 java 提交记录
*/
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] nums = new int[m + n];
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                nums[i + j] = nums1[i];
                i++;
            } else {
                nums[i + j] = nums2[j];
                j++;
            }
        }
        if (i == m) {
            while (j < n) {
                nums[i + j] = nums2[j];
                j++;
            }
        } else if (j == n) {
            while (i < m) {
                nums[i + j] = nums1[i];
                i++;
            }
        }
        System.arraycopy(nums, 0, nums1, 0, nums.length);
    }
}
