// 127. 单词接龙
/*
  深度回溯剪枝，果然会寄
  LTE
*/
class Solution {
    int ret;
    int step = 1;
    boolean[] mask;
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        ret = wordList.size() + 2;
        mask = new boolean[wordList.size()];
        recursion(beginWord, endWord, wordList);
        return ret == wordList.size() + 2 ? 0 : ret;
    }

    public void recursion(String cur, String target, List<String> wordList) {
        if (cur.equals(target)) {
            ret = Math.min(ret, step);
            return;
        }
        for (int i = 0; i < wordList.size(); i++) {
            if (mask[i]) {
                continue;
            }
            String word = wordList.get(i);
            if (canTransform(word, cur)) {
                mask[i] = true;
                step++;
                recursion(word, target, wordList);
                mask[i] = false;
                step--;
            }
        }
    }

    public boolean canTransform(String a, String b) {
        int diff = 1;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff--;
                if (diff < 0) {
                    return false;
                }
            }
        }
        return diff == 0;
    }
}
/*
  回溯有很多的冗余，受限于visited，没有办法做记录
  或者说，计算强行记录了，也不是最短的
  比如深度递归到头，发现到不了target
  实际上，递归往回退两步就能到了
  那么尽头处的结点已经被标记为不可达了
  这时如果有另外一条搜索路径，到达了这个结点，并且到不了这个结点在递归时回退的那个结点
  那么这条路径也就到此为止了，本来能找到或者说行得通的路径，也会被判断为行不通
  确实不会在之前超时的地方卡住了，但是会有WA
*/
class Solution {
    int ret;
    boolean[] mask;
    Map<String, Integer> map;
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        ret = wordList.size() + 2;
        mask = new boolean[wordList.size()];
        map = new HashMap<>(wordList.size());
        ret = recursion(beginWord, endWord, wordList);
        return ret == wordList.size() + 2 ? 0 : ret;
    }

    public int recursion(String cur, String target, List<String> wordList) {
        int min = wordList.size() + 2;
        int step = 1;
        if (cur.equals(target)) {
            return step;
        }
        if (map.containsKey(cur)) {
            return map.get(cur);
        }
        for (int i = 0; i < wordList.size(); i++) {
            if (mask[i]) {
                continue;
            }
            String word = wordList.get(i);
            if (canTransform(word, cur)) {
                mask[i] = true;
                step = 1 + recursion(word, target, wordList);
                min = Math.min(min, step);
                mask[i] = false;
            }
        }
        map.put(cur, min);
        return min;
    }

    public boolean canTransform(String a, String b) {
        int diff = 1;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff--;
                if (diff < 0) {
                    return false;
                }
            }
        }
        return diff == 0;
    }
}

/*
  建图 + 无向图最短路径
  每一个单词都是一个结点，很多互相之间都能做转换
  只需要求从开始到target的路径即可
  求路径用BFS，什么时候匹配到了，就是最短的
  因为BFS，所有朝向都会同时走一步
  所以先遇到的路径，就是最短路径
  执行用时：466 ms, 在所有 Java 提交中击败了26.66%的用户
*/
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        wordList.add(beginWord);
        int ret = 1;
        int target = -1;
        boolean[] visited = new boolean[wordList.size()];
        List<Integer>[] graph = new LinkedList[wordList.size()];
        for (int i = 0; i < wordList.size(); i++) {
            graph[i] = new LinkedList<Integer>();
            if (wordList.get(i).equals(endWord)) {
                target = i;
            }
        }
        if (target == -1) {
            return 0;
        }
        for (int i = 0; i < wordList.size(); i++) {
            for (int j = i + 1; j < wordList.size(); j++) {
                if (canTransform(wordList.get(i), wordList.get(j))) {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> nextStep = new LinkedList<>();
        queue.offer(wordList.size() - 1);
        visited[wordList.size() - 1] = true;
        while (!(queue.isEmpty() && nextStep.isEmpty())) {
            if (!queue.isEmpty()) {
                int cur = queue.poll();
                for (int n : graph[cur]) {
                    if (n == target) {
                        return ret + 1;
                    }
                    if (!visited[n]) {
                        visited[n] = true;
                        nextStep.offer(n);
                    }
                }
            } else {
                queue = nextStep;
                ret++;
                nextStep = new LinkedList<>();
            }
            
        }
        return 0;
    }

    public boolean canTransform(String a, String b) {
        int diff = 1;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff--;
                if (diff < 0) {
                    return false;
                }
            }
        }
        return diff == 0;
    }
}
/*
  谈一谈优化思路
  双向广度搜索
  广度搜索一个很大的弊端就是越往后，越大
  双向广度搜索可以很好避免这种扩大
  双向奔赴，更快相遇
  在还没扩大的时候就及时遇见
  理论上是变快了，但是，计算复杂度的时候，并没有本质区别
  第二个优化思路是官方题解给的
  在建图连边的时候，我们会比较每一个单词，判断是否能连
  这一步其实要消耗O(c * n ^ 2)，c是字符串的长度，n是字符串的个数
  题解机智的利用了缓存和HashMap
  构建了一个虚拟节点的概念
  这样在匹配的的时候，可以用hash接近O(1)的匹配，判断是否相连
  有点空间换时间的概念了
  anyway，图的问题，我现在的立场是：能做出来就行
  进阶的这种，打扰了
  所以这些优化，以后再实现叭
*/
