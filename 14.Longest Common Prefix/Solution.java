//14. 最长公共前缀
/*
  以字符串数组中的任意字符串为基础，逐位增加，对整个字符串数组的前缀进行检测，一旦有不匹配的，就返回当前前缀-1
  执行用时：12 ms 已经战胜 51.96 % 的 java 提交记录
*/
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0)
            return "";
        char[] a = strs[0].toCharArray();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            res.append(a[i]);
            for (String str : strs) {
                if (!str.startsWith(res.toString())) {
                    res.deleteCharAt(res.length() - 1);
                    return res.toString();
                }
            }
        }
        return strs[0];
    }
}
