//8. 字符串转整数 (atoi)
/*
  首先用trim()将空白符消除，之后转为数组。看第一位是否为负号，做个标记。
  最烦人的是前面放一堆0的情况。需要将前面的0全都过滤掉。
  利用两个指针，一个头，一个尾，将有效部分囊括起来，提取出子字符串。
  为了防止溢出，需要对子字符串的长度进行限制，超过10位数的（Integer.MAX_VALUE是10位），直接根据正负号返回MAX_VALUE或者MIN_VALUE。
  正常的情况就通过parseLong来转换（因为10位数的还是有超int范围的数）
  执行用时：34 ms 已经战胜 84.63 % 的 java 提交记录
*/
class Solution {
    public int myAtoi(String str) {
        int begin = 1;
        int end = 1;
        boolean negative = false;
        
        str = str.trim();
        
        char[] a = str.toCharArray();
        if (a.length == 0)
            return 0;
        if (a.length == 1) {
            if (Character.isDigit(a[0]))
                return Integer.parseInt(str);
            else
                return 0;
        }
        
        if (Character.isDigit(a[0]) || a[0] == '-' || a[0] == '+') {
            if (a[0] == '-')
                negative = true;
            if (Character.isDigit(a[0]))
                begin = 0;
            //前面一堆0
            if (str.startsWith("00") || str.startsWith("+00") || str.startsWith("-00")) {
                while (begin < a.length && a[begin] == '0')
                    begin++;
            }
            end = begin;
            while (end < a.length && Character.isDigit(a[end]))
                end++;
            str = str.substring(begin, end); //正负号和前导0已经被去掉了
            
            if (str.length() == 0)
                return 0;
            
            if (str.length() > 10) { //INT_MAX有10位
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            else {
                long res = Long.parseLong(str);
                if (res > Integer.MAX_VALUE)
                    return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                else {
                    return negative ? -(int)res : (int)res;
                }
            }
        }
        else {
            return 0;
        }
    }
}
