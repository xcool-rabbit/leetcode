//155. 最小栈
/*
  ArrayList实现栈。
  问题在于如何能在常数时间内返回最小值。思路是在push()，pop()的时候就能够更新最小值。
  review的时候发现没有必要把minIndex实时更新记录，很鸡肋。
  执行用时：100 ms 已经战胜 87.10 % 的 java 提交记录
*/

class MinStack {
    private List<Integer> data;
    private int minIndex;
    private int minValue;
    /** initialize your data structure here. */
    public MinStack() {
        data = new ArrayList<>();
        minIndex = -1;
    }
    
    public void push(int x) {
        data.add(x);
        if (minIndex == -1) {
            minIndex = 0;
            minValue = x;
        } else if (x < minValue) {
            minIndex = data.size() - 1;
            minValue = x;
        }
    }
    
    public void pop() {
        if (minIndex == data.size() - 1) {
            minValue = data.get(0);
            for (int i = 0; i < data.size() - 1; i++) {
                if (data.get(i) < minValue) {
                    minValue = data.get(i);
                    minIndex = i;
                }
            }
        }
        data.remove(data.size() - 1);
        if (data.size() == 0) {
            minIndex = -1;
        }
    }
    
    public int top() {
        return data.get(data.size() - 1);
    }
    
    public int getMin() {
        return minValue;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
