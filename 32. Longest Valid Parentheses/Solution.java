// 32. 最长有效括号
/*
  遍历一遍能标记出所有能匹配上的括号的位置，再遍历一遍就知道最长的有多长了
  离谱的是为什么依旧这么慢
  执行用时：3 ms, 在所有 Java 提交中击败了17.27%的用户
*/
class Solution {
    public int longestValidParentheses(String s) {
        boolean[] mark = new boolean[s.length()];
        LinkedList<List<Integer>> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.isEmpty()) {
                List<Integer> e = new ArrayList<>(2);
                e.add(c == '(' ? -2 : -1);
                e.add(i);
                stack.push(e);
                continue;
            }
            if (stack.peek().get(0) == -2 && c == ')') {
                mark[stack.peek().get(1)] = true;
                mark[i] = true;
                stack.pop();
            } else {
                List<Integer> e = new ArrayList<>(2);
                e.add(c == '(' ? -2 : -1);
                e.add(i);
                stack.push(e);
            }
        }
        int count = 0;
        int max = 0;
        for (boolean b : mark) {
            count = b ? count + 1 : 0;
            max = max > count ? max : count;
        }
        return max;
    }
}
/*
  看了一下题解，果然可以dp
  其实这种最长xxx的，很适合用dp，但是我简单的想了一下，不知道怎么推理
  看了题解也想了半天
  主要是俺有O(N)的方法，就，没有太钻研dp
  每次做dp的题都能有新发现，又是不一样的推导思路，带了一些if的情况
  执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        int max = 0;
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                dp[i] = 0;
                continue;
            }
            if (s.charAt(i - 1) == '(') {
                dp[i] = i - 2 >= 0 ? dp[i - 2] + 2 : 2;
            } else {
                if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = i - dp[i - 1] - 2 >= 0 ? dp[i - 1] + 2 + dp[i - dp[i - 1] - 2] : dp[i - 1] + 2;
                }
            }
            max = max > dp[i] ? max : dp[i];
        }
        return max;
    }
}
