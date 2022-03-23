// 剑指 Offer 05. 替换空格
/*
  调用api，replace即可
  自己实现也可以，不难，没必要写了
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public String replaceSpace(String s) {
        return s.replace(" ", "%20");
    }
}
