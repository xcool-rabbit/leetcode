//13. 罗马数字转整数
/*
  由于不需要判定给出的罗马数字是否合规，所以只需要从左到右将字母表示的数字相加减就好
  如果右边的字母代表的数字比自己大，那么这一位的权值应该为-1
  执行用时：5 ms 已经战胜 97.65 % 的 java 提交记录
*/
class Solution {
    public int romanToInt(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int negative = i < s.length() - 1 && value(s.charAt(i)) < value(s.charAt(i + 1)) ? -1 : 1;
            sum += negative * value(s.charAt(i));
        }
        return sum;
    }
    
    private int value(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
        }
        return 0;
    }
}
