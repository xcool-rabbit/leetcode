//380. 常数时间插入、删除和获取随机元素
/*
  用HashSet实现，随机的那块儿用迭代器的话，重复调用并不随机
  性能这么差，一定是代码写的矬
  执行用时：341 ms 已经战胜 5.46 % 的 java 提交记录
*/
class RandomizedSet {
    Set<Integer> set;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        set = new HashSet<>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        return set.add(val);
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        return set.remove(val);
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        Integer[] a = new Integer[set.size()];
        set.toArray(a);
        return a[new Random().nextInt(a.length)];
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
 /*
  测试集里面有很多的getRandom()调用，而以前的代码每次都要toArray()和new Random()，开销较大
  可以用一个数组来保存toArray()的值
  用成员变量来保存random对象
  执行用时：61 ms 已经战胜 18.27 % 的 java 提交记录
*/
class RandomizedSet {
    Set<Integer> set;
    List<Integer> list;
    Random random;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        set = new HashSet<>();
        list = new ArrayList<>();
        random = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        boolean success = set.add(val);
        if (success) {
            list.add(val);
        }
        return success;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        boolean success = set.remove(val);
        if (success) {
            list.remove((Integer) val);
        }
        return success;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
/*
  测试集里面有很多的getRandom()调用，而以前的代码每次都要toArray()和new Random()，开销较大
  因为数组的remove()是O(N)，所以不能让数组时刻保持最新
  而是用一个标志记录是否需要更新数组
  每次getRandom()的时候生成数组，如果没有更新那就可以直接返回，略微节省时间
  这样反而更慢了（吐血......
  执行用时：97 ms 已经战胜 15.90 % 的 java 提交记录
*/
class RandomizedSet {
    Set<Integer> set;
    Integer[] a;
    boolean modified;
    Random random;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        set = new HashSet<>();
        modified = false;
        random = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        modified = true;
        return set.add(val);
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        modified = true;
        return set.remove(val);
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        if (modified) {
            a = new Integer[set.size()];
            set.toArray(a);
            modified = false;
        }
        return a[random.nextInt(a.length)];
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
/*
  正道的光！
  别以为这是一道很无聊的题！真的存在这样的设计！
  核心是HashSet没有问题
  为了解决getRandom()的问题引入list
  但是list的remove又有问题
  如何让remove可以O(1)呢
  把HashSet改成HashMap，value存储索引位置
  这样可以O(1)找到待删除的元素
  但是list删除元素，后面的会补上来，那怎么办呢
  那就是，删除最后一个元素！
  把待删除的和最后一个换一下，就能保证O(1)的删除了
  哈哈哈哈哈真的是太精妙了
  执行用时：13 ms 已经战胜 66.97 % 的 java 提交记录
*/
class RandomizedSet {
    Map<Integer, Integer> map;
    List<Integer> list;
    Random random;
    
    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        boolean exist = map.containsKey(val);
        if (exist) {
            return false;
        }
        map.put(val, list.size());
        list.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        boolean success = map.containsKey(val);
        if (!success) {
            return success;
        }
        int index = map.get(val);
        if (index != list.size() - 1) {
            int tmp = list.get(list.size() - 1);
            list.set(list.size() - 1, list.get(index));
            list.set(index, tmp);
            list.remove(list.size() - 1);
            map.remove(val);
            map.put(list.get(index), index);
        } else {
            list.remove(index);
            map.remove(val);
        }
        return success;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */