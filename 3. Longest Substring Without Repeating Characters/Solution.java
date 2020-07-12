//3. 无重复字符的最长子串
/*
  用一个队列结构保存最长子串
  Set结构判断重复字符
  执行用时：10 ms 已经战胜 40.29 % 的 java 提交记录
*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Queue<Character> queue = new LinkedList<>();
        Set<Character> set = new HashSet<>();
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (set.contains(cur)) {
                while (!queue.isEmpty() && queue.peek() != cur) {
                    set.remove(queue.poll());
                }
                queue.poll();
            }
            queue.add(s.charAt(i));
            set.add(cur);
            max = queue.size() > max ? queue.size() : max;
        }
        return max;
    }
}
/*
  上一种解法的问题出在了不需要队列，用一个变量就可以维护这个结构
  执行用时：7 ms 已经战胜 82.10 % 的 java 提交记录
*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>(26);
        int start = 0;
        int max = 0;
        for (int end = 0; end < s.length(); end++) {
            char cur = s.charAt(end);
            if (set.contains(cur)) {
                while (cur != s.charAt(start)) {
                    set.remove(s.charAt(start++));
                }
                start++;
            }
            set.add(cur);
            int length = end - start + 1;
            max = length > max ? length : max;
        }
        return max;
    }
}
