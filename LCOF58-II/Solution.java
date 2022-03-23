// 剑指 Offer 58 - II. 左旋转字符串
/*
  what???凭什么我的方法没人家快？
  我寻思你就是JDK自己实现，也得是这几步啊
  执行用时：2 ms, 在所有 Java 提交中击败了52.42%的用户
*/
class Solution {
    public String reverseLeftWords(String s, int n) {
        char[] cs = new char[s.length()];
        int index = 0;
        for (int i = n; i < s.length(); i++) {
            cs[index++] = s.charAt(i);
        }
        for (int i = 0; i < n; i++) {
            cs[index++] = s.charAt(i);
        }
        return new String(cs);
    }
}
/*
  调用API果然更快，无语了嗷
  看了一下源码，调用的native方法的System.arraycopy
  那确实快嗷
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }
}
