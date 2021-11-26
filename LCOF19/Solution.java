// 剑指 Offer 19. 正则表达式匹配
/*
  选用了最肝帝的一种写法
  if-else加递归
  第一是区分正则是否带型号，不带星号的很容易解决，带星号要展开讨论
  一种是".*"这个太赖了，匹配一切，所以循环递归，分成子任务
  剩下的是字母星号，思路大体类似
  一点小优化，连续星号可以规约掉
  执行用时：4 ms, 在所有 Java 提交中击败了57.72%的用户
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
  动态规划才是这道题的正解
  我承认这是一个非常复杂的动态规划，或者说，很难想到这是一道动态规划题
  整个解法设计巧妙，十分成熟，颇有小KMP的风范
  首先是关于0行0列的设定，可以很好应对空串的情况
  如果不是星号，就看当前位置能否匹配的上，能的话，当前dp的值就是左上角的dp的值
  匹配不上就是false
  如果是星号，星号可以代表的含义有：匹配0次，匹配0次以上
  匹配0次，即，将dp[i][j - 2]的值赋给当前，表示pattern里这个星号和之前一个字符，我压根就没管，之前将两个字符之前的值赋过来
  匹配0次以上，需要保证星号前面的那个字符是对的，然后dp[i - 1][j]的值赋过来，表示当前匹配状态应该跟s少一个字符的时候一样
  所以核心思想就是，推理状态
  执行用时：2 ms, 在所有 Java 提交中击败了98.15%的用户
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
  上一个方法我起小心思了
  因为高效的匹配应该向右下增长，而不是一行行的要把每个dp的值都推理出来
  所以在上面的代码中我使用了int数组，就是为了能利用1,0,-1来表示当前的推理状态
  0表示未推理
  这种优化需要解决的问题是，在我们推理过程中，如果遇到了0值，即不确定的值时，应该怎样回溯求解
  回溯这个词都说出口了，当然是，递归了！
  能想到如此优雅的优雅的方法真是佩服我自己
  其实除了这个地方比较难以外，在推理的逻辑判断上，也要多一种处理的情况，写起来还是蛮复杂的
  重点是有星号的时候
  匹配0次的值如果是1，就go ahead继续推理
  如果是0，要去看看匹配0次以上的那个格子，是否是一个确定的值，如果是1，那么继续推理
  如果是0，就回溯
  总之就是1通过，其他值要刨根问底
  这种方法在LCOF的测试集上跑，反而更慢了，当时看到这个结果的时候我很难受
  但是后续在LC原版测试集上跑是更快的
  因为从原理上来讲，我的方法减少了推理次数，理应更快
  并且在卡测试用例的时候我也打出来过dp数组，确实有很多0，确实降低了推理次数
  执行用时：3 ms, 在所有 Java 提交中击败了68%的用户
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