// 1910. 删除一个字符串中所有出现的给定子字符串
/*
  就是一个Java API调用题
  用StringBuilder，字符串修改效率更高
  执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public String removeOccurrences(String s, String part) {
        StringBuilder sb = new StringBuilder(s);
        int target = sb.indexOf(part);
        while (target != -1) {
            sb.delete(target, target + part.length());
            target = sb.indexOf(part);
        }
        return sb.toString();
    }
}
