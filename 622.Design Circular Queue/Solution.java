//622. 设计循环队列
/*
  用数组实现，front，rear两个指针。
  front和rear两个指针所指的空间是有的还是没有的。
  我由于设计问题，实现的队列里面需要额外一个变量来指示队列是否为空。
  看到别人的优化，将front和rear两个指针初始值设成-1，用来代表队列是空的。
  如果deQueue()之后队列空了，就将front和rear重新置为-1。
  执行用时：98 ms 已经战胜 85.21 % 的 java 提交记录
*/

class MyCircularQueue {
    private int[] data;
    private int front;
    private int rear;
    private int size;
    boolean isExist;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        data = new int[k];
        front = 0;
        rear = 0;
        size = k;
        isExist = false;
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        if (!isExist) {
            isExist  =true;
        }
        data[rear] = value;
        rear = (rear + 1) % size;
        return true;
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % size;
        if (front == rear) {
            isExist = false;
        }
        return true;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return data[front];
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return data[(rear - 1 + size) % size];
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return !isExist;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        if (isExist) {
            return rear == front;
        }
        return false;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */

