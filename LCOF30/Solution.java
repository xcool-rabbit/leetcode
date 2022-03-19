// 剑指 Offer 30. 包含min函数的栈 LCOF
/*
  小细节pop的那个equals，今天被它摆了一道
  不能用==，因为测试数据里面有超出缓存的，必须用equals
  执行用时：13 ms, 在所有 Java 提交中击败了80.14%的用户
*/
class MinStack {
    public LinkedList<Integer> stack;
    public LinkedList<Integer> minStack;
    /** initialize your data structure here. */
    public MinStack() {
        stack = new LinkedList<>();
        minStack = new LinkedList<>();
    }
    
    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }
    
    public void pop() {
        if (stack.pop().equals(minStack.peek())) {
            minStack.pop();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int min() {
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */