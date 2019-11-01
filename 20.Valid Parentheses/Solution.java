//20. 有效的括号
/*
  用栈解决，科班学生的经典问题
  我第一次见这道题是在大一的C语言书上，很惊叹
  执行用时：3 ms 已经战胜 83.74 % 的 java 提交记录
*/
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char tmp = s.charAt(i);
            switch (tmp) {
                case '(':
                case '[':
                case '{':
                    stack.push(tmp);
                    break;
                case ')':
                    if (!stack.empty() && stack.peek() == '(') {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
                case ']':
                    if (!stack.empty() && stack.peek() == '[') {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
                case '}':
                    if (!stack.empty() && stack.peek() == '{') {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
            }
        }
        return stack.empty();
    }
}
