// 166. 分数到小数
/*
  我这个方法已经搞不赢了
  首先我想到了直接用现成的除法去算，并且用现成的String.valueOf转成字符串
  小问题不谈，最大的问题是，无限循环小数要怎么处理
  这时我想到，无限循环小数乘以除数，应该是一堆9
  我就根据这一点来判断
  结果，还有一种情况没有考虑到，1 / 6
  这种情况是0.9996，我的方法并不能识别这种无限循环小数
  后来我针对这种情况，认为，从996变到9996，就是无限循环
  真是防不胜防
  1 / 19，它的循环节太长了，用int算已经爆了
  但是理论上又不能用double算，因为不一定相等
  经过实践，发现瓶颈在double的显示位数上
  总之就是，没有办法借助现成的除法
  需要手动实现一个模拟手工除法的算法
*/
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator % denominator == 0) {
            return String.valueOf(numerator / denominator);
        }
        String s = String.valueOf((double)numerator / denominator);
        if (s.length() < 18) {
            return s;
        }
        if (numerator != 1) {
            s = String.valueOf(1.0 / denominator);
        }
        double n = 0;
        double scale = 1.0;
        double last = 0;
        for (int i = 2; i < s.length(); i++) {
            scale /= 10.0;
            n += (s.charAt(i) - '0') * scale;
            if (n * denominator == 1) {
                return s;
            }
            if (n * denominator == 1 - scale) {
                s = String.valueOf((double)numerator / denominator);
                return "0.(" + s.substring(2, i + 1) + ")";
            }
            if (n * denominator == last / 10 + 0.9) {
                s = String.valueOf((double)numerator / denominator);
                return "0." + s.substring(2, i) + "(" + s.charAt(i) + ")";
            }
            last = n * denominator;
            System.out.println(last);
        }
        return "Sorry, I can not";
    }
}
/*
  模拟手工除法，就不用怕超长循环
  实现起来并不难
  反而麻烦的地方在边界条件
  首先有一次这种处理-2 ^ 31的经验，就想着递归减轻判断难度
  但是问题在于，返回值是String，没有办法在递归上下文章
  况且，如果除数是-2 ^ 31，做手工除法的时候，可是要算几倍于2 ^ 31的数，这显然是int做不到的
  那么，，，题目并没有限制我使用long！！！
  回头看了一下前一道自己实现除法的题，那里面有限制
  嗨呀我直接思维惯性了
  转成long，很多问题都迎刃而解
  执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        long lNumerator = numerator;
        long lDenominator = denominator;
        boolean isNegative = lNumerator < 0 ^ lDenominator < 0;
        lNumerator = Math.abs(lNumerator);
        lDenominator = Math.abs(lDenominator);
        StringBuilder ret = new StringBuilder(isNegative ? "-" : "");
        ret.append(lNumerator / lDenominator == 0 ? "0" : String.valueOf(lNumerator / lDenominator));
        lNumerator %= lDenominator;
        if (lNumerator == 0) {
            return ret.toString();
        }
        ret.append('.');
        Map<Long, Integer> map = new HashMap<>();
        int index = ret.length();
        while (lNumerator != 0) {
            lNumerator *= 10;
            if (map.get(lNumerator) == null) {
                map.put(lNumerator, index);
            } else {
                ret.insert(map.get(lNumerator), "(");
                ret.append(")");
                break;
            }
            ret.append(lNumerator / lDenominator);
            index++;
            lNumerator %= lDenominator;
        }
        return ret.toString();
    }
}
