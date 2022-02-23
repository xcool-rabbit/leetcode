// 295. 数据流的中位数
/*
  维护一个有序数组
  插入O(n)，查找O(1)
  执行用时：933 ms, 在所有 Java 提交中击败了5.03%的用户
*/
class MedianFinder {
    List<Integer> list;
    public MedianFinder() {
        list = new ArrayList<>();
    }
    
    public void addNum(int num) {
        int index = 0;
        while (index < list.size()) {
            if (num < list.get(index)) {
                break;
            }
            index++;
        }
        list.add(index, num);
    }
    
    public double findMedian() {
        if (list.size() % 2 == 1) {
            return list.get(list.size() / 2);
        } else {
            return (list.get(list.size() / 2 - 1) + list.get(list.size() / 2)) / 2.0;
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
/*
  数组的改进版，寻找插入位置可以用二分提速
  但是插入的时候，数组会进行复制操作，还是O(n)
  总之效果显著
  执行用时：362 ms, 在所有 Java 提交中击败了5.03%的用户
*/
class MedianFinder {
    List<Integer> list;
    public MedianFinder() {
        list = new ArrayList<>();
    }
    
    public void addNum(int num) {
        list.add(binarySearch(num), num);
    }

    public double findMedian() {
        if (list.size() % 2 == 1) {
            return list.get(list.size() / 2);
        } else {
            return (list.get(list.size() / 2 - 1) + list.get(list.size() / 2)) / 2.0;
        }
    }

    public int binarySearch(int target) {
        if (list.size() == 0) {
            return 0;
        }
        int left = 0;
        int right = list.size() - 1;
        int mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (target >= list.get(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return target >= list.get(left) ? left + 1 : left;
    }
}

/*
  其实我也在纠结，还有什么更好的方法
  我想到了堆天生就维护了一个相对有序的序列
  插入O(logn)，但是查找怎么办呢
  中位数就是top(n / 2)
  堆设置多大呢
  设置n的话，查找O(nlogn)
  设置n / 2的话，不在堆里的n / 2，要能经常返回其最小值
  因为数据流中的数据在不断增长，堆的规模在不断变大
  但是往堆中添加的元素，应该有新增的，也有以前不在堆里的
  看起来好像做不了诶
  然后看了眼题解，用堆能做！
  然后就开始研究怎么实现
  不在堆里的n / 2，也用一个堆维护，就能及时获得这部分的最小值
  分成left和right，left放小数，right放大数
  left是大顶堆，right是小顶堆
  注意维护，保持left的顶比right小
  效果更加显著，但是离正确方法还差了不少
  ！！！题解的方法比我快好多
  原因就在addNum那里，比我操作次数更少
  绝了
  但是我不想改了，就这样吧
  题解还有一种方法，更赖
  TreeMap是红黑树，自动维护一个有序序列
  但是并没有索引相关的接口
  自己记录中位数
  TreeMap的作用就是记录一个数字出现的次数，以及按顺序给出序列中的相邻数
  总之很复杂，很蠢
  执行用时：136 ms, 在所有 Java 提交中击败了13.54%的用户
*/
class MedianFinder {
    Queue<Integer> left;
    Queue<Integer> right;
    public MedianFinder() {
        left = new PriorityQueue<>(Comparator.reverseOrder());
        right = new PriorityQueue<>(Comparator.naturalOrder());
    }
    
    public void addNum(int num) {
        if (left.size() == right.size()) {
            if (right.size() == 0 || num <= right.peek()) {
                left.add(num);
            } else {
                left.add(right.poll());
                right.add(num);
            }
        } else {
            right.add(num);
            if (right.peek() < left.peek()) {
                right.add(left.poll());
                left.add(right.poll());
            }
        }
    }
    
    public double findMedian() {
        int total = left.size() + right.size();
        if (left.size() != right.size()) {
            return left.peek();
        } else {
            return (left.peek() + right.peek()) / 2.0;
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
 