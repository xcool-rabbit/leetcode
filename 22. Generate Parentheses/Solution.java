// 22. 括号生成
/*
  递归，n的答案是由n - 1添加而来的
  具体的方法就是在每个位置都尝试添加，看看有没有重复
  显然重复的情况占了很多，所以这种方法慢
  执行用时：4 ms, 在所有 Java 提交中击败了15.64%的用户
*/
class Solution {
    public List<String> generateParenthesis(int n) {
        if (n == 1) {
            List<String> ret = new ArrayList<>(1);
            ret.add("()");
            return ret;
        }
        List<String> prev = generateParenthesis(n - 1);
        Set<String> set = new HashSet<>();
        for (String s : prev) {
            StringBuilder sb = new StringBuilder(s);
            for (int i = 0; i < sb.length(); i++) {
                sb.insert(i, "()");
                if (!set.contains(sb.toString())) {
                    set.add(sb.toString());
                }
                sb = new StringBuilder(s);
            }
        }
        return new ArrayList<>(set);
    }
}
/*
  回溯剪枝！
  剪枝就是，擦屁股，把刚才递归加的东西，再恢复回去
  因为后面还有其他方向的递归
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    List<String> ret;
    int n;
    public List<String> generateParenthesis(int n) {
        ret = new ArrayList<>();
        this.n = n;
        recursion(new StringBuilder(), n, 0);
        return ret;
    }

    public void recursion(StringBuilder sb, int left, int right) {
        if (left == 0 && right == 0) {
            ret.add(sb.toString());
            return;
        }
        if (left > 0) {
            sb.append('(');
            recursion(sb, left - 1, right + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (right > 0) {
            sb.append(')');
            recursion(sb, left, right - 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
