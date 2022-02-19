// 131. 分割回文串
/*
  哎，吃力不讨好
  当我发现我的方法需要利用set去重的时候，我就知道它凉了
  我想的是，找到回文子串，然后递归左右两边
  最后拼起来
  但是这样会有重复，只能再加一个set去重
  看到重复的现象我就想到了回溯
  执行用时：879 ms, 在所有 Java 提交中击败了5.29%的用户
*/
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> ret = new ArrayList<>();
        Map<Character, List<Integer>> map = new HashMap<>(26);
        List<String> tmp = new ArrayList(s.length());
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                map.get(s.charAt(i)).add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(s.charAt(i), list);
            }
            tmp.add(s.substring(i, i + 1));
        }
        ret.add(tmp);
        if (s.length() == 0) {
            tmp.add("");
            return ret;
        }
        Set<List<String>> set = new HashSet<>();
        while (map.entrySet().size() != 0) {
            Iterator<Map.Entry<Character, List<Integer>>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Character, List<Integer>> e = it.next();
                List<Integer> list = e.getValue();
                if (list.size() == 1) {
                    it.remove();
                } else {
                    int start = list.get(0);
                    Iterator<Integer> itList = list.iterator();
                    itList.next();
                    while (itList.hasNext()) {
                        int end = itList.next();
                        if (isReverse(s, start, end)) {
                            // System.out.println(s.substring(0, start));
                            List<List<String>> left = partition(s.substring(0, start));
                            // System.out.println(s.substring(end + 1));
                            List<List<String>> right = partition(s.substring(end + 1));
                            for (List<String> l : left) {
                                // System.out.println("left" + l);
                                for (List<String> r : right) {
                                    // System.out.println("right" + r);
                                    tmp = new ArrayList<>(l.size() + 1 + r.size());
                                    if (l.get(0).length() != 0) {
                                        tmp.addAll(l);
                                    }
                                    tmp.add(s.substring(start, end + 1));
                                    if (r.get(0).length() != 0) {
                                        tmp.addAll(r);
                                    }
                                    if (!set.contains(tmp)) {
                                        ret.add(tmp);
                                        set.add(tmp);
                                    }
                                }
                            }
                        }
                    }
                    list.remove(0);
                }
            }
        }
        
        return ret;
    }

    public boolean isReverse(String s, int start, int end) { // both included
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
/*
  俺的递归是这样写的，很容易看懂，但是有个问题就是
  s.substring(i + 1)进行了一些重复计算，这也是慢的原因，我想把他们存储起来或者
  换个递归的方式
  执行用时：32 ms, 在所有 Java 提交中击败了5.18%的用户
*/
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> ret = new ArrayList<>();
        if (s.length() == 0) {
            List<String> tmp = new LinkedList<>();
            ret.add(tmp);
            return ret;
        }
        for (int i = 0; i < s.length(); i++) {
            if (isReverse(s, 0, i)) {
                List<List<String>> child = partition(s.substring(i + 1));
                for (List<String> list : child) {
                    list.add(0, s.substring(0, i + 1));
                    ret.add(list);
                }
            }
        }
        return ret;
    }

    public boolean isReverse(String s, int start, int end) { // both included
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
/*
  把partition(s.substring(i + 1))的结果存起来有点麻烦，各种引用啥的，得复制，不能赋值
  稍微看了下题解，有点感悟
  这种引用多的一匹的，最好用回溯
  用完了就删，非常的好管理
  性能还算可以，但是还有地方可以优化
  判断回文，依然有很大的重复
  执行用时：8 ms, 在所有 Java 提交中击败了61.48%的用户
*/
class Solution {
    List<List<String>> ret = new ArrayList<>();
    List<String> cur = new ArrayList<>();
    public List<List<String>> partition(String s) {
        if (s.length() == 0) {
            ret.add(new ArrayList<>(cur));
            return ret;
        }
        for (int i = 0; i < s.length(); i++) {
            if (isReverse(s, 0, i)) {
                cur.add(s.substring(0, i + 1));
                partition(s.substring(i + 1));
                cur.remove(cur.size() - 1);
            }
        }
        return ret;
    }

    public boolean isReverse(String s, int start, int end) { // both included
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}

/*
  尝试去优化判断回文，没想到给负优化了
  之前把每个字母出现过的位置都存起来，为了能节省一点点遍历的时间，因为回文字符串的首尾一定是相同的字母
  但是呢，emmmm，时间确实变慢了
  执行用时：19 ms, 在所有 Java 提交中击败了5.72%的用户
*/
class Solution {
    List<List<String>> ret = new ArrayList<>();
    List<String> cur = new ArrayList<>();
    Map<Character, List<Integer>> map;
    int originalLength;
    public List<List<String>> partition(String s) {
        if (map == null) {
            originalLength = s.length();
            map = new HashMap<>(26);
            for (int i = 0; i < s.length(); i++) {
                if (map.containsKey(s.charAt(i))) {
                    map.get(s.charAt(i)).add(i);
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    map.put(s.charAt(i), list);
                }
            }
        }

        if (s.length() == 0) {
            ret.add(new ArrayList<>(cur));
            return ret;
        }
        int offset = originalLength - s.length();
        List<Integer> list = map.get(s.charAt(0));
        for (Integer i : list) {
            i -= offset;
            if (i >= 0) {
                if (isReverse(s, 0, i)) {
                    cur.add(s.substring(0, i + 1));
                    partition(s.substring(i + 1));
                    cur.remove(cur.size() - 1);
                }
            }
        }
        return ret;
    }

    public boolean isReverse(String s, int start, int end) { // both included
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
/*
  还是题解的方法硬啊，dp判断回文，直接把整个字符串，每两个字母之间的回文关系全推出来了
  确实都用得到
  终极版
  递推公式dp[i][j] = dp[i + 1][j - 1] & (s.charAt(i) ==s.charAt(j))
  题解还有个什么记忆化搜索，需要算哪个才算
  跟这个其实是一样的，因为，dp的每个位置都用得到
  执行用时：7 ms, 在所有 Java 提交中击败了84.33%的用户
*/
class Solution {
    List<List<String>> ret = new ArrayList<>();
    List<String> cur = new ArrayList<>();
    boolean[][] dp;
    int originalLength;
    public List<List<String>> partition(String s) {
        if (s.length() == 0) {
            ret.add(new ArrayList<>(cur));
            return ret;
        }
        if (dp == null) {
            originalLength = s.length();
            dp = new boolean[s.length()][s.length()];
            dp[0][0] = true;
            for (int i = 1; i < s.length(); i++) {
                dp[i][i - 1] = true;
                dp[i][i] = true;
            }
            for (int j = 0; j < s.length(); j++) {
                for (int i = 0; i < j; i++) {
                    dp[i][j] = dp[i + 1][j - 1] & (s.charAt(i) ==s.charAt(j));
                }
            }
        }
        int offset = originalLength - s.length();
        for (int i = 0; i < s.length(); i++) {
            if (dp[offset][i + offset]) {
                cur.add(s.substring(0, i + 1));
                partition(s.substring(i + 1));
                cur.remove(cur.size() - 1);
            }
        }
        return ret;
    }

    public boolean isReverse(String s, int start, int end) { // both included
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
