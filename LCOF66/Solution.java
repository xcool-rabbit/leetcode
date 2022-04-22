// 剑指 Offer 66. 构建乘积数组
/*
  思路还是那个思路
  就是对0做了特殊处理
  执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int[] constructArr(int[] a) {
        int total = 1;
        int zeroCount = 0;
        for (int n : a) {
            total *= n;
            if (n == 0) {
                zeroCount++;
            }
        }
        int[] ret = new int[a.length];
        for (int i = 0; i < ret.length; i++) {
            if (a[i] != 0) {
                ret[i] = total / a[i];
            } else {
                if (zeroCount > 1) {
                    ret[i] = 0;
                } else {
                    ret[i] = mul(a, i);
                }
            }
        }
        return ret;
    }

    public int mul(int[] a, int index) {
        int ret = 1;
        for (int i = 0; i < a.length; i++) {
            if (i != index) {
                ret *= a[i];
            }
        }
        return ret;
    }
}
