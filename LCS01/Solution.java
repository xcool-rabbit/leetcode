// LCS 01. 下载插件
/*
  升级是收益最大的选择
  升级前需要4次完成的任务
  花一次升级，升级之后只需要两次，总共需要三次
  还是比不升级要更优
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int leastMinutes(int n) {
         return n == 1 ? 1 : (int)(Math.log(n - 1) / Math.log(2)) + 2;
    }
}
