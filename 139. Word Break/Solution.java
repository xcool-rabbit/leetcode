// 139. 单词拆分
/*
  递归再一次大失败！
  超时了，原理上没有问题且没有冗余
  但是如果wordDict中的一个word能被其他word表示，我这种方法会被迫冗余
  RTE
*/
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s.length() == 0) {
            return true;
        }
        boolean ret = false;
        StringBuilder sb = new StringBuilder(s);
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                if (wordBreak(s.substring(word.length()), wordDict)) {
                    return true;
                }
            }
        }
        return false;
    }
}
/*
  递归输麻了
  后来还想了一种优化，如果wordDict中的一个word能被其他word表示，那就应该将它删除
  但是这样只能删一个，因为在删第二个的时候，你不能保证，表示第一个的时候有没有用到第二个
  也就是说，你删了第二个，整个wordDict就有可能丧失表达第一个的能力
  但是，聪明的我想到了一个解决方案
  把wordDict按照从短到长排序
  表示前一个的时候，绝对没有用到后面的
  然鹅，还是不行
  "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
  ["aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa","ba"]
  这个例子，最后wordDict被我缩减成["aa", "aaa", "ba"]
  但是还是超时
  原因在于，每次"aa"回滚都会去尝试"aaa"，还是避免不了重复
  也就是说，这么长的a，对于我的方法，每一次都有岔路，这直接爆炸
*/
class Solution {
    boolean optimizeWordDict = false;
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s.length() == 0) {
            return true;
        }
        boolean ret = false;
        if (!optimizeWordDict) {
            optimizeWordDict = true;
            wordDict.sort(
                (w1, w2) -> w1.length() - w2.length()
            );
            ListIterator<String> it = wordDict.listIterator();
            while (it.hasNext()) {
                String tmp = it.next();
                it.remove();
                if (!wordBreak(tmp, wordDict)) {
                    it.add(tmp);
                }
            }
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                if (wordBreak(s.substring(word.length()), wordDict)) {
                    return true;
                }
            }
        }
        return false;
    }
}
/*
  题解用的dp
  刚开始我脑子里有这个想法，后面我觉得这玩意儿咋dp，我的递归挺好的
  后来看到题解的一瞬间，我就明白了
  唉这种字符串的题，不是回溯剪枝就是dp
  执行用时：3 ms, 在所有 Java 提交中击败了87.65%的用户
*/
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i < dp.length; i++) {
            for (String word : wordDict) {
                if (i - word.length() >= 0 && dp[i - word.length()] && s.substring(i - word.length(), i).equals(word)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[dp.length - 1];
    }
}
