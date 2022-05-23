// 剑指 Offer 31. 栈的压入、弹出序列
/*
  一开始想复杂了
  利用的性质是，一个数一旦pop出来，那么在它之后push进去的，不可能在它之后pop
  其实也是FILO的思想
  但是这样判断，是O(n ^ 2)
  并且非常的复杂
  后来我才想到，没必要搞一个这么高级的啊，我可是有pop序列的
  跟着模拟就行了
  执行用时：1 ms, 在所有 Java 提交中击败了96.45%的用户
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
