// 227. 基本计算器 II
/*
  用递归的方法解决优先级问题
  每个运算符后面的一部分，交给递归来解决
  但是这样是错的。。。
  1 - 1 + 1，我这种方法就算不对
  所以，这种方法并不能很好的解决优先级的问题
  WA
*/
class Solution {
    public int calculate(String s) {
        int num1 = 0;
        int num2;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur == ' ') {
                continue;
            }
            if (cur == '+' || cur == '-' || cur == '*' || cur == '/') {
                num2 = calculate(s.substring(i + 1));
                switch (cur) {
                    case '+':
                        return num1 + num2;
                    case '-':
                        return num1 - num2;
                    case '*':
                        return num1 * num2;
                    case '/':
                        return num1 / num2;
                }
            }
            num1 *= 10;
            num1 += cur - '0';
        }
        return num1;
    }
}
/*
  唉，写的一团糟
  逐个字符去读，在遇到乘除法的时候，没有办法及时知道后面是数是什么
  只能在适当的时候，去看之前存的操作符是啥
  导致代码写的非常的乱
  就改成了一次性获取下一个数
  这样写起来就简洁的多了
  题解就是一位一位读的，但是它巧妙的地方在于，记录的是前一个操作符，也能解决我一开始不知道后面的数是什么的问题
  很棒，但是不实现了，我的也不错
  执行用时：9 ms, 在所有 Java 提交中击败了89.92%的用户
*/
class Solution {
    int curIndex = 0;
    String s;
    public int calculate(String s) {
        this.s = s;
        LinkedList<Integer> deque = new LinkedList<>();
        deque.offer(getNum());
        while (curIndex < s.length()) {
            char op = s.charAt(curIndex++);
            switch (op) {
                case '+':
                    deque.offer(getNum());
                    break;
                case '-':
                    deque.offer(-getNum());
                    break;
                case '*':
                    deque.offer(deque.pollLast() * getNum());
                    break;
                case '/':
                    deque.offer(deque.pollLast() / getNum());
            }
        }
        int ret = 0;
        while (!deque.isEmpty()) {
            ret += deque.poll();
        }
        return ret;
    }

    public int getNum() {
        int ret = 0;
        while (curIndex < s.length()) {
            char cur = s.charAt(curIndex);
            if (cur == ' ') {
                curIndex++;
                continue;
            }
            if (isOp(cur)) {
                break;
            }
            ret *= 10;
            ret += cur - '0';
            curIndex++;
        }
        // System.out.println(ret);
        return ret;
    }

    public boolean isOp(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}
