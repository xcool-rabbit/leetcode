// 1109. 航班预订统计
/*
  题意很好理解，首先想到的是暴力法，给对应的地方加上就可以了
  执行用时：1424 ms, 在所有 Java 提交中击败了19%的用户
*/
class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] answer = new int[n];
        for (int[] booking : bookings) {
            for (int i = booking[0] - 1; i < booking[1]; i++) {
                answer[i] += booking[2];
            }
        }
        return answer;
    }
}
/*
  emmm，我一点一点老老实实的加起来，有什么问题吗？
  还能有比这个更快的方式？
  你至少要都加起来才能得到答案的呀，这个应该是不可避免的吧
  然而，在我看完题解的一句话之后，我就悟了
  数组可以有第二种表示的方式，名叫差分数组，实际上呢，就是用delta来表示数组，记录相邻元素之间的差
  这种表示方式对于这种批量的段操作的问题非常有效，只需要在段的开头加上，在段的结尾减去，就能表示这一段的加减
  最后再遍历一遍delta数组就能恢复原数组
  执行用时：2 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] delta = new int[n];
        for (int[] booking : bookings) {
            int start = booking[0] - 1;
            int end = booking[1];
            delta[start] += booking[2];
            if (end < delta.length) {
                delta[end] -= booking[2];
            }
        }
        int[] result = new int[n];
        result[0] = delta[0];
        for (int i = 1; i < delta.length; i++) {
            result[i] = result[i - 1] + delta[i];
        }
        return result;
    }
}
