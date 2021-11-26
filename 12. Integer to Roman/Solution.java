// 12. 整数转罗马数字
/*
  朴实无华的代码，就是有点长，从大单位到小单位，慢慢贪婪
  执行用时：5 ms, 在所有 Java 提交中击败了88.12%的用户
*/
class Solution {
    public StringBuilder sb = new StringBuilder();
    public String intToRoman(int num) {
        if (num / 1000 > 0) {
            addString("M", num / 1000);
            num %= 1000;
        }
        if (num / 900 > 0) {
            addString("CM", num / 900);
            num %= 900;
        }
        if (num / 500 > 0) {
            addString("D", num / 500);
            num %= 500;
        }
        if (num / 400 > 0) {
            addString("CD", num / 400);
            num %= 400;
        }
        if (num / 100 > 0) {
            addString("C", num / 100);
            num %= 100;
        }
        if (num / 90 > 0) {
            addString("XC", num / 90);
            num %= 90;
        }
        if (num / 50 > 0) {
            addString("L", num / 50);
            num %= 50;
        }
        if (num / 40 > 0) {
            addString("XL", num / 40);
            num %= 40;
        }
        if (num / 10 > 0) {
            addString("X", num / 10);
            num %= 10;
        }
        if (num / 9 > 0) {
            addString("IX", num / 9);
            num %= 9;
        }
        if (num / 5 > 0) {
            addString("V", num / 5);
            num %= 5;
        }
        if (num / 4 > 0) {
            addString("IV", num / 4);
            num %= 4;
        }
        if (num / 1 > 0) {
            addString("I", num / 1);
            num %= 1;
        }
        return sb.toString();
    }

    public void addString(String s, int times) {
        for (int i = 0; i < times; i++) {
            sb.append(s);
        }
    }
}
