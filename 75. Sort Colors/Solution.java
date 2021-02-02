// 75. 颜色分类
/*
  这个题，用嘴讲，十分的简单，0丢到前面去，2丢到后面去
  实际写起来还是问题挺多的
  并不是单纯的将0和前指针交换，将2和后指针交换就可以的
  存在一个问题就是，2和后指针换，换到的可能是个0，还得继续换
  全部换完了才能+1
  最终写了一个讨巧的方法，又简洁，又对
  0ms,在所有Java提交中击败了100.00%的用户
*/
class Solution {
    public void sortColors(int[] nums) {
        int front = 0;
        int end = nums.length - 1;
        int swap;
        for (int i = 0; i < end + 1;) {
            if (nums[i] == 2) {
                swap = nums[i];
                nums[i] = nums[end];
                nums[end] = swap;
                end--;
            }
            if (nums[i] == 0) {
                swap = nums[i];
                nums[i] = nums[front];
                nums[front] = swap;
                front++;
                i++;
            }
            if (i < end + 1 && nums[i] == 1) {
                i++;
            }
        }
    }
}
/*
  重新捋清了一下，写了一个凡人版的
  0ms,在所有Java提交中击败了100.00%的用户
*/
class Solution {
    public void sortColors(int[] nums) {
        int front = 0;
        int end = nums.length - 1;
        int swap;
        for (int i = 0; i < end + 1;) {
            switch (nums[i]) {
                case 0:
                    swap = nums[i];
                    nums[i] = nums[front];
                    nums[front] = swap;
                    front++;
                    i++;
                    break;
                case 1:
                    i++;
                    break;
                case 2:
                    swap = nums[i];
                    nums[i] = nums[end];
                    nums[end] = swap;
                    end--;
                    if (nums[i] == 0) {
                        swap = nums[i];
                        nums[i] = nums[front];
                        nums[front] = swap;
                        front++;
                        i++;
                    }
            }
        }
    }
}