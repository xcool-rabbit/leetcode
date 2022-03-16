// 134. 加油站
/*
  算法不够，case来凑
  首先，leftSum不能小于0，那样的话，就是不可能到达
  其次，如果这次失败了，说明，咱开局不够好，后面得找一个比现在这个开局更强的
  执行用时：2 ms, 在所有 Java 提交中击败了76.16%的用户
*/
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int leftSum = 0;
        int[] left = new int[gas.length];
        for (int i = 0; i < gas.length; i++) {
            left[i] = gas[i] - cost[i];
            leftSum += left[i];
        }
        if (leftSum < 0) {
            return -1;
        }
        for (int i = 0; i < left.length; i++) {
            int cur = 0;
            for (int count = 0; count < left.length; count++) {
                int j = (i + count) % left.length;
                cur += left[j];
                if (cur < 0) {
                    break;
                }
            }
            if (cur >= 0) {
                return i;
            }
            while (left[i] >= left[i + 1]) {
                i++;
            }
        }
        return -1;
    }
}
