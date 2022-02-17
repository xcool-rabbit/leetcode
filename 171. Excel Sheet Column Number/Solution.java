// 171. Excel 表列序号
/*
  跟那些什么二进制十六进制一个意思
  这个是26进制
  需要注意的是这里面没有0，是从1 ~ 26
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int titleToNumber(String columnTitle) {
        int ret = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            ret *= 26;
            ret += columnTitle.charAt(i) - 'A' + 1;
        }
        return ret;
    }
}
