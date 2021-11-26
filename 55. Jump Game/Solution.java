// 55. 跳跃游戏
/*
  双层循环，可以点亮所有能达到的位置
  优点在于好写，可以快速验证思路
  缺点在于遍历的时候存在大量的重复
  执行用时：483 ms, 在所有 Java 提交中击败了7.68%的用户
*/
class Solution {
    public boolean canJump(int[] nums) {
        boolean[] arrive = new boolean[nums.length];
        arrive[0] = true;
        for (int i = 0; i < nums.length; i++) {
            if (!arrive[i]) {
                return false;
            }
            int steps = nums[i];
            for (int j = 1; j <= steps && i + j < arrive.length; j++) {
                arrive[i + j] = true;
            }
        }
        return true;
    }
}
/*
  a
  执行用时：4 ms, 在所有 Java 提交中击败了18.42%的用户
*/
class Solution {
    public boolean canJump(int[] nums) {
        int canArrive = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (canArrive > i) {
                    continue;
                } else {
                    return i == nums.length - 1 ? true : false;
                }
            } else {
                canArrive = canArrive > i + nums[i] ? canArrive : i + nums[i];
            }
        }
        return true;
    }
}
/*
  a
  执行用时：2 ms, 在所有 Java 提交中击败了90.59%的用户
*/
class Solution {
    public boolean canJump(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                boolean canArrive = false;
                for (int j = 1; j <= i; j++) {
                    if (nums[i - j] + i -j > i) {
                        canArrive = true;
                        break;
                    }
                }
                if (!canArrive) {
                    return i == nums.length - 1 ? true : false;
                }
            }
        }
        return true;
    }
}
