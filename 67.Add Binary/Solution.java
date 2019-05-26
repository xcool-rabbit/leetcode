//67. 二进制求和
/*
  题目很简单，倒着一位一位的加，有进位的标记一下，注意最后答案可能会比两个数都长1位，所以不要忘记循环退出之后的进位。
  但是很诡异的是，我用时超长，看了一下，不是思维上的问题。别人做这道题用的charAt，倒着遍历。我用的入栈。性能上有一些差距，思路上是没问题的，所以不打算改了，没意义。
  执行用时：15 ms 已经战胜 7.39 % 的 java 提交记录
*/
import java.util.Stack;

class Solution {
    public String addBinary(String a, String b) {
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();
        Stack<Integer> aStack = new Stack<>();
        for (char c : aChars) {
            aStack.push(Integer.parseInt(String.valueOf(c)));
        }
        Stack<Integer> bStack = new Stack<>();
        for (char c : bChars) {
            bStack.push(Integer.parseInt(String.valueOf(c)));
        }
        Stack<Integer> answer = new Stack<>();
        Boolean forward = false;
        int sum = 0;
        while (!(aStack.empty() && bStack.empty())) {
            int aPop = 0;
            int bPop = 0;
            sum = forward ? 1 : 0;
            if (!aStack.empty()) {
                aPop = aStack.pop();
            }
            if (!bStack.empty()) {
                bPop = bStack.pop();
            }
            sum = sum + aPop + bPop;
            forward = sum > 1;
            answer.push(sum % 2);
        }
        if (forward) {
            answer.push(1);
        }
        StringBuffer answerStr = new StringBuffer();
        while (!answer.empty()) {
            answerStr.append(answer.pop());
        }

        return answerStr.toString();
    }
}
