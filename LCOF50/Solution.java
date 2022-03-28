// 剑指 Offer 50. 第一个只出现一次的字符
/*
  遍历第一遍记录次数
  遍历第二遍找到那个只出现一次的
  记得要按照字符串的顺序遍历，而不是按照记录表的顺序遍历
  执行用时：5 ms, 在所有 Java 提交中击败了88.16%的用户
*/
class Solution {
    public char firstUniqChar(String s) {
        int[] times = new int[26];
        for (int i = 0; i < s.length(); i++) {
            times[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (times[s.charAt(i) - 'a'] == 1) {
                return s.charAt(i);
            }
        }
        return ' ';
    }
}
