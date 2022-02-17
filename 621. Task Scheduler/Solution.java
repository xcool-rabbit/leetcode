// 621. 任务调度器
/*
  这种题，没做过，脑子里没有一些正常的思路，觉得做不出来就看题解了
  不过这个方案并不是最快的，是模拟的思路，我觉得有必要实现一下，这对我来讲很新鲜
  让重复次数最多的任务，优先去调度，会得到最节约时间的方案
  综合各种数据结构来看，堆是最适合维护任务队列的
  另外就是冷却机制，我也用了一个队列去维护
  执行用时：31 ms, 在所有 Java 提交中击败了15.94%的用户
*/
class Solution {
    class Pair {
        public char letter;
        public int count;

        public Pair(char letter, int count) {
            this.letter = letter;
            this.count = count;
        }

        public void process() {
            this.count--;
        }
    }

    public int leastInterval(char[] tasks, int n) {
        int processed = 0;
        int ret = 0;
        int[] arr = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            arr[tasks[i] - 'A']++;
        }
        Queue<Pair> heap = new PriorityQueue<>(
            (p1, p2) -> p2.count - p1.count
        );
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                heap.add(new Pair((char)('A' + i), arr[i]));
            }
        }
        Queue<Pair> coolDown = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            coolDown.offer(null);
        }
        while (processed < tasks.length) {
            Pair p = heap.poll();
            if (p != null) {
                p.process();
                p = p.count <= 0 ? null : p;
                processed++;
            }
            p = next(coolDown, p);
            if (p != null) {
                heap.add(p);
            }
            ret++;
        }
        return ret;
    }

    public Pair next(Queue<Pair> coolDown, Pair p) {
        coolDown.offer(p);
        return coolDown.poll();
    }
}
/*
  首先，这个方法太牛逼了，直接把这个调度问题变成了O(n)
  直接去看题解2吧，图文并茂，很容易懂
  再看这个循环，需要统计每个字母出现的次数，然后统计次数中的最大值，以及最大值出现的次数
  本来想练习stream的写法，但是发现开销有点大，要4ms，就弃用了
  想要精简，在改变stream的写法的同时，把求maxProc的环节放到tasks的循环里去了
  这时其实有点走火入魔了，发现执行用时降到了3ms，继续开动脑筋精简，发现maxCount也可以放进去
  所以最后就成就了这个只用一个for循环的写法
  但是呢，时间并没有快
  这时我就开始怀疑人生了，到底还能在哪里快
  先看了一下题解2，用的Map啥的，绝对慢的一批，运行了一下，果然
  然后去提交详情里面找最快的人代码是怎么写的
  啧，用了3个for循环呢
  但是人家用的是forEach，难道是这个原因？
  然后我就改了，还是3ms
  我自闭了
  执行用时：3 ms, 在所有 Java 提交中击败了57.82%的用户
*/
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] arr = new int[26];
        int maxProc = 0;
        int maxCount = 1;
        for (char c : tasks) {
            arr[c - 'A']++;
            if (maxProc == arr[c - 'A']) {
                maxCount++;
            } else if (maxProc < arr[c - 'A']) {
                maxCount = 1;
                maxProc = arr[c - 'A'];
            }
        }
        return Math.max((maxProc - 1) * (n + 1) + maxCount, tasks.length);
    }
}
/*
  后来我想到了，tasks很大的，arr只有26长
  把本能放在arr里面解决的东西放到tasks里去，实际上变慢了很多
  所以有了这一版的解法
  我还把后两个for循环放到了一起，企图达到0ms，但是没成功，不过已经是最快的了
  执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] arr = new int[26];
        for (char c : tasks) {
            arr[c - 'A']++;
        }
        int maxProc = 0;
        int maxCount = 1;
        for (int a : arr) {
            if (a == maxProc) {
                maxCount++;
            } else if (a > maxProc) {
                maxProc = a;
                maxCount = 1;
            }
        }
        return Math.max((maxProc - 1) * (n + 1) + maxCount, tasks.length);
    }
}
