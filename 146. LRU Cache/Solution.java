// 146. LRU 缓存
/*
  这种数据结构的设计题，是真的妙啊
  要求O(1)，用map都想得到
  但是借助已有的数据结构，是做不到维护LRU的
  自己实现的双链表才能满足需求
  维护双链表使得头是最新的，尾是最旧的
  map存储的不是kv，value是DNode的引用
  知道这两点以后，剩下的就自己实现就行了，没什么难度
  难的地方就是想到这样一种维护机制
  另外有一个小技巧就是，以后如果遇到了涉及到更改头的
  就用dummyHead，省去很多的判断
  执行用时：44 ms, 在所有 Java 提交中击败了71.59%的用户
*/
class LRUCache {
    class DNode {
        public int key;
        public int value;
        public DNode prev;
        public DNode next;

        public DNode() {}

        public DNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public Map<Integer, DNode> map;
    public DNode dummyHead, dummyTail;
    public int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        dummyHead = new DNode();
        dummyTail = new DNode();
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            DNode cur = map.get(key);
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;
            cur.next = dummyHead.next;
            cur.prev = dummyHead;
            dummyHead.next = cur;
            cur.next.prev = cur;
            return cur.value;
        } else {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            get(key);
            map.get(key).value = value;
        } else {
            DNode cur = new DNode(key, value);
            cur.next = dummyHead.next;
            cur.prev = dummyHead;
            dummyHead.next = cur;
            cur.next.prev = cur;
            map.put(key, cur);
        }
        if (map.size() > capacity) {
            map.remove(dummyTail.prev.key);
            DNode removed = dummyTail.prev;
            removed.prev.next = dummyTail;
            dummyTail.prev = removed.prev;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
/*
  用LinkedHashMap实现
  LinkedHashMap是在HashMap的基础上，将Entry用链表连起来，解决了HashMap无序的问题（丢失添加时的顺序）
  基于这个特性，加上LRU算法的广泛用途，LinkedHashMap补充实现了LRU算法
  只需要初始化时将accessOrder置为true，然后重写removeEldestEntry，实现对Least Recent的元素的删除
  实现方式基本上都是继承LinkedHashMap，这里需要注意泛型
  执行用时：38 ms, 在所有 Java 提交中击败了99.36%的用户
*/
class LRUCache extends LinkedHashMap<Integer, Integer> {
    public int capacity;
    public LRUCache(int capacity) {
        super((int)(capacity / 0.75 + 1), 0.75f, true);
        this.capacity = capacity;
    }
    
    public int get(int key) {
        return super.getOrDefault(key, -1);
    }
    
    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> entry) {
        if (this.size() > this.capacity) {
            return true;
        }
        return false;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
