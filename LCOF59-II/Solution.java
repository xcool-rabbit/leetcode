// 剑指 Offer 59 - II. 队列的最大值
/*
  这题让我想起了最小栈
  这样看的话，单调队列就不难理解了
  执行用时：26 ms, 在所有 Java 提交中击败了69.23%的用户
*/
class MaxQueue {
    Deque<Integer> queue;
    Deque<Integer> maxQueue;

    public MaxQueue() {
        queue = new LinkedList<>();
        maxQueue = new LinkedList<>();
    }
    
    public int max_value() {
        if (queue.isEmpty()) {
            return -1;
        }
        return maxQueue.peekFirst();
    }
    
    public void push_back(int value) {
        queue.offer(value);
        while (!maxQueue.isEmpty() && maxQueue.peekLast() < value) {
            maxQueue.pollLast();
        }
        maxQueue.offerLast(value);
    }
    
    public int pop_front() {
        if (queue.isEmpty()) {
            return -1;
        }
        int ret = queue.poll();
        if (ret == maxQueue.peekFirst()) {
            maxQueue.pollFirst();
        }
        return ret;
    }
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */
