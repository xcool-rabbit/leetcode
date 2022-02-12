// 719. 找出第 k 小的距离对
/*
  解法一
  这题看起来也太简单了，并且做法多多
  没有想到是个hard
  应该是数据集比较变态，垃圾方法过不了
  首先理解上要勘误
  这种做法过不了，说OOM，但是不知道是PriorityQueue的问题还是单纯数据集太大
  改成list然后排序试一下
  O(n ^ 2)
*/
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Queue<Integer> distance = new PriorityQueue<>(nums.length * nums.length / 2);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                distance.add(Math.abs(nums[i] - nums[j]));
            }
        }
        while (k-- > 1) {
            distance.poll();
        }
        return distance.poll();
    }
}
/*
  解法二
  改成了list，还是过不去，说明瓶颈不在优先队列上，是看来数据集太大了，distance就不应该这样存
  O(n ^ 2 + nlogn)
*/
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        List<Integer> distance = new ArrayList<>(nums.length * nums.length / 2);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                distance.add(Math.abs(nums[i] - nums[j]));
            }
        }
        Collections.sort(distance);
        return distance.get(k - 1);
    }
}
/*
  解法三
  发现数据集里重复数据太多了，distance改用Map来装
  内存不超了，时间超了
  O(n ^ 2 * logm)，m为distance的种类，最不理想的情况为n ^ 2，操作TreeMap的查找添加有logm的开销
*/
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Map<Integer, Integer> distance = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int d = Math.abs(nums[i] - nums[j]);
                if (distance.containsKey(d)) {
                    distance.put(d, distance.get(d) + 1);
                } else {
                    distance.put(d, 1);
                }
            }
        }
        for (Map.Entry<Integer, Integer> entry : distance.entrySet()) {
            k -= entry.getValue();
            if (k <= 0) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
/*
  解法四
  “改良”的解法三
  O(n ^ 2 + mlogm)，有点自欺欺人了，m只要不理想，跟上一种没什么区别
*/
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Map<Integer, Integer> distance = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int d = Math.abs(nums[i] - nums[j]);
                if (distance.containsKey(d)) {
                    distance.put(d, distance.get(d) + 1);
                } else {
                    distance.put(d, 1);
                }
            }
        }
        List<Integer> list = new ArrayList<>(distance.keySet());
        Collections.sort(list);
        for (int n : list) {
            k -= list.get(n);
            if (k <= 0) {
                return n;
            }
        }
        return -1;
    }
}
/*
  解法五
  想到了top K问题，把解法一大幅改良，OOM问题解决，但是仍然超时！！！
  我还针对了k过大进行了优化
  我这已经O(n ^ 2 * logk)了，还不行？
  再优化的话，堆内部有大量重复的数字，用一种方法记录重复数字可以加速这部分内容
  但是可以预见的是，只能减少logk，优化效果不大
  正当我想量化优化效果的时候，我发现，第18个数据集，把distance全都放到set，都会超时
  这怎么办呢，n ^ 2都不能接受吗？
  我再把set相关代码注释掉之后，只算distance，不做存储，都要181ms
  看来是数据太多，set操作退化，接近n ^ 3
*/
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        int small = k;
        int big = nums.length * (nums.length - 1) / 2 + 1 - k;
        int size = 0;
        Queue<Integer> heap;
        if (big < small) {
            size = big;
            heap = new PriorityQueue<>(size);
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    int d = Math.abs(nums[i] - nums[j]);
                    if (heap.size() < size) {
                        heap.add(d);
                    } else if (d > heap.peek()){
                        heap.poll();
                        heap.add(d);
                    }
                }
            }
        } else {
            size = small;
            heap = new PriorityQueue<>(Comparator.reverseOrder());
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    int d = Math.abs(nums[i] - nums[j]);
                    if (heap.size() < size) {
                        heap.add(d);
                    } else if (d < heap.peek()){
                        heap.poll();
                        heap.add(d);
                    }
                }
            }
        }
        return heap.peek();
    }
}
/*
  解法六
  整体思路大变化
  针对重复数字多的数据集，用dict把他们存起来，避免在操作的时候+ 1过于低效
  不重复的数据集，用前面的解法
  但是第18个数据集，属于重复数字少的，我还没有能做出来的解法，所以，这个解法还是不行
*/
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Map<Integer, Integer> dict = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (dict.containsKey(nums[i])) {
                dict.put(nums[i], dict.get(nums[i]) + 1);
            } else {
                dict.put(nums[i], 1);
            }
        }
        List<Integer> list = new ArrayList<>(dict.keySet());
        if (list.size() <= nums.length / 2) {
            Map<Integer, Integer> distance = new TreeMap<>();
            distance.put(0, 0);
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    int d = Math.abs(list.get(i) - list.get(j));
                    if (distance.containsKey(d)) {
                        distance.put(d, distance.get(d) + dict.get(list.get(i)) * dict.get(list.get(j)));
                    } else {
                        distance.put(d, dict.get(list.get(i)) * dict.get(list.get(j)));
                    }
                }
                if (dict.get(list.get(i)) > 1) {
                    distance.put(0, distance.get(0) + dict.get(list.get(i)) * (dict.get(list.get(i)) - 1) / 2);
                }
            }
            for (Map.Entry<Integer, Integer> entry : distance.entrySet()) {
                k -= entry.getValue();
                if (k <= 0) {
                    return entry.getKey();
                }
            }
        } else {
            Map<Integer, Integer> distance = new TreeMap<>();
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    int d = Math.abs(nums[i] - nums[j]);
                    if (distance.containsKey(d)) {
                        distance.put(d, distance.get(d) + 1);
                    } else {
                        distance.put(d, 1);
                    }
                }
            }
            for (Map.Entry<Integer, Integer> entry : distance.entrySet()) {
                k -= entry.getValue();
                if (k <= 0) {
                    return entry.getKey();
                }
            }
        }
        
        return -1;
    }
}
/*
  我人麻了，面对第18号数据集
  计算出所有结果，不存储，就消耗掉181ms，所以，最终解法只能是比O(n ^ 2)多一点点
  如果用list存储，就会OOM
  如果用map存储，会超时，因为数据量太大，map退化
  就感觉，无从下嘴
*/
/*
  还有一个思路是二分蒙答案，距离一共就0-max
  每次提供一个距离带入遍历，就知道它是第几
  在一开始的时候我就想到了这个方法，但是当时不知道数据集这么变态，这个方法没有开头几个方法时间复杂度低，它是O(n ^ 2 * logk)，跟堆是一样的
  但是！题解是真的妙啊，在遍历上做了巨大优化，使它不需要O(n ^ 2)
  首先，给原数组排序！其实这一步是很多复杂度高的问题的很好的加速剂
  面对有序数组，我们要研究的是，如何统计出，小于某个值的数对的数量
  双指针！可以实现O(2n)就能统计出符合条件的数对的数量
  有序使得left只要跟着right动就可以了，所以可以O(n)
  跟题解一模一样的代码，不知道为什么还是慢
  执行用时：5 ms, 在所有 Java 提交中击败了46.11%的用户
*/
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums[nums.length - 1] - nums[0];
        int mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (count(nums, mid) < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    public int count(int[] nums, int target) { // count who <= target
        int left = 0;
        int count = 0;
        for (int right = 0; right < nums.length; right++) {
            while (nums[right] - nums[left] > target) {
                left++;
            }
            count += (right - left);
        }
        return count;
    }
}
