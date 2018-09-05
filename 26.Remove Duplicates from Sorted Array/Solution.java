//26. 删除排序数组中的重复项
/*
  一开始当成了无序数组来做，十分麻烦，鉴于这么麻烦，我就把代码留下来了
  思路是设置头尾两个指针，从头开始，先确保头指针当前指的数不与前面的所有数相等，若相等，则该数应该与尾指针所指的数对换；若不等，则头指针向后移。
  但在对换的时候要避免尾指针所指的数与头指针之前的数重复，若不重复则可以对换；若重复则将尾指针向前移，但要保证尾指针要在头指针的后面。
*/
class Solution {
    public int removeDuplicates(int[] nums) {
        int head = 1;
        int tail = nums.length - 1;
        int len = 0;
        while (head < tail) {
            boolean equal = false;
            for (int i = 0; i < head; i++) {
                if (nums[head] == nums[i]) {
                    equal = true;
                    for (int j = 0; j <= head && head < tail; j++) {
                        if (nums[tail] == nums[j]) {
                            tail--;
                            j = -1;
                        }
                    }
                    break;
                }
            }
            if (tail > head) {
                if (equal) {
                    int tmp = nums[head];
                    nums[head] = nums[tail];
                    nums[tail] = tmp;
                    tail--;
                }
                head++;
            }
            len = head;
        }
        return len;
    }
}
