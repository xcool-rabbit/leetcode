//703. 数据流中的第K大元素
/*
  topK问题首先想到的就是堆，这个题有很多的边界情况很烦
  执行用时：20 ms 已经战胜 77.13 % 的 java 提交记录
*/
class KthLargest {
    PriorityQueue<Integer> pq;
    int top;
    public KthLargest(int k, int[] nums) {
        pq = new PriorityQueue<>(k);
        top = k;
        for (int n : nums) {
            if (pq.size() < k) {
                pq.offer(n);
            } else if (n > pq.peek()) {
                pq.poll();
                pq.offer(n);
            }
        }
    }
    
    public int add(int val) {
        if (pq.size() < top) {
            pq.offer(val);
        } else if (val > pq.peek()) {
            pq.poll();
            pq.offer(val);
        }
        return pq.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
 /*
   从编码方便的角度上来讲，PriorityQueue实现的堆很方便
   但是这个题是在二叉搜索树分类里的，我觉得有点强行了
*/