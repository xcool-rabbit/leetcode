//189. 旋转数组
/*
  第一种是我自己想到的，先做好向右平移1位的功能，再根据k的值，将该功能执行k次。
  但这样做遇到了一点问题，超时了。
  后来进行了几方面的优化，对k的值取模，对k较大的值，改变为向左移动互补的距离。
  最终通过。
*/
class Solution {
    public void rotate(int[] nums, int k) {
        if (k % nums.length <= nums.length / 2) {
            for (int i = 0; i < k % nums.length; i++) {
                int tmp1 = 0;
                int tmp2 = 0;
                int j = 0;
                while (j < nums.length) {
                    tmp2 = nums[j];
                    nums[j] = tmp1;
                    tmp1 = tmp2;
                    j++;
                }
                nums[0] = tmp1;
            }
        }
        else {
            for (int i = 0; i < nums.length - k % nums.length; i++) {
                int tmp1 = 0;
                int tmp2 = 0;
                int j = nums.length - 1;
                while (j >= 0) {
                    tmp2 = nums[j];
                    nums[j] = tmp1;
                    tmp1 = tmp2;
                    j--;
                }
                nums[nums.length - 1] = tmp1;
            }
        }
    }
}
/*
  这道题要求至少想出三种方案，在空间复杂度O(1)的前提下，我只想到一种，另外一种如下：
  交换。
  比如一个长度为7的数组，向右平移两位，就是将最后两个元素挪到最前面，将前五个元素挪到后面。
  那么就可以让前五个元素先做翻转，再让后两个元素做翻转，最终将整个数组进行翻转，即可得到最终结果。
  超级快。
*/
class Solution {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        swap(nums, 0, nums.length - k - 1);
        swap(nums, nums.length - k, nums.length - 1);
        swap(nums, 0, nums.length - 1);
    }
    
    public void swap(int[] nums, int start, int end) {
        int tmp = 0;
        while (start < end) {
            tmp = nums[end];
            nums[end] = nums[start];
            nums[start] = tmp;
            start++;
            end--;
        }
    }
}
/*
  第三种做法：没找出来
*/
