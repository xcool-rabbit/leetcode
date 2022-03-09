// 521. 最长特殊序列 Ⅰ
/*
  脑筋急转弯题，长的那个序列的子序列（自身），在短的里面，肯定没有
  所以，谁长就是谁
  如果是长度相等，就看看是不是完全等于
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int findLUSlength(String a, String b) {
        if (a.length() < b.length()) {
            return findLUSlength(b, a);
        }
        if (a.length() == b.length()) {
            return a.equals(b) ? -1 : a.length();
        }
        return a.length();
    }
}
