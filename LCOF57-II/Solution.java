// 剑指 Offer 57 - II. 和为s的连续正数序列
/*
  滑动窗口，O(n)
  时间不够快的原因，我猜测是数组的转换这里
  我都离谱了，题解的所有方法，最快的也跟我一样，所以到底有什么更快的方法呢
  执行用时：3 ms, 在所有 Java 提交中击败了48.67%的用户
*/
class Solution {
    public int[][] findContinuousSequence(int target) {
        List<int[]> list = new ArrayList<>();
        int start = 1;
        int end = 1;
        int curSum = 1;
        while (end <= target) {
            if (curSum < target) {
                end++;
                curSum += end;
            } else if (curSum == target) {
                if (start < end) {
                    list.add(genArr(start, end));
                }
                end++;
                curSum += end;
            } else {
                if (start > end) {
                    break;
                }
                curSum -= start;
                start++;
            }
        }
        return list.toArray(new int[list.size()][]);
    }

    public int[] genArr(int start, int end) {
        int[] ret = new int[end - start + 1];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = start + i;
        }
        return ret;
    }
}
