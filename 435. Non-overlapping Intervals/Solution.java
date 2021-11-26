// 435. 无重叠区间
/*
  从卡55优化到了卡56，也算是一种小小的进步吧
  看到段，以为是要差分数组
  实际上差分数组并没有对这道题起太多的优化作用
  可以看到只有一开始用到了差分数组，后面很多的判断还是基于sum数组来的，所以没有快多少
  另一点，由于没有用到差分数组，每次操作长度都很长，大大降低了程序的运行速度
  并且也不好优化或者用Map来做缓存
  总之不是一种很好的方法
  但是这种删减的思路还是值得称赞的
  先叠起来计算每个位置的重复次数
  删除的时候要根据有效长度来删，确实是一种能达到最小的方法
  56 / 58 个通过测试用例
*/
class Solution {
    public int min = Integer.MAX_VALUE;
    public int max = Integer.MIN_VALUE;
    public int largerThanOneIndex = 0;
    public int eraseOverlapIntervals(int[][] intervals) {
        List<int[]> intervalList = new ArrayList<>(Arrays.asList(intervals));
        Collections.sort(intervalList, new Comparator<int[]>() {
            @Override
            public int compare(int[] a1, int[] a2) {
                return a1[0] - a2[0];
            }
        });
        for (int[] interval : intervalList) {
            min = min < interval[0] ? min : interval[0];
            max = max > interval[1] ? max : interval[1];
        }
        int[] delta = new int[max - min + 1];
        for (int[] interval : intervalList) {
            int start = interval[0] - min;
            delta[start]++;
            int end = interval[1] - min;
            delta[end]--;
        }
        int[] sum = new int[delta.length];
        for (int i = 0; i < delta.length; i++) {
            if (i == 0) {
                sum[i] = delta[i];
            } else {
                sum[i] = sum[i - 1] + delta[i];
            }
        }
        int times = 0;
        while (true) {
            int index = findLargerThanOne(sum);
            if (index == -1) {
                return times;
            }
            int intervalIndex = -1;
            int maxLength = Integer.MIN_VALUE;
            for (int i = 0; i < intervalList.size(); i++) {
                if (intervalContainsIndex(intervalList.get(i), index)) {
                    int curLength = 0;
                    int start = intervalList.get(i)[0] - min;
                    int end = intervalList.get(i)[1] - min;
                    if ((end - start) < maxLength) {
                        continue;
                    }
                    for (int j = start; j < end; j++) {
                        if (sum[j] > 1) {
                            curLength++;
                        }
                    }
                    if (curLength > maxLength) {
                        maxLength = curLength;
                        intervalIndex = i;
                    }
                } else if (intervalLargerThanIndex(intervalList.get(i), index)) {
                    break;
                }
            }
            sumMinusInterval(sum, intervalList.get(intervalIndex));
            intervalList.remove(intervalIndex);
            times++;
        }
    }

    public int findLargerThanOne(int[] sum) {
        for (int i = largerThanOneIndex; i < sum.length; i++) {
            if (sum[i] > 1) {
                largerThanOneIndex = i;
                return i;
            }
        }
        return -1;
    }

    public boolean intervalContainsIndex(int[] interval, int index) {
        int start = interval[0] - min;
        int end = interval[1] - min;
        return start <= index && index < end;
    }
    
    public boolean intervalLargerThanIndex(int[] interval, int index) {
        int start = interval[0] - min;
        int end = interval[1] - min;
        return start > index;
    }

    public void sumMinusInterval(int[] sum, int[] interval) {
        int start = interval[0] - min;
        int end = interval[1] - min;
        for (int i = start; i < end; i++) {
            sum[i]--;
        }
    }
}
/*
  动态规划！又是动态规划！
  我跟yb吐槽，yb说最大最小的问题都可以用动态规划
  这道题首先需要拐个弯儿，去掉最少的，就相当于能装尽量多的
  状态转移方程是这一段加上前面答案中的最大值（前提是不与这一段相交）
  为了可以更方便的判断，需要对数组先排序
  然后！随手写的并不能ac，双层循环n ^ 2确实会慢
  所以天才的我用了一个巧妙的数据结构来维护，使得第二次循环的嵌套不需要遍历完全
  大概也就快了50%的样子，本质上还是n ^ 2
  执行用时：695 ms, 在所有 Java 提交中击败了5.04%的用户
*/
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a1, int[] a2) {
                return a1[0] - a2[0];
            }
        });
        TreeMap<Integer, List<Integer>> answer = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i2 - i1;
            }
        });
        answer.put(1, new ArrayList<>(1));
        answer.get(1).add(0);
        for (int i = 1; i < intervals.length; i++) {
            int max = 0;
            for (Map.Entry<Integer, List<Integer>> entry : answer.entrySet()) {
                for (int n : entry.getValue()) {
                    if (intervals[n][1] <= intervals[i][0]) {
                        max = entry.getKey();
                        break;
                    }
                }
                if (max != 0) {
                    break;
                }
            }
            if (answer.containsKey(max + 1)) {
                answer.get(max + 1).add(i);
            } else {
                answer.put(max + 1, new ArrayList<>(1));
                answer.get(max + 1).add(i);
            }
        }
        return intervals.length - answer.firstKey();
    }
}
/*
  贪心来了！
  首先还是转变思维，删除最少的就是装下最多的
  有个东西是可以局部最优的，就是你用了一个右端点最小的区间，对于剩下的来讲，一定是最划算的，这给剩下的留了最大的空间
  所以对所有区间右端点排序，选右端点最小的，但是还不能跟前面冲突
  这里想到一个优化，可以对相同右端点的放到一起，只要有一个成功匹配的，剩下的就不需要再看了
  但是这对排序提出了不小的要求，总的来讲较为麻烦，没有必要，不会快多少，就不写了
  执行用时：48 ms, 在所有 Java 提交中击败了90.83%的用户
*/
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length < 2) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a1, int[] a2) {
                return a1[1] - a2[1];
            }
        });
        int right = intervals[0][1];
        int count = 1;
        for (int[] a : intervals) {
            if (a[0] >= right) {
                right = a[1];
                count++;
            }
        }
        return intervals.length - count;
    }
}
