// 946. 验证栈序列
/*
  同LCOF31
  执行用时：1 ms, 在所有 Java 提交中击败了93.34%的用户
*/
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new LinkedList<>();
        int pushCursor = 0;
        int popCursor = 0;
        while (popCursor < popped.length) {
            if (stack.isEmpty() || stack.peek() != popped[popCursor]) {
                if (pushCursor >= pushed.length) {
                    return false;
                }
                stack.push(pushed[pushCursor++]);
            } else {
                stack.pop();
                popCursor++;
            }
        }
        return true;
    }
}
