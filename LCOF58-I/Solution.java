// 剑指 Offer 58 - I. 翻转单词顺序
/*
  调API的
  可能慢在split了
  执行用时：4 ms, 在所有 Java 提交中击败了26.23%的用户
*/
class Solution {
    public String reverseWords(String s) {
        String[] words = s.trim().split("[ ]+");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            stringBuilder.append(words[i]);
            stringBuilder.append(" ");
        }
        return stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
    }
}
/*
  split慢那就不用了！
  但是为啥这样更慢了，无语住了，快的到底是怎么写的
  用了题解的高集成度API，也是5ms，合理推断这是以前测试用例少
  执行用时：5 ms, 在所有 Java 提交中击败了24.14%的用户
*/
class Solution {
    public String reverseWords(String s) {
        StringBuilder ret = new StringBuilder();
        int start = 0;
        int end = 0;
        while (end < s.length()) {
            while (start < s.length() && s.charAt(start) == ' ') {
                start++;
            }
            if (start >= s.length()) {
                break;
            }
            end = start;
            while (end < s.length() && s.charAt(end) != ' ') {
                end++;
            }
            ret.insert(0, s.substring(start, end));
            // System.out.println(s.substring(start, end));
            ret.insert(0, ' ');
            start = end;
        }
        if (ret.length() > 0) {
            ret.deleteCharAt(0);
        }
        return ret.toString();
    }
}
