// 剑指 Offer 19. 正则表达式匹配
/*
  选用了最肝帝的一种写法
  if-else加递归
  第一是区分正则是否带型号，不带星号的很容易解决，带星号要展开讨论
  一种是".*"这个太赖了，匹配一切，所以循环递归，分成子任务
  剩下的是字母星号，思路大体类似
  一点小优化，连续星号可以规约掉
  执行用时：4 ms, 在所有 Java 提交中击败了43.77%的用户
*/
class Solution {
    public boolean isMatch(String s, String p) {
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();
        int indexS = 0;
        int indexP = 0;
        while (indexS < sc.length && indexP < pc.length) {
            if (indexP + 1 < pc.length && pc[indexP + 1] == '*') {
                if (pc[indexP] == '.') {
                    int offset = 0;
                    while (indexP + 3 < pc.length && (pc[indexP] == pc[indexP + 2] && pc[indexP + 1] == pc[indexP + 3])) {
                        indexP = indexP + 2;
                    }
                    while (indexS + offset <= sc.length) {
                        if (isMatch(s.substring(indexS + offset), p.substring(indexP + 2))) {
                            return true;
                        }
                        offset++;
                    }
                    return false;
                } else {
                    if (sc[indexS] != pc[indexP]) {
                        indexP = indexP + 2;
                    } else {
                        while (indexP + 3 < pc.length && (pc[indexP] == pc[indexP + 2] && pc[indexP + 1] == pc[indexP + 3])) {
                            indexP = indexP + 2;
                        }
                        indexP = indexP + 2;
                        char cur = sc[indexS];
                        int offset = 0;
                        while (indexS + offset <= sc.length) {
                            if (offset > 0 && sc[indexS + offset - 1] != sc[indexS]) {
                                break;
                            }
                            if (isMatch(s.substring(indexS + offset), p.substring(indexP))) {
                                return true;
                            }
                            offset++;
                        }
                    }
                }
            } else {
                if (pc[indexP] != '.' && sc[indexS] != pc[indexP]) {
                    return false;
                } else {
                    indexS++;
                    indexP++;
                }
            }
        }
        if (indexS == sc.length && indexP < pc.length) {
            while (indexP + 1 < pc.length && pc[indexP + 1] == '*') {
                indexP += 2;
            }
        }
        return indexS == sc.length && indexP == pc.length;
    }
}
/*
  这是官方解法，这么慢我是没想到的
  出现这种情况一般是，长江后浪推前浪，这题上过一次每日一题，被大量用户cv刷题导致的
  执行用时：6 ms, 在所有 Java 提交中击败了26%的用户
*/
class Solution {
    public boolean isMatch(String s, String p) {
        int[][] dp = new int[s.length() + 1][p.length() + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = -1;
        }
        dp[0][0] = 1;
        for (int j = 1; j < dp[0].length; j++) {
            if (p.charAt(j - 1) != '*') {
                dp[0][j] = -1;
            } else {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (p.charAt(j - 1) != '*') {
                    if (p.charAt(j - 1) == '.' || s.charAt(i - 1) == p.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = -1;
                    }
                } else {
                    dp[i][j] = dp[i][j - 2];
                    if (dp[i][j] != 1) {
                        if (p.charAt(j - 2) == '.' || s.charAt(i - 1) == p.charAt(j - 2)) {
                            dp[i][j] = dp[i - 1][j];
                        }
                    }
                }
            }
        }
        return dp[s.length()][p.length()] == 1;
    }
}
/*
  这是我优化过的版本
  缩减了推理规模
  前几次交都比官方的慢，难道我写的就这么拉？
  后来不信邪多交了几次也达到了官方的水平，哈哈哈哈
  但是还是看了一下官方的，善用逻辑运算，但是毕竟我这个是int的dp，学不来
  所以这东西，只要思想没问题，稍微慢一点也无所谓
  执行用时：3 ms, 在所有 Java 提交中击败了45%的用户
  执行用时：2 ms, 在所有 Java 提交中击败了91%的用户
*/
class Solution {
    public int[][] dp;
    public String s;
    public String p;
    public boolean isMatch(String s, String p) {
        this.s = s;
        this.p = p;
        dp = new int[s.length() + 1][p.length() + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = -1;
        }
        dp[0][0] = 1;
        for (int j = 1; j < dp[0].length; j++) {
            if (p.charAt(j - 1) != '*') {
                dp[0][j] = -1;
            } else {
                dp[0][j] = dp[0][j - 2];
            }
        }

        int i = 1;
        int j = 1;
        while (i < dp.length && j < dp[0].length) {
            getValue(i, j);
            if (i == s.length() && j == p.length()) {
                if (dp[s.length()][p.length()] != 0) {
                    break;
                }
            }
            if (dp[i][j] == 1) {
                if (i + 1 < dp.length) {
                    i++;
                }
                if (j + 1 < dp[0].length) {
                    j++;
                }
            } else if (dp[i][j] == -1) {
                if (j + 1 < dp[0].length) {
                    j++;
                } else if (i + 1 < dp.length){
                    i++;
                }
            }
        }
        return dp[s.length()][p.length()] == 1;
    }

    public void getValue(int i, int j) {
        if (p.charAt(j - 1) != '*') {
            if (p.charAt(j - 1) == '.' || s.charAt(i - 1) == p.charAt(j - 1)) {
                if (dp[i - 1][j - 1] == 0) {
                    getValue(i - 1, j - 1);
                }
                dp[i][j] = dp[i - 1][j - 1];
            } else {
                dp[i][j] = -1;
            }
        } else {
            dp[i][j] = dp[i][j - 2];
            if (dp[i][j] == 0) {
                if (p.charAt(j - 2) == '.' || s.charAt(i - 1) == p.charAt(j - 2)) {
                    if (dp[i - 1][j] == 1) {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            } else if (dp[i][j] == -1) {
                if (p.charAt(j - 2) == '.' || s.charAt(i - 1) == p.charAt(j - 2)) {
                    dp[i][j] = dp[i - 1][j];
                }
            }
            if (dp[i][j] == 0) {
                if (dp[i][j - 2] == 0) {
                    getValue(i, j - 2);
                } else {
                    getValue(i - 1, j);
                }
            }
        }
        
    }
}
