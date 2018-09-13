//283. 移动零
/*
  一开始两种想法:
  第一种是从后往前开始遍历，将0一步一步往右挪；（太蠢啦！）
  第二种是从前往后挪，遇到另一个0后，将两个0合并起来，一起挪动；
  后来看到题目要求是尽量减少操作次数，才意识到，0不0的，无所谓，把非0的部分挪到该存在的位置，最后在数组末尾补0就行了。
  执行用时：2 ms 已经战胜 99.60 % 的 java 提交记录
*/
class Solution {
    public void moveZeroes(int[] nums) {
        int head = 0;
        if (nums.length <= 1)
            return;
        while (head < nums.length && nums[head] != 0)
            head++;
        int tail = head + 1;
        while (tail < nums.length) {
            if (nums[tail] != 0)
                nums[head++] = nums[tail];
            tail++;
        }
        while (head < nums.length)
            nums[head++] = 0;
    }
}
