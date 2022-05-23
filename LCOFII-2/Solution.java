// 剑指 Offer II 002. 二进制加法
/*
  这个题以前是做过的，但是看了一眼当时的代码，写的可真是复杂
  还有什么栈什么的
  我晕了
  现在的话，经过三百题的历练，可以说是成熟多了
  执行用时：1 ms, 在所有 Java 提交中击败了99.92%的用户
*/
class Solution {
    public String addBinary(String a, String b) {
        if (a.length() < b.length()) {
            return addBinary(b, a);
        }
        // a > b
        int aLen = a.length();
        int bLen = b.length();
        char extra = '0';
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= aLen; i++) {
            char ac = a.charAt(aLen - i);
            char bc = bLen - i >= 0 ? b.charAt(bLen - i) : '0';
            int count = 0;
            if (ac == '1') {
                count++;
            }
            if (bc == '1') {
                count++;
            }
            if (extra == '1') {
                count++;
            }
            if ((count & 1) == 1) {
                sb.insert(0, '1');
            } else {
                sb.insert(0, '0');
            }
            if (count >= 2) {
                extra = '1';
            } else {
                extra = '0';
            }
        }
        if (extra == '1') {
            sb.insert(0, '1');
        }
        return sb.toString();
    }
}
