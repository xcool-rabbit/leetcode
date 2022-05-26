// 剑指 Offer II 005. 单词长度的最大乘积
/*
  估计是暴力解法里面最优秀的那一批了，但是显然并不是最优方法
  首先这个字符重复指的是两者之间，比如单个字符串内部有重复是没有关系的
  所以我用布尔数组来判断重复，比那些set之类的要快好多
  然后还有一个快速判断乘积是否有可能大于ret的
  这里我尝试了一个优化，就是把words排个序，长的放前面，这样更有可能先遇到大的
  并且遍历的顺序也做了些些调整，保证先遍历乘积大的（但并不绝对，所以没有办法做快速返回）
  但是效果都不好，让我们一起来看看题解是什么惊为天人的操作叭！
  执行用时：23 ms, 在所有 Java 提交中击败了42.70%的用户
*/
class Solution {
    public int maxProduct(String[] words) {
        boolean[][] chart = new boolean[words.length][26];
        for (int i = 0; i < words.length; i++) {
            chart[i] = genBooleanArray(words[i]);
        }
        int ret = 0;
        for (int i = 0; i < words.length; i++) {
            boolean[] b1 = chart[i];
            for (int j = i + 1; j < words.length; j++) {
                boolean[] b2 = chart[j];
                if (words[i].length() * words[j].length() > ret) {
                    if (!same(b1, b2)) {
                        ret = words[i].length() * words[j].length();
                    }
                }
            }
        }
        return ret;
    }

    public boolean[] genBooleanArray(String s) {
        boolean[] chart = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            chart[s.charAt(i) - 'a'] = true;
        }
        return chart;
    }

    public boolean same(boolean[] b1, boolean[] b2) {
        for (int i = 0; i < b1.length; i++) {
            if (b1[i] && b2[i]) {
                return true;
            }
        }
        return false;
    }
}
/*
  不排序反而更快捏
  这也打开了一个新思路，就是可以把一个boolan[26]的数组改成一个int来表示
  并且在运算交集并集的时候，int展现出了超强的优势
  执行用时：11 ms, 在所有 Java 提交中击败了50.06%的用户
*/
class Solution {
    public int maxProduct(String[] words) {
        // Arrays.sort(words, (s1, s2) -> s1.length() - s2.length());
        int[] chart = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                chart[i] |= 1 << (words[i].charAt(j) - 'a');
            }
        }
        int ret = 0;
        for (int i = words.length - 1; i >= 0; i--) {
            for (int j = words.length - 1; j > i; j--) {
                if (words[i].length() * words[j].length() > ret) {
                    if ((chart[i] & chart[j]) == 0) {
                        ret = words[i].length() * words[j].length();
                        // System.out.println(words[i]);
                        // System.out.println(words[j]);
                    }
                }
            }
        }
        return ret;
    }
}
/*
  无语了嗷，题解给的这种优化，也不行啊
  这个方法我看到之后就觉得提升有限，效果完全看数据集
  再加上map的实际效率真的很差
  有这样的表现我也是有预期的，所以真的很好奇，快的到底是怎么写的
  执行用时：33 ms, 在所有 Java 提交中击败了31.99%的用户
*/
class Solution {
    public int maxProduct(String[] words) {
        // Arrays.sort(words, (s1, s2) -> s1.length() - s2.length());
        int[] chart = new int[words.length];
        Map<Integer, Integer> map = new HashMap<>(words.length);
        for (int i = 0; i < words.length; i++) {
            int cur = 0;
            for (int j = 0; j < words[i].length(); j++) {
                cur |= 1 << (words[i].charAt(j) - 'a');
            }
            int len = map.getOrDefault(cur, 0);
            if (words[i].length() > len) {
                map.put(cur, words[i].length());
            }
        }
        int ret = 0;
        List<Integer> list = new ArrayList<>(map.keySet());
        for (int i = 0; i < list.size(); i++) {
            int a = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                int b = list.get(j);
                int tmp = map.get(a) * map.get(b);
                if (tmp > ret) {
                    if ((a & b) == 0) {
                        ret = tmp;
                    }
                }
            }
        }
        return ret;
    }
}
/*
  大道至简不过如此吧
  我之前先判断乘积在去比较的策略
  因为之前的比较需要一个很大的开销，比算乘积要大
  而现在的比较只是一个&运算，比乘积小的多了，所以这个屏障要互换了
  执行用时：6 ms, 在所有 Java 提交中击败了99.13%的用户
*/
class Solution {
    public int maxProduct(String[] words) {
        int[] chart = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                chart[i] |= 1 << (words[i].charAt(j) - 'a');
            }
        }
        int ret = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((chart[i] & chart[j]) == 0) {
                    ret = Math.max(ret, words[i].length() * words[j].length());
                }
            }
        }
        return ret;
    }
}
