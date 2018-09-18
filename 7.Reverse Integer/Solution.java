//7. 反转整数
/*
  这道题正好出现在344题之后，将整数转化为字符串后，套用344题反转字符串的方法即可。
  需要注意的是，Integer.MIN_VALUE的相反数会超int，因为int的数值范围是 [−2^31,  2^31 − 1]。所以可以先把int转化为long再做处理。
  执行用时：32 ms 已经战胜 82.64 % 的 java 提交记录
*/
class Solution {
    public int reverse(int x) {
        long l = x;
        boolean negative = false;
        if (l < 0) {
            negative = true;
            l = -l;
        }
        
        String s = null;
        s = String.valueOf(l);
        
        char[] a = s.toCharArray();
        char tmp = 0;
        for (int i = 0; i < a.length / 2; i++) {
            tmp = a[a.length - i - 1];
            a[a.length - i - 1] = a[i];
            a[i] = tmp;
        }
        int start = 0;
        if (a.length != 1) {
            for (char i : a) {
                if (i == '0')
                    start++;
                else
                    break;
            }
        }
        
        String res = new String(a);
        res = res.substring(start);
        
        long ans = Long.parseLong(res);
        if (negative)
            ans = -ans;
        
        if (ans < Integer.MIN_VALUE || ans > Integer.MAX_VALUE)
            return 0;
        
        return (int)ans;
    }
}
