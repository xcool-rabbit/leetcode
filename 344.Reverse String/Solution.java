//344. 反转字符串
/*
  将字符串转化为字符数组，然后对字符数组进行反转，再返回字符串
  执行用时：5 ms 已经战胜 29.77 % 的 java 提交记录
  mgj不知道那些记录为什么这么快，网上找了找，没有什么快的
*/
class Solution {
    public String reverseString(String s) {
        char[] a = s.toCharArray();
        char tmp = 0;
        for (int i = 0; i < a.length / 2; i++) {
            tmp = a[a.length - i - 1];
            a[a.length - i - 1] = a[i];
            a[i] = tmp;
        }
        return new String(a);
    }
}
