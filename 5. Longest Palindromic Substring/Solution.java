//5. 最长回文子串
/*
  两层循环遍历，找出最长的回文子串
  超时
*/
class Solution {
    public String longestPalindrome(String s) {
        if (s.length() == 0) {
            return "";
        }
        String subReverseString = s.substring(0, 1);
        int longestLength = 1;
        for (int i = 0; i < s.length();  i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && isReverse(s.substring(i, j + 1))) {
                    if ((j + 1 - i) > longestLength) {
                        subReverseString = s.substring(i, j + 1);
                        longestLength = j + 1 - i;
                    }
                }
            }
        }
        return subReverseString;
    }
    
    public boolean isReverse(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
/*
  经过分析，问题出在了计算了很多无用功
  首先要想找最长的，那么在遍历的时候就应该倾向于从最长的开始遍历
  还有一个就是快速结束的机制，如果接下来遍历的比已知的回文子串长度要短，就提前结束遍历
  从这两方面对程序进行优化，可以大幅提升程序运行效率
  执行用时：573 ms 已经战胜 6.57 % 的 java 提交记录
*/
class Solution {
    public String longestPalindrome(String s) {
        if (s.length() == 0) {
            return "";
        }
        String subReverseString = s.substring(0, 1);
        int longestLength = 1;
        for (int i = 0; i < s.length();  i++) {
            if ((s.length() - i) < longestLength) {
                break;
            }
            for (int j = s.length() - 1; j > i; j--) {
                if ((j + 1 - i) <= longestLength) {
                    break;
                }
                if (s.charAt(i) == s.charAt(j) && isReverse(s.substring(i, j + 1))) {
                    subReverseString = s.substring(i, j + 1);
                    longestLength = j + 1 - i;
                    break;
                }
            }
        }
        return subReverseString;
    }
    
    public boolean isReverse(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
/*
  可以看到这种提升仍然不理想，要去学习一些高端操作了
  上一种方法复杂度是O(N ^ 3)，遍历是O(N ^ 2)，但是每次遍历还需要检查一遍字符串是否回文
  动态规划
  回文字符串有点递归的意味
  状态转移方程P(i, j) = P(i + 1, j - 1) && s(i) == s(j)
  这个方程成立的前提是字符串长度大于2
  需要对长度1和2单独考虑
  动态规划就是个填表的过程，只不过这次的填表有点奇怪
  执行用时：148 ms 已经战胜 26.27 % 的 java 提交记录
*/
class Solution {
    public String longestPalindrome(String s) {
        if (s.length() == 0) {
            return "";
        }
        String subReverseString = "";
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int offset = 0; offset < dp.length; offset++) {
            for (int i = 0; i < s.length(); i++) {
                if ((i + offset) >= s.length()) {
                    break;
                }
                switch (offset) {
                    case 0:
                        dp[i][i + offset] = true;
                        break;
                    case 1:
                        dp[i][i + offset] = s.charAt(i) == s.charAt(i + offset);
                        break;
                    default:
                        dp[i][i + offset] = (dp[i + 1][i + offset - 1]) && (s.charAt(i) == s.charAt(i + offset));
                }
                if (dp[i][i + offset] && (offset + 1) > subReverseString.length()) {
                    subReverseString = s.substring(i, i + offset + 1);
                }
            }
        }
        return subReverseString;
    }
}
/*
  填表的复杂度是O(N ^ 2)
  回文字符串可以从中心增长
  把所有中心全试一遍
  复杂度还是O(N ^ 2)
  不想写了，这题都要把我写吐了，这么多种方法
  并且这个方法实现起来不是很难
*/
/*
  终极解法————Manacher算法
  （居然有人专门为这道题想了很多很多年）
  跟中心扩散的思想一样，但是又运用了动态规划的思想
  将扩散的结果记录下来，对后面的判断有帮助，有点KMP那个意思
*/