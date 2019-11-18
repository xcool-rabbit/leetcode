//151. 翻转字符串里的单词
/*
  分割然后反着拼接
  split的时候不知道为什么不能用\s
  进阶里面还有一种不占用额外空间的方法，就是自己在里面倒腾，我就不做了
  执行用时：7 ms 已经战胜 56.36 % 的 java 提交记录
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
