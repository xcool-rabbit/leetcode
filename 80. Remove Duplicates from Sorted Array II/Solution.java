// 80. 删除排序数组中的重复项 II
/*
  一气呵成十分惊讶
  今天进入了一个新的leetbook，叫数组算法，里面很多题都做过
  并且我还发现，同一个考点会出现在不同的题里面
  这种删除的题，还要求空间，基本上就是在自己这个数组里面玩儿
  需要用一些变量来记录，一个指针遍历，另一个指针指向待替换的位置
  1ms,在所有Java提交中击败了81.32%的用户
*/
class Solution {
    public int removeDuplicates(int[] nums) {
        int repeatNum = Integer.MIN_VALUE;
        int repeatTimes = 0;
        int trueEnd = 0;
        for (int i = 0; i < nums.length; i++) {
            if (repeatNum != nums[i]) {
                repeatNum = nums[i];
                repeatTimes = 1;
                nums[trueEnd] = nums[i];
                trueEnd++;
            } else {
                repeatTimes++;
                if (repeatTimes > 2) {
                    continue;
                } else {
                    nums[trueEnd] = nums[i];
                    trueEnd++;
                }
            }
        }
        return trueEnd;
    }
}
