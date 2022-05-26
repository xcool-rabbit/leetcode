// 318. 最大单词长度乘积
/*
  同LCOFII-5
  我跟最快的题解写的一模一样，应该是用例有变化
  执行用时：9 ms, 在所有 Java 提交中击败了49.58%的用户
*/
class Solution {
    public int maxProduct(String[] words) {
        int[] chart = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                chart[i] |= 1 << (words[i].charAt(j) - 'a');
            }
        }
        int ret = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((chart[i] & chart[j]) == 0) {
                    ret = Math.max(ret, words[i].length() * words[j].length());
                }
            }
        }
        return ret;
    }
}
