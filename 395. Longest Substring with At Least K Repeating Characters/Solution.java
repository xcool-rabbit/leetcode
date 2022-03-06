// 395. 至少有 K 个重复字符的最长子串
/*
  今天终于舒服了一下
  本来把题读错了，够难受的了
  但是冷静下来沉着思考，暴力要O(n ^ 3)，肯定不行
  后面发现，从最长的开始考虑，只要里面存在不足k个的字符，那么最长子串就一定会被它分隔开
  正所谓，一只苍蝇坏了一锅汤
  所以就只能把问题规模缩小
  所有字符都不少于k个的时候，整个字符串就是最长子串
  结果证明了我方法的先进性
  执行用时：2 ms, 在所有 Java 提交中击败了69.53%的用户
*/
class Solution {
    public int longestSubstring(String s, int k) {
        if (s.length() < k) {
            return 0;
        }
        List<List<Integer>> list = new ArrayList<>(26);
        for (int i = 0; i < 26; i++) {
            list.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            list.get(c - 'a').add(i);
        }

        for (List<Integer> l : list) {
            if (l.size() != 0 && l.size() < k) {
                int max = 0;
                for (int i = 0; i <= l.size(); i++) {
                    if (i == 0) {
                        max = Math.max(max, longestSubstring(s.substring(0, l.get(i)), k));
                    } else if (i == l.size()){
                        max = Math.max(max, longestSubstring(s.substring(l.get(i - 1) + 1), k));
                    } else {
                        max = Math.max(max, longestSubstring(s.substring(l.get(i - 1) + 1, l.get(i)), k));
                    }
                }
                return max;
            }
        }
        return s.length();
    }
}
