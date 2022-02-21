// 140. 单词拆分 II
/*
  就这？我139题超时的做法，搬过来直接用
  139题超时的原因是例子太变态，如果只判断boolean的话，回溯剪枝是慢的
  但是呢，这个题要把所有的可能写出来，那么它就没办法用变态例子
  我的回溯剪枝，优势就是能写出来
  所以这也得出一个经验，如果是只判断boolean，优先用dp，如果要写出来，那就考虑回溯了
  执行用时：1 ms, 在所有 Java 提交中击败了94.52%的用户
*/
class Solution {
    List<String> ret = new LinkedList<>();
    StringBuilder sb = new StringBuilder();
    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s.length() == 0) {
            ret.add(sb.substring(0, sb.length() - 1).toString());
            return null;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                sb.append(word + " ");
                wordBreak(s.substring(word.length()), wordDict);
                sb.delete(sb.length() - word.length() - 1, sb.length());
            }
        }
        return ret;
    }
}
