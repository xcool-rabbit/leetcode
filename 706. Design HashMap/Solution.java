//706. 设计哈希映射
/*
  桶是一个链表数组，链表里面放的是Node
  执行用时：22 ms 已经战胜 85.78 % 的 java 提交记录
*/
class MyHashMap {
    class Node {
        int key;
        int value;
        
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    LinkedList[] bucket;
    /** Initialize your data structure here. */
    public MyHashMap() {
        bucket = new LinkedList[10000];
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int tmp = key % 10000;
        if (bucket[tmp] == null) {
            bucket[tmp] = new LinkedList<Node>();
        }
        boolean isExist = false;
        for (Object o : bucket[tmp]) {
            Node n = (Node)o;
            if (n.key == key) {
                n.value = value;
                isExist = true;
            }
        }
        if (!isExist) {
            bucket[tmp].add(new Node(key, value));
        }
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int tmp = key % 10000;
        if (bucket[tmp] == null) {
            return -1;
        }
        for (Object o : bucket[tmp]) {
            Node n = (Node)o;
            if (n.key == key) {
                return n.value;
            }
        }
        return -1;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int tmp = key % 10000;
        if (bucket[tmp] == null) {
            return;
        }
        Iterator<Node> it = bucket[tmp].iterator();
        while (it.hasNext()) {
            Node n = it.next();
            if (n.key == key) {
                it.remove();
                return;
            }
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */