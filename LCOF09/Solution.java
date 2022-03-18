// 剑指 Offer 09. 用两个栈实现队列
/*
  当需要取队头的时候，把其中一个栈全都弹出，放到另一个里面
  那么另一个以栈的方式取用，就是FIFO
  执行用时：36 ms, 在所有 Java 提交中击败了93.32%的用户
*/
class CQueue {
    LinkedList<Integer> FILO;
    LinkedList<Integer> FIFO;
    public CQueue() {
        FILO = new LinkedList<>();
        FIFO = new LinkedList<>();
    }
    
    public void appendTail(int value) {
        FILO.push(value);
    }
    
    public int deleteHead() {
        if (FIFO.isEmpty()) {
            while (!FILO.isEmpty()) {
                FIFO.push(FILO.pop());
            }
        }
        return FIFO.isEmpty() ? -1 : FIFO.pop();
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
