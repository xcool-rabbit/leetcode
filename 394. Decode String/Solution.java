//394. 字符串解码
/*
  这种又是符号嵌套又是跟运算相关的，我的直觉是用栈，但是我有些地方没有想明白
  于是就按照人工解码的思路直接做
  做着做着发现中括号内部的东西可以递归
  完全凭借自己，在一个恰到好处的地方实现了一个较为复杂问题的递归，非常开心！
  执行用时：0 ms 已经战胜 100.00 % 的 java 提交记录
*/
class Solution {
    public String decodeString(String s) {
        char[] str = s.toCharArray();
        StringBuilder ans = new StringBuilder();
        int start = 0;
        int end = 0;
        while (end < str.length) {
            while (end < str.length && isLetter(str[end])) {
                end++;
            }
            if (start != end) {
                ans.append(s.substring(start, end));
            }
            if (end >= str.length) {
                return ans.toString();
            }
            int times;
            start = end;
            while (isNumber(str[end])) {
                end++;
            }
            times = Integer.parseInt(s.substring(start, end));
            start = ++end;
            int count = 0;
            while (true) {
                if (str[end] == '[') {
                    count++;
                } else if (str[end] == ']') {
                    if (count != 0) {
                        count--;
                    } else {
                        break;
                    }
                }
                end++;
            }
            String src = decodeString(s.substring(start, end));
            while (times-- != 0) {
                ans.append(src);
            }
            start = ++end;
        }
        return ans.toString();
    }
    
    private boolean isLetter(char c) {
        return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
    }
    
    private boolean isNumber(char c) {
        return '0' <= c && c <= '9';
    }
}
/*
  栈的方法比递归要慢，天然的
  遇到']'出栈，其他的都进栈
  出栈出到'['停，然后再出栈就是倍数
  算好以后入栈
  执行用时：1 ms 已经战胜 90.06 % 的 java 提交记录
*/
class Solution {
    public String decodeString(String s) {
        char[] str = s.toCharArray();
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < str.length; i++) {
            if (isLetter(str[i]) || str[i] == '[') {
                stack.push(String.valueOf(str[i]));
            } else if (isNumber(str[i])) {
                int start = i++;
                while (isNumber(str[i])) {
                    i++;
                }
                stack.push(s.substring(start, i--));
            } else {
                StringBuilder sb = new StringBuilder();
                while (!stack.peek().equals("[")) {
                    sb.insert(0, stack.pop());
                }
                stack.pop();
                String tmp = sb.toString();
                int times = Integer.parseInt(stack.pop());
                while (times-- > 1) {
                    sb.append(tmp);
                }
                stack.push(sb.toString());
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.empty()) {
            sb.insert(0, stack.pop());
        }
        return sb.toString();
    }

    private boolean isLetter(char c) {
        return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
    }
    
    private boolean isNumber(char c) {
        return '0' <= c && c <= '9';
    }
}
