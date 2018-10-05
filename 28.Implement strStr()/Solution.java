//28. 实现strStr()
/*
  偷鸡就完事儿了，String类的方法直接调。
  讲道理的话是KMP，暂时不想接触编译技术的算法了，脑阔痛。
  执行用时：4 ms 已经战胜 99.81 % 的 java 提交记录
*/
class Solution {
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}
