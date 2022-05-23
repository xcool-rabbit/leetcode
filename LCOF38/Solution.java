// 剑指 Offer 38. 字符串的排列
/*
  这个题，和重排列，我都是用递归的方法做的
  基于这个，进行优化
  如果重复字符较多，每一层我们都应该进行Set的去重
  执行用时：32 ms, 在所有 Java 提交中击败了27.20%的用户
*/
class Solution {
    public String[] permutation(String s) {
        Set<String> ret = new HashSet<>();
        Deque<StringBuilder> queue = new LinkedList();
        queue.offerLast(new StringBuilder());
        for (int i = 0; i < s.length(); i++) {
            while (queue.peekFirst().length() < i + 1) {
                StringBuilder sb = queue.pollFirst();
                for (int j = 0; j <= sb.length(); j++) {
                    StringBuilder tmp = new StringBuilder(sb);
                    tmp.insert(j, s.charAt(i));
                    queue.offerLast(tmp);
                }
            }
        }
        for (StringBuilder sb : queue) {
            ret.add(sb.toString());
        }
        String[] arr = new String[ret.size()];
        return ret.toArray(arr);
    }
}
/*
  每一层都做去重
  但是还是这么慢，原因出在，我这个方法，去重，只能借助set
  实际上有更好的去重方法，但是要回溯
  首先，脑子里完全抛弃这种一层一层的，队列式的，递归想法
  只看结果
  想要得到结果，可以认为是在对一个长度为n的字符串进行填空
  这样就是回溯的思想
  优点之一：这样只会生成n!个对象，而我那个方法，生成了1!到n!之和个对象
  第二个好处就是，回溯的去重，非常的简单
  首先对字符串排序，这一步对于阶乘级别的复杂度来讲，不算什么
  在填空过程中，保证每次填入的字符，是第一个不重复的，就可以避免重复的情况发生
  举例：aab，aba
  这时候回溯到第一个空，应该填入第二个a
  但是我们要遵循刚才的规定，填入的是第一个不重复的
  那么就会避免用第二个a再填一次一模一样的
  执行用时：25 ms, 在所有 Java 提交中击败了34.47%的用户
*/
class Solution {
    public String[] permutation(String s) {
        Deque<String> queue = new LinkedList();
        queue.offerLast("");
        for (int i = 0; i < s.length(); i++) {
            Set<String> curLevel = new HashSet<>();
            while (queue.peekFirst().length() < i + 1) {
                String sb = queue.pollFirst();
                for (int j = 0; j <= sb.length(); j++) {
                    StringBuilder tmp = new StringBuilder(sb);
                    tmp.insert(j, s.charAt(i));
                    if (!curLevel.contains(tmp.toString())) {
                        queue.offerLast(tmp.toString());
                        curLevel.add(tmp.toString());
                    }
                }
            }
        }
        String[] arr = new String[queue.size()];
        return queue.toArray(arr);
    }
}
/*
  执行用时：7 ms, 在所有 Java 提交中击败了89.83%的用户
*/
class Solution {
    List<String> ret;
    public String[] permutation(String s) {
        char[] cs = s.toCharArray();
        Arrays.sort(cs);

        ret = new ArrayList<>();
        boolean[] used = new boolean[cs.length];
        StringBuilder sb = new StringBuilder();
        recursion(cs, used, sb);
        String[] tmp = new String[ret.size()];
        return ret.toArray(tmp);
    }

    public void recursion(char[] cs, boolean[] used, StringBuilder sb) {
        if (sb.length() == cs.length) {
            ret.add(sb.toString());
        }
        for (int i = 0; i < cs.length; i++) {
            if (!used[i]) {
                if (i == 0 || !(!used[i - 1] && cs[i] == cs[i - 1])) {
                    sb.append(cs[i]);
                    used[i] = true;
                    recursion(cs, used, sb);
                    sb.deleteCharAt(sb.length() - 1);    
                    used[i] = false;
                }
            }
        }
    }
}
