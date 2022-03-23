// 剑指 Offer 03. 数组中重复的数字
/*
  开个布尔数组记录就可以啦
  执行用时：1 ms, 在所有 Java 提交中击败了78.30%的用户
*/
class Solution {
    public int findRepeatNumber(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        for (int n : nums) {
            if (!visited[n]) {
                visited[n] = true;
            } else {
                return n;
            }
        }
        return -1;
    }
}
