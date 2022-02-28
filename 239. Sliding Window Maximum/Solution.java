// 239. 滑动窗口最大值
/*
  超时版，为什么没做出来呢
  因为PriorityQueue的remove操作，首先要找到target，然后再remove
  找target这一步，是O(k)
  所以整个算法是O(nk)
  这里的误区就是，remove操作确实是O(logk)，但是得先做查找。。。
  思路就是用堆来维护滑动窗口部分的最大值
  问题就在于，怎样删除已经不在窗口的数
  显然我用了一种错误的维护和删除方法
*/
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ret = new int[nums.length - k + 1];
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < nums.length; i++) {
            if (queue.size() >= k) {
                ret[i - k] = queue.peek();
                queue.remove(nums[i - k]);
            }
            queue.add(nums[i]);
        }
        ret[ret.length - 1] = queue.peek();
        return ret;
    }
}
/*
  堆不行，咱还有红黑树
  上次见到一个堆的题，用TreeMap做，天秀
  TreeMap是天然的平衡二叉搜索树，但是不允许元素重复，于是就用value来记录该元素出现的次数
  跟上一个方法的思路完全一样，但是时间复杂度上有所差异
  这一次，寻找最大值要O(logk)
  remove需要O(logk)
  添加也要O(logk)
  所以整个算法是O(5nlogk)
  所以能勉强通过
  由于对TreeMap做的所有操作都需要O(logk)，不像之前HashMap的O(1)所以对于重复的查询操作极其敏感
  getOrDefalut的形式可以做到只查询两次，并且代码行数也更少，以后就用这种方法
  另外，还是由于查询开销，bst.lastKey()这里可以缓存起来，不过影响不大，再怎么优化也是O(nlogk)，人家题解有O(n)，就不费事儿去实现了
  执行用时：501 ms, 在所有 Java 提交中击败了5.01%的用户
*/
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ret = new int[nums.length - k + 1];
        TreeMap<Integer, Integer> bst = new TreeMap<>();
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count >= k) {
                ret[i - k] = bst.lastKey();
                int tmp = bst.get(nums[i - k]);
                if (tmp == 1) {
                    bst.remove(nums[i - k]);
                } else {
                    bst.put(nums[i - k], tmp - 1);
                }
                count--;
            }
            int tmp = bst.getOrDefault(nums[i], 0);
            bst.put(nums[i], tmp + 1);
            count++;
        }
        ret[ret.length - 1] = bst.lastKey();
        return ret;
    }
}
/*
  还想了一种同样很菜的方案，正常人都能想得到的
  缺点是不太稳定
  就是顺序遍历，然后保存最大值所在位置
  只要最大值没有滑出窗口，那就是O(n)
  如果滑出窗口，就需要O(k)来重新找到最大值
  O(n / k * k) ~ O(nk)
  因为同样太菜了，就不实现了
*/
/*
  O(n)的这个方法，可谓是精妙
  维护了一个精心构思的队列，队列里存储索引
  循环开始时要判断队头是否已经超出滑动窗口，超出就弹出
  每次新加入一个数，跟队尾比较
  如果队尾小，就弹出，直到队尾大于新数，或者队列为空（一会儿会解释为什么弹出）
  然后将新数添加进去
  滑动窗口的最大值就是队头元素
  为什么可以将比新数小的数弹出呢
  因为只要新数还存在，前面那些小的就不可能当最大值，所以直接不管它了
  这个叫，单调队列，妙！
  可以说是这种最大最小值题目里第二个我接触到的牛逼方法了
  第一是那个正向反向找最大最小值的数组
  那个也是O(n)
  执行用时：28 ms, 在所有 Java 提交中击败了90.00%的用户
*/
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ret = new int[nums.length - k + 1];
        LinkedList<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (!deque.isEmpty() && deque.peek() <= i - k) {
                deque.poll();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.add(i);
            if (i - k + 1 >= 0) {
                ret[i - k + 1] = nums[deque.peek()];
            }
        }
        return ret;
    }
}
