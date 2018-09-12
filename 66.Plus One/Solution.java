//66. 加一
/*
  题目描述极其混淆，就是一个数，把每一位分开，装到一个数组里。
  输入就是这个数组，输出这个数组表示的数+1后的结果。
  非常简单，注意两种情况：
  1.进位
  2.最终结果比原来的数多了一位，这样需要重新声明一个数组。由于只是+1，所以涉及到这种情况，只有999 + 1 = 1000的这种情况。返回的数组只需要第一位为1即可。
  不过很意外，这种简单的题，做出来效率竟然如此的低。
  执行用时：1 ms 已经战胜 14.90 % 的 java 提交记录
*/
class Solution {
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            }
            else {
                digits[i]++;
                break;
            }
        }
        if (digits[0] == 0) {
            int[] res = new int[digits.length + 1];
            res[0] = 1;
            return res;
        }
        return digits;
    }
}

/*
  ？？？为什么，这么简单的一个程序，我却能跟别人差这么多
  我一开始是以为别人设置了一个进位标志，导致他们的程序比我快，但我仔细想了一下，感觉没区别啊。
  结果经过我深入而仔细的研究，发现，是return的位置问题。
  因为大多数的测试样例都不会涉及到进位，或者很少的进位，更别提数的位数多一位了，所以要让return提前执行。
  执行用时：0 ms 已经战胜 100.00 % 的 java 提交记录
*/
class Solution {
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if(digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        if (digits[0] == 0) {
            int[] res = new int[digits.length + 1];
            res[0] = 1;
            return res;
        }
        return digits;
    }
}
