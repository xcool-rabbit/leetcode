//752. 打开转盘锁
/*
  这个题是我最近做过最复杂的一道题了
  首先依靠计算机的智能，并不能直接找出能避开死锁还是最短路径的最优解
  采用的方法是暴力破解
  转盘每一位都采用BFS遍历，找出能到达的所有解
  优化小技巧：
  deadends用Set数据结构，空间换时间
  visited记录已经访问过的元素，比如一个正着转，一个倒着转，会在5相遇，应避免这种重复的情况
  执行用时：88 ms 已经战胜 81.46 % 的 java 提交记录
*/
class Solution {
    public int openLock(String[] deadends, String target) {
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        Queue<String> level = new LinkedList<>();
        Set<String> dead = new HashSet<>(Arrays.asList(deadends));
        if (dead.contains("0000")) {
            return -1;
        }
        Set<String> visited = new HashSet<>();
        visited.add("0000");
        int times = 0;
        while (!(queue.isEmpty() && level.isEmpty())) {
            if (!queue.isEmpty()) {
                String tmp = queue.poll();
                char[] tmpCharArray = tmp.toCharArray();
                for (int i = 0; i < tmpCharArray.length; i++) {
                    char origin = tmpCharArray[i];
                    tmpCharArray[i] = origin == '9' ? '0' : (char)(tmpCharArray[i] + 1);
                    String cur = new String(tmpCharArray);
                    if (cur.equals(target)) {
                        return times + 1;
                    }
                    if (!dead.contains(cur) && !visited.contains(cur)) {
                        level.offer(cur);
                        visited.add(cur);
                    }
                    tmpCharArray[i] = origin;
                    tmpCharArray[i] = origin == '0' ? '9' : (char)(tmpCharArray[i] - 1);
                    cur = new String(tmpCharArray);
                    if (cur.equals(target)) {
                        return times + 1;
                    }
                    if (!dead.contains(cur) && !visited.contains(cur)) {
                        level.offer(cur);
                        visited.add(cur);
                    }
                    tmpCharArray[i] = origin;
                }
            } else {
                queue = level;
                level = new LinkedList<>();
                times++;
            }
        }
        return -1;
    }
}
