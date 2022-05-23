// 剑指 Offer 43. 1～n 整数中 1 出现的次数
/*
  不过如此
  我这，还有优化余地呢
  递归的时候重复计算了dp
  看了下题解，挺复杂的
  我用的是一个dp数组，表示n为1位数、2位数……的最大答案
  构建这个数组的目的是，减少重复计算
  比如118，首先我们根据dp数组，知道1-99里面有多少个1
  那么接下来只需要处理3位数
  100-118，百位都有一个1，一共19个，很容易得
  然后再递归求18里面有几个1就可以了
  那么针对第一位不是1的情况，比如318
  100-199的要全算上，这一部分就是100的答案
  然后是1-99的部分，从100到299，有两份
  然后再递归一个18
  我就说不太对嘛，应该是LeetCode出bug了，没有用时的排名，就会显示100%
  执行用时：6 ms, 在所有 Java 提交中击败了100.00%的用户
*/
/*
1-9 1
10-99 19
*/
class Solution {
    public int countDigitOne(int n) {
        System.out.println("n: " + n);
        if (n == 0) {
             return 0;
        }
        String s = String.valueOf(n);
        int length = s.length();
        if (length == 1) {
            return 1;
        }
        int[] dp = new int[length - 1];
        dp[0] = 1;
        int tmp = 10;
        for (int i = 1; i < dp.length; i++) {
            if (n / 10 < tmp) {
                break;
            }
            dp[i] = dp[i - 1] * 10 + tmp;
            tmp *= 10;
        }
        // System.out.println(Arrays.toString(dp));
        int ret = dp[length - 2];
        int next = Integer.parseInt(s.substring(1));
        if (s.charAt(0) == '1') {
            ret += (next + 1);
        } else {
            ret *= (s.charAt(0) - '0');
            ret += tmp;
        }
        ret += countDigitOne(next);
        return ret;
    }
}
