//8. 字符串转整数 (atoi)
/*
  首先用trim()将空白符消除，之后转为数组。看第一位是否为负号，做个标记。
  最烦人的是前面放一堆0的情况。需要将前面的0全都过滤掉。
  利用两个指针，一个头，一个尾，将有效部分囊括起来，提取出子字符串。
  为了防止溢出，需要对子字符串的长度进行限制，超过10位数的（Integer.MAX_VALUE是10位），直接根据正负号返回MAX_VALUE或者MIN_VALUE。
  正常的情况就通过parseLong来转换（因为10位数的还是有超int范围的数）
  执行用时：30 ms 已经战胜 94.36 % 的 java 提交记录
*/
class Solution {
    public int myAtoi(String str) {
        int begin = 1;
        int end = 1;
        boolean negative = false;
        
        str = str.trim();
        
        char[] a = str.toCharArray();
        if (a.length == 0)
            return 0;
        if (a.length == 1) {
            if (Character.isDigit(a[0]))
                return Integer.parseInt(str);
            else
                return 0;
        }
        
        if (Character.isDigit(a[0]) || a[0] == '-' || a[0] == '+') {
            if (a[0] == '-')
                negative = true;
            if (Character.isDigit(a[0]))
                begin = 0;
            //前面一堆0
            if (str.startsWith("00") || str.startsWith("+00") || str.startsWith("-00")) {
                while (begin < a.length && a[begin] == '0')
                    begin++;
            }
            end = begin;
            while (end < a.length && Character.isDigit(a[end]))
                end++;
            str = str.substring(begin, end); //正负号和前导0已经被去掉了
            
            if (str.length() == 0)
                return 0;
            
            if (str.length() > 10) { //INT_MAX有10位
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            else {
                long res = Long.parseLong(str);
                if (res > Integer.MAX_VALUE)
                    return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                else {
                    return negative ? -(int)res : (int)res;
                }
            }
        }
        else {
            return 0;
        }
    }
}
/*
  复杂的字符串问题的正解！
  依旧是有限状态自动机
  但是这次不需要推什么数字逻辑，用字符串就可以
  列个表，一种状态经过字符，转换成另一种状态
  这个做法并不是最快的，因为最快的方法用的if-else
  我想这里用字符串表示状态，后面还需要用equals来判断，确实会笨了些
  执行用时：2 ms, 在所有 Java 提交中击败了30.08%的用户
*/
class Solution {
    public int myAtoi(String s) {
        s = s.trim();
        Automation automation = new Automation();
        for (int i = 0; i < s.length(); i++) {
            automation.get(s.charAt(i));
        }
        return automation.sign * (int)automation.ans;
    }

    class Automation {
        public int sign = 1;
        public long ans = 0;
        private String state = "start";
        private Map<String, String[]> table = new HashMap<>();

        public Automation() {
            table.put("start", new String[]{"start", "signed", "number", "end"});
            table.put("signed", new String[]{"end", "end", "number", "end"});
            table.put("number", new String[]{"end", "end", "number", "end"});
            table.put("end", new String[]{"end", "end", "end", "end"});
        }

        public void get(char c) {
            state = table.get(state)[getCol(c)];
            if (state.equals("number")) {
                ans *= 10;
                ans += c - '0';
                if (sign == 1) {
                    if (ans > Integer.MAX_VALUE) {
                        ans = Integer.MAX_VALUE;
                    }
                } else if (sign == -1) {
                    if (ans > (long)Integer.MAX_VALUE + 1) {
                        ans = (long)Integer.MAX_VALUE + 1;
                    }
                }
            } else if (state.equals("signed")) {
                if (c == '-') {
                    sign = -1;
                }
            }
        }

        public int getCol(char c) {
            if (c == ' ') {
                return 0;
            } else if (c == '+' || c == '-') {
                return 1;
            } else if (Character.isDigit(c)) {
                return 2;
            } else {
                return 3;
            }
        }
    }
}
/*
  想着用枚举类来避免字符串的呆呆的equals
  但是好像没啥用
  不过平常不怎么用枚举，这里用了也算熟悉一下
  执行用时：2 ms, 在所有 Java 提交中击败了30.08%的用户
*/
class Solution {
    public int myAtoi(String s) {
        s = s.trim();
        Automation automation = new Automation();
        for (int i = 0; i < s.length(); i++) {
            automation.get(s.charAt(i));
        }
        return automation.sign * (int)automation.ans;
    }

    class Automation {
        enum State {
            START, SIGN, NUMBER, END;
        }
        public int sign = 1;
        public long ans = 0;
        private State state = State.START;
        private Map<State, State[]> table = new HashMap<>();

        public Automation() {
            table.put(State.START, new State[]{State.START, State.SIGN, State.NUMBER, State.END});
            table.put(State.SIGN, new State[]{State.END, State.END, State.NUMBER, State.END});
            table.put(State.NUMBER, new State[]{State.END, State.END, State.NUMBER, State.END});
            table.put(State.END, new State[]{State.END, State.END, State.END, State.END});
        }

        public void get(char c) {
            state = table.get(state)[getCol(c)];
            if (state == State.NUMBER) {
                ans *= 10;
                ans += c - '0';
                if (sign == 1) {
                    if (ans > Integer.MAX_VALUE) {
                        ans = Integer.MAX_VALUE;
                    }
                } else if (sign == -1) {
                    if (ans > (long)Integer.MAX_VALUE + 1) {
                        ans = (long)Integer.MAX_VALUE + 1;
                    }
                }
            } else if (state == State.SIGN) {
                if (c == '-') {
                    sign = -1;
                }
            }
        }

        public int getCol(char c) {
            if (c == ' ') {
                return 0;
            } else if (c == '+' || c == '-') {
                return 1;
            } else if (Character.isDigit(c)) {
                return 2;
            } else {
                return 3;
            }
        }
    }
}
