//268. 缺失数字
/*
  先无视那个进阶要求，打一个表然后存在的数字对应的位置就true
  最后再遍历一遍，没有的就是missing number
  线性时间但是会占用n的空间
  执行用时：1 ms 已经战胜 97.60 % 的 java 提交记录
*/
class Solution {
    public int missingNumber(int[] nums) {
        boolean[] chart = new boolean[nums.length + 1];
        for (int n : nums) {
            chart[n] = true;
        }
        for (int i = 0; i < chart.length; i++) {
            if (!chart[i]) {
                return i;
            }
        }
        return 0;
    }
}
/*
  进阶要求占用常数空间，我一开始着手在查找的效率上进行提升
  后来一想，数组的随机存储本来就是常数级别的，没有查找开销
  那如何在存储上节省空间呢？哈希？（误
  不会！
  查答案：
  我靠我怎么是个呆子呢
  1. 异或，循环nums，异或index和nums[index]。利用的性质就是异或运算的逆元是它自己。那么异或的结果就是缺少的那个数
  2. 求和，因为已知如果不缺的话，数组的和是多少。那么最后加起来就知道差谁了
  个人建议当然是第一种，因为不可能超过数据范围。第二种求和可能具有这样的风险。
  执行结果没有跟前一种解法拉开差距，应该是测试用例不够大
  从程序执行的角度来讲，这种解法只遍历一遍，而上一种解法会遍历不到两遍
  执行用时：1 ms 已经战胜 97.60 % 的 java 提交记录
*/
class Solution {
    public int missingNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result ^= i;
            result ^= nums[i];
        }
        return result ^ nums.length;
    }
}
