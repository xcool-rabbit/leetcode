// 5773. 插入后的最大值
/*
  搞清楚策略，这个题首先分为正数和负数两种，区别就是一个要最小一个要最大，但是思想是一模一样的
  这个数，插在不同的位置，有什么区别呢
  插入位置之前的数 * 10，插入位置之后的数不变
  所以，要让它最大，插入位置之前的数，都应该比待插入的数大（注意没有等于），这样才能保证插入后最大
  注意不要包含等于的情况，等于其实就是这个判断没有意义，应该继续看后面的，而不是等于的位置也插
  执行用时：14 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public String maxValue(String n, int x) {
        if (n.charAt(0) == '-') {
            for (int i = 1; i < n.length(); i++) {
                if (n.charAt(i) - '0' > x) {
                    return n.substring(0, i) + String.valueOf(x) + n.substring(i);
                }
            };
        } else {
            for (int i = 0; i < n.length(); i++) {
                if (n.charAt(i) - '0' < x) {
                    return n.substring(0, i) + String.valueOf(x) + n.substring(i);
                }
            }
        }
        return n + String.valueOf(x);
    }
}
