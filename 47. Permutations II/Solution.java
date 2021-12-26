// 47. 全排列 II
/*
  将list转化为字符串，然后借助Set去重
  执行用时：12 ms, 在所有 Java 提交中击败了15.88%的用户
*/
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        LinkedList<List<Integer>> ret = new LinkedList<>();
        Set<String> set = new HashSet<>();
        List<Integer> tmp = new LinkedList<>();
        tmp.add(nums[0]);
        ret.offer(tmp);
        String s = listToString(tmp);
        set.add(s);
        while (ret.peek().size() < nums.length) {
            List<Integer> l = ret.poll();
            for (int j = 0; j <= l.size(); j++) {
                tmp = new LinkedList<>(l);
                tmp.add(j, nums[l.size()]);
                s = listToString(tmp);
                if (!set.contains(s)) {
                    ret.offer(tmp);
                    set.add(s);
                }
            }
        }
        return ret;
    }

    public String listToString(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (Integer i : list) {
            sb.append(String.valueOf(i));
            sb.append(" ");
        }
        return sb.toString();
    }
}
/*
  查了一下文档，发现List的equals是可以用在这个题的，因为List里面存放的是128以内的Integer，就没有必要转成String了
  还有一种优化，既然要用两个数据结构来存储，何不把他们合并呢？如果有一种队列+Set的数据结构，就能解决这个问题
  但是可以预见的是，这应该没什么乱用
  但是很显然，这个还不是正解
  执行用时：8 ms, 在所有 Java 提交中击败了16.43%的用户
*/
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        LinkedList<List<Integer>> ret = new LinkedList<>();
        Set<List<Integer>> set = new HashSet<>();
        List<Integer> tmp = new LinkedList<>();
        tmp.add(nums[0]);
        ret.offer(tmp);
        set.add(tmp);
        while (ret.peek().size() < nums.length) {
            List<Integer> l = ret.poll();
            for (int j = 0; j <= l.size(); j++) {
                tmp = new LinkedList<>(l);
                tmp.add(j, nums[l.size()]);
                if (!set.contains(tmp)) {
                    ret.offer(tmp);
                    set.add(tmp);
                }
            }
        }
        return ret;
    }
}
/*
  看了题解，我恍然大悟，但是心中还有一点不服，我觉得我的插入大法一定行
  我也排了个序，能解决我想到的一个bug
  比如[1, 2, 1]
  在最后一个1插入的时候，前面有12和21两个序列
  这个1插入会带来两次121序列
  如果我们排好序，就能解决这个问题
  就是后一个重复数字在插入的时候会构成对称序列导致重复
  并且这种对称序列离得很远，很难判断
  然而我还是败了，[2, 2, 1, 1]这个测试用例就通不过
  确实这种插入对于避免重复没什么用
  但是递归是真可以，从原理上就能避免，一听就很靠谱
  Wrong Answer!!!
*/
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        LinkedList<List<Integer>> ret = new LinkedList<>();
        List<Integer> tmp = new LinkedList<>();
        tmp.add(nums[0]);
        ret.offer(tmp);
        while (ret.peek().size() < nums.length) {
            List<Integer> l = ret.poll();
            for (int j = 0; j <= l.size(); j++) {
                tmp = new LinkedList<>(l);
                if (j == 0) {
                    tmp.add(j, nums[l.size()]);
                    ret.offer(tmp);
                } else {
                    if (tmp.get(j - 1) != nums[l.size()]) {
                        tmp.add(j, nums[l.size()]);
                        ret.offer(tmp);
                    }
                }
            }
        }
        return ret;
    }
}
/*
  递归（回溯），我更喜欢叫递归，大家更喜欢叫回溯
  要注意的是对象的引用，一定要new，不然的话容易被篡改
  但是依然不太行，这次跟答案对比了一下，找到问题了
  就是代码写的矬，回溯问题可以避免new的，在递归返回之后，把之前的删除掉，就可以避免在过程中new了
  执行用时：2 ms, 在所有 Java 提交中击败了41.15%的用户
*/
class Solution {
    List<List<Integer>> ret;
    int[] nums;
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        this.nums = nums;
        ret = new LinkedList<>();
        dfs(new ArrayList<Integer>(), new boolean[nums.length]);
        return ret;
    }

    public void dfs(List<Integer> curList, boolean[] visited) {
        if (curList.size() == nums.length) {
            ret.add(curList);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (i > 0 && !visited[i - 1] && nums[i - 1] == nums[i]) {
                continue;
            }
            List<Integer> nextList = new LinkedList<>(curList);
            nextList.add(nums[i]);
            boolean[] nextVisited = Arrays.copyOf(visited, visited.length);
            nextVisited[i] = true;
            dfs(nextList, nextVisited);
        }
    }
}
/*
  最终形态，回溯之后擦屁股，避免了在过程中new大量的对象
  执行用时：1 ms, 在所有 Java 提交中击败了99.26%的用户
*/
class Solution {
    List<List<Integer>> ret;
    int[] nums;
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        this.nums = nums;
        ret = new LinkedList<>();
        dfs(new ArrayList<Integer>(), new boolean[nums.length]);
        return ret;
    }

    public void dfs(List<Integer> curList, boolean[] visited) {
        if (curList.size() == nums.length) {
            ret.add(new LinkedList<>(curList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (i > 0 && !visited[i - 1] && nums[i - 1] == nums[i]) {
                continue;
            }
            curList.add(nums[i]);
            visited[i] = true;
            dfs(curList, visited);
            curList.remove(curList.size() - 1);
            visited[i] = false;
        }
    }
}
