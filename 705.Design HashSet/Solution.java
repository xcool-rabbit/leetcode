//705. 设计哈希集合
/*
  注意添加的移除的时候判断一下是否存在
  关于桶大小的设计，如果按照jdk的标准来的话是有扩容机制的，大于0.75的桶被占有了就会触发扩容机制
  我设计成10000，是因为测试集最多有10000个操作，10000个的话平均下来是不会出现哈希碰撞的
  执行用时：19 ms 已经战胜 83.50 % 的 java 提交记录
*/
class MyHashSet {
    LinkedList[] bucket;
    /** Initialize your data structure here. */
    public MyHashSet() {
        bucket = new LinkedList[10000];
    }
    
    public void add(int key) {
        int tmp = key % 10000;
        if (bucket[tmp] == null) {
            bucket[tmp] = new LinkedList<Integer>();
        }
        if (!bucket[tmp].contains(key)) {
            bucket[tmp].add(key);
        }
    }
    
    public void remove(int key) {
        if (contains(key)) {
            int tmp = key % 10000;
            bucket[tmp].remove((Integer)key);
        }
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int tmp = key % 10000;
        if (bucket[tmp] == null) {
            return false;
        } else {
            return bucket[tmp].contains(key);
        }
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
 