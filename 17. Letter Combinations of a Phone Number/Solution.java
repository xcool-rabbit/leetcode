// 17. 电话号码的字母组合
/*
  枚举全排列的问题
  dfs就能遍历
  遇到的问题是dfs的时候，引用传递，导致字符串很长
  然后我想，应该每一次调用的时候都得new一个新的，因为最后的答案也有很多项
  但是好像很慢
  执行用时：6 ms, 在所有 Java 提交中击败了22%的用户
*/
class Solution {
    public Map<Character, char[]> keymap;
    String digits;
    List<String> result;

    public List<String> letterCombinations(String digits) {
        keymap = new HashMap<>(8);
        this.digits = digits;
        result = new ArrayList<>();
        if (digits.length() == 0) {
            return result;
        }
        keymap.put('2', new char[]{'a', 'b', 'c'});
        keymap.put('3', new char[]{'d', 'e', 'f'});
        keymap.put('4', new char[]{'g', 'h', 'i'});
        keymap.put('5', new char[]{'j', 'k', 'l'});
        keymap.put('6', new char[]{'m', 'n', 'o'});
        keymap.put('7', new char[]{'p', 'q', 'r', 's'});
        keymap.put('8', new char[]{'t', 'u', 'v'});
        keymap.put('9', new char[]{'w', 'x', 'y', 'z'});
        dfs(0, "");
        return result;
    }

    public void dfs(int index, String prefix) {
        if (!(index < digits.length())) {
            result.add(prefix);
            return;
        }
        char[] letters = keymap.get(digits.charAt(index));
        for (char c : letters) {
            dfs(index + 1, prefix + c);
        }
    }
}
/*
  为什么会这么慢呢，最有可能的问题还是字符串操作
  这种频繁的修改字符串的操作肯定是StringBuilder更好用
  但是碍于逻辑上的问题，上一个题解没有跑通
  借鉴了一下题解，发现添加之后进递归，出来之后删除，就可以了
  或许这就是回溯的意思吧
  执行用时：1 ms, 在所有 Java 提交中击败了83.75%的用户
*/
class Solution {
    public Map<Character, char[]> keymap;
    String digits;
    List<String> result;

    public List<String> letterCombinations(String digits) {
        keymap = new HashMap<>(8);
        this.digits = digits;
        result = new ArrayList<>();
        if (digits.length() == 0) {
            return result;
        }
        keymap.put('2', new char[]{'a', 'b', 'c'});
        keymap.put('3', new char[]{'d', 'e', 'f'});
        keymap.put('4', new char[]{'g', 'h', 'i'});
        keymap.put('5', new char[]{'j', 'k', 'l'});
        keymap.put('6', new char[]{'m', 'n', 'o'});
        keymap.put('7', new char[]{'p', 'q', 'r', 's'});
        keymap.put('8', new char[]{'t', 'u', 'v'});
        keymap.put('9', new char[]{'w', 'x', 'y', 'z'});
        dfs(0, new StringBuilder());
        return result;
    }

    public void dfs(int index, StringBuilder prefix) {
        if (!(index < digits.length())) {
            result.add(prefix.toString());
            return;
        }
        char[] letters = keymap.get(digits.charAt(index));
        for (char c : letters) {
            dfs(index + 1, prefix.append(c));
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }
}
