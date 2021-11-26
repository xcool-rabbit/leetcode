// 1482. 制作 m 束花所需的最少天数
/*
  这道题，载入屎厕！
  这个题做的极其恼火，被示例误导到一个很笨很笨的方法去了
  并且呢，这个笨方法实在是太笨了，不经过精细的优化，笨方法是ac不了的
  下面我先来讲讲这个笨方法
  循环一次就是一天
  每天首先要更新开花的状态，然后判断够不够
  优化：
  用PriorityQueue（堆）实现了可以快速找到当天哪朵花开了
  之前是用链表存储，然后实现了Comparator，用sort排序，这种方法我觉得也可以
  链表要先添加一遍，再排序，而堆是一边添加，一边就维护好了一个有序的队列
  然而链表在取的时候没有开销，堆取的时候还需要sift操作
  可是，实际执行下来，链表实现的超时了
  执行用时：1400 ms, 在所有 Java 提交中击败了5.00%的用户
*/
class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        if (m * k > bloomDay.length) {
            return -1;
        }
        Map<Integer, Integer> map = new HashMap<>(bloomDay.length);
        for (int i = 0; i < bloomDay.length; i++) {
            map.put(i, bloomDay[i]);
        }
        Queue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(map.entrySet().size(), new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        for (Map.Entry e : map.entrySet()) {
            queue.add(e);
        }

        boolean[] bloomed = new boolean[bloomDay.length];
        int bloomedNum = 0;
        int bloomLeast = m * k;
        while (queue.size() > 0) {
            List<Integer> bloomIndex = new LinkedList<>();
            int cur = queue.peek().getValue();
            while (queue.size() > 0 && queue.peek().getValue() == cur) {
                bloomIndex.add(queue.poll().getKey());
            }
            for (int i : bloomIndex) {
                bloomed[i] = true;
            }
            if (bloomedNum < bloomLeast) {
                bloomedNum += bloomIndex.size();
            }
            bloomIndex.clear();
            if (bloomedNum >= bloomLeast) {
                int difference = isEnough(bloomed, m, k);
                if (difference <= 0) {
                    return cur;
                } else {
                    bloomedNum = 0;
                    bloomLeast = difference;
                }
            }
        }

        return -1;
    }

    public int isEnough(boolean[] bloomed, int m, int k) {
        boolean isContinuous = false;
        int continueTimes = 0;
        for (boolean b : bloomed) {
            if (b) {
                isContinuous = true;
                continueTimes++;
            } else {
                isContinuous = false;
                continueTimes = 0;
            }
            if (continueTimes == k) {
                m--;
                continueTimes = 0;
            }
        }
        return m;
    }
}
/*
  就在我写题解的时候，忽然想到好像可以倒排
  key是开花的日子，value是一个列表，记录当天开花的是谁
  然后map用Treemap，可以实现key的排序
  实测比堆还要快
  感慨一下由于掌握了很多种数据结构，所以这个题可以有多种优化方式，相互组合，五花八门
  执行用时：1294 ms, 在所有 Java 提交中击败了5.00%的用户
*/
class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        if (m * k > bloomDay.length) {
            return -1;
        }
        Map<Integer, List<Integer>> map = new TreeMap<>();
        for (int i = 0; i < bloomDay.length; i++) {
            if (map.containsKey(bloomDay[i])) {
                map.get(bloomDay[i]).add(i);
            } else {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(i);
                map.put(bloomDay[i], tmp);
            }
        }

        boolean[] bloomed = new boolean[bloomDay.length];
        int bloomedNum = 0;
        int bloomLeast = m * k;
        for (Map.Entry<Integer, List<Integer>> e : map.entrySet()) {
            List<Integer> bloomIndex = e.getValue();
            for (int i : bloomIndex) {
                bloomed[i] = true;
            }
            if (bloomedNum < bloomLeast) {
                bloomedNum += bloomIndex.size();
            }
            bloomIndex.clear();
            if (bloomedNum >= bloomLeast) {
                int difference = isEnough(bloomed, m, k);
                if (difference <= 0) {
                    return e.getKey();
                } else {
                    bloomedNum = 0;
                    bloomLeast = difference;
                }
            }
        }

        return -1;
    }

    public int isEnough(boolean[] bloomed, int m, int k) {
        boolean isContinuous = false;
        int continueTimes = 0;
        for (boolean b : bloomed) {
            if (b) {
                isContinuous = true;
                continueTimes++;
            } else {
                isContinuous = false;
                continueTimes = 0;
            }
            if (continueTimes == k) {
                m--;
                continueTimes = 0;
            }
        }
        return m;
    }
}
/*
  其实做到这里应该想到，我这样遍历，模拟开花的步骤，何不如，用二分法来试答案呢
  目前我的所谓正解，其实也是从第一天开始，逐渐试答案，一直模拟到最后一天
  优化：二分的left不是0，可以稍微调一点点
  执行用时：21 ms, 在所有 Java 提交中击败了53%的用户
*/
class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        if (m * k > bloomDay.length) {
            return -1;
        }
        int left = Integer.MAX_VALUE;
        int right = 0;
        for (int n : bloomDay) {
            left = left < n ? left : n;
            right = right > n ? right : n;
        }
        int mid = left + (right - left) / 2;

        while (left < right) {
            if (isEnough(bloomDay, mid, m, k)) {
                right = mid;
            } else {
                left = mid + 1;
            }
            mid = left + (right - left) / 2;
        }

        return mid;
    }

    public boolean isEnough(int[] bloomDay, int cur, int m, int k) {
        boolean isContinuous = false;
        int continueTimes = 0;
        for (int i = 0; i < bloomDay.length; i++) {
            boolean b = bloomDay[i] <= cur;
            if (b) {
                isContinuous = true;
                continueTimes++;
            } else {
                isContinuous = false;
                continueTimes = 0;
            }
            if (continueTimes == k) {
                m--;
                continueTimes = 0;
            }
        }
        return m <= 0;
    }
}
/*
  在考虑上一种方法的优化的时候，我曾经想过，比如只有两个值，[1, 10000]
  二分会哼哧哼哧分半天，而枚举反而会更快
  那么可以设计一种策略，评估问题规模，决定何时用枚举，何时用二分
  然而我又想了一下，枚举快的场景很有限，在这种场景下，就算二分，也没有很耗时间，没有必要
  后来深入思考了一下，这个矛盾实际上是以值来二分的一个弊端，很多时候可能并没有踩在点子上
  有没有这样一种策略可以在二分的时候踩在点子上呢？基于索引！
  我跟ybb说，她揭露真相：这不就是最基础的二分查找咩
  我恍然大悟
  然而效果并不是很好，可能的原因是数据的特点吧，不适合我这种策略
  基于索引的二分，前提是数组有序，可能在排序上花了一些开销，导致他并不快
  执行用时：113 ms, 在所有 Java 提交中击败了5.00%的用户
*/
class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        if (m * k > bloomDay.length) {
            return -1;
        }
        Set<Integer> set = new HashSet<>(bloomDay.length);
        for (int n : bloomDay) {
            set.add(n);
        }
        List<Integer> chart = new ArrayList<>(set);
        Collections.sort(chart);

        int left = 0;
        int right = chart.size() - 1;
        int mid = left + (right - left) / 2;
        while (left < right) {
            if (isEnough(bloomDay, chart.get(mid), m, k)) {
                right = mid;
            } else {
                left = mid + 1;
            }
            mid = left + (right - left) / 2;
        }

        return chart.get(mid);
    }

    public boolean isEnough(int[] bloomDay, int cur, int m, int k) {
        boolean isContinuous = false;
        int continueTimes = 0;
        for (int i = 0; i < bloomDay.length; i++) {
            boolean b = bloomDay[i] <= cur;
            if (b) {
                isContinuous = true;
                continueTimes++;
            } else {
                isContinuous = false;
                continueTimes = 0;
            }
            if (continueTimes == k) {
                m--;
                continueTimes = 0;
            }
        }
        return m <= 0;
    }
}
