//557. 反转字符串中的单词 III
/*
  先分割单词，然后反转
  执行用时：7 ms 已经战胜 83.33 % 的 java 提交记录
*/
class Solution {
    public String reverseWords(String s) {
        String[] words = s.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (String word : words) {
            char[] letters = word.toCharArray();
            for (int i = 0; i < letters.length / 2; i++) {
                char tmp = letters[i];
                letters[i] = letters[letters.length - 1 - i];
                letters[letters.length - 1 - i] = tmp;
            }
            stringBuilder.append(letters);
            stringBuilder.append(" ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
