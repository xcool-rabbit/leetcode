//232. 用栈实现队列
/*
  属实没什么好讲的
  执行用时：0 ms 已经战胜 100.00 % 的 java 提交记录
*/
class MyQueue {
    private Stack<Integer> container;
    /** Initialize your data structure here. */
    public MyQueue() {
        container = new Stack<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        container.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        int front;
        Stack<Integer> tmp = new Stack<>();
        while (!container.empty()) {
            tmp.push(container.pop());
        }
        front = tmp.pop();
        while (!tmp.empty()) {
            container.push(tmp.pop());
        }
        return front;
    }
    
    /** Get the front element. */
    public int peek() {
        int front;
        Stack<Integer> tmp = new Stack<>();
        while (!container.empty()) {
            tmp.push(container.pop());
        }
        front = tmp.peek();
        while (!tmp.empty()) {
            container.push(tmp.pop());
        }
        return front;
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return container.empty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */