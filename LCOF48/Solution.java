// 剑指 Offer 48. 最长不含重复字符的子字符串
/*
  dp
  dp[i] = dp[i - 1] + 1
  特殊情况就是如果s[i]跟当前子字符串有重复
  子字符串应该从重复的位置往后重新开始
  但是需要注意一个优先级的问题
  要取两种情况的最小值，意味，要同时满足这两个要求
  执行用时：4 ms, 在所有 Java 提交中击败了91.89%的用户
*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int ret = 0;
        int[] dp = new int[s.length()];
        dp[0] = 1;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            int lastIndex = map.getOrDefault(cur, -1);
            if (i - 1 >= 0) {
                dp[i] = dp[i - 1] + 1;
                if (lastIndex != -1) {
                    dp[i] = Math.min(dp[i], i - lastIndex);
                }
            }
            map.put(cur, i);
            ret = Math.max(ret, dp[i]);
        }
        return ret;
    }
}
