//225. 用队列实现栈
/*
  没什么好讲的
  执行用时：0 ms 已经战胜 100.00 % 的 java 提交记录
*/
class MyStack {
    private Queue<Integer> container;
    /** Initialize your data structure here. */
    public MyStack() {
        container = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        container.offer(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int element;
        Queue<Integer> tmp = new LinkedList<>();
        while (container.size() > 1) {
            tmp.offer(container.poll());
        }
        element = container.poll();
        container = tmp;
        return element;
    }
    
    /** Get the top element. */
    public int top() {
        int element;
        Queue<Integer> tmp = new LinkedList<>();
        while (container.size() > 1) {
            tmp.offer(container.poll());
        }
        element = container.poll();
        container = tmp;
        container.offer(element);
        return element;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return container.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */