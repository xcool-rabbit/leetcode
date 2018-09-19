//242. 有效的字母异位词
/*
  跟387题的布尔数组的思想一样，不过这个题需要统计每个字母的数量，所以用长度为26的int数组。
  根据第一个词的字母，在int数组中对相应位置+1；再根据第二个词的字母对应-1；最终检查int数组中各位是否为0，若为0则返回true，否则返回false。
  执行用时：4 ms 已经战胜 90.13 % 的 java 提交记录
*/
class Solution {
    public boolean isAnagram(String s, String t) {
        char[] a = s.toCharArray();
        char[] b = t.toCharArray();
        int[] c = new int[26];
        
        for (char i : a) {
            c[i - 'a']++;
        }
        
        for (char i : b) {
            c[i - 'a']--;
        }
        
        for (int i : c) {
            if (i != 0)
                return false;
        }
        
        return true;
    }
}
