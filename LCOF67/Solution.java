// 剑指 Offer 67. 把字符串转换成整数
/*
  同主站8题
  执行用时：2 ms, 在所有 Java 提交中击败了14.86%的用户
*/
class Solution {
    public int strToInt(String str) {
        String s = str;
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
