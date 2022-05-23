// 剑指 Offer 20. 表示数值的字符串
/*
  无情的if-else
  主要是这个题，情况已经分的十分清晰了，照着它的分类写就OK了
  执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public boolean isNumber(String s) {
        s = s.trim();
        int next = matchDecimal(s, 0);
        if (next == -1) {
            next = matchInteger(s, 0);
            if (next == -1) {
                return false;
            }
        }
        if (next == s.length()) {
            return true;
        }
        if (s.charAt(next) == 'e' || s.charAt(next) == 'E') {
            next = matchInteger(s, next + 1);
        } else {
            return false;
        }
        return next == s.length();
    }

    public int matchDecimal(String s, int start) {
        if (start >= s.length()) {
            return -1;
        }
        if (s.charAt(start) == '+' || s.charAt(start) == '-') {
            start++;
        }

        int tmp = start;
        while (start < s.length() && Character.isDigit(s.charAt(start))) {
            start++;
        }
        if (start >= s.length()) {
            return -1;
        }

        if (s.charAt(start) == '.') {
            start++;
        } else {
            return -1;
        }

        while (start < s.length() && Character.isDigit(s.charAt(start))) {
            start++;
        }
        return start <= tmp + 1 ? -1 : start;
    }

    public int matchInteger(String s, int start) {
        if (start >= s.length()) {
            return -1;
        }
        if (s.charAt(start) == '+' || s.charAt(start) == '-') {
            start++;
        }

        int tmp = start;
        while (start < s.length() && Character.isDigit(s.charAt(start))) {
            start++;
        }
        return start == tmp ? -1 : start;
    }
}
