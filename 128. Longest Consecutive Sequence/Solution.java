// 128. 最长连续序列
/*
  大意了，一看到数组，O(n)，就掏出我那些工具箱（大小数组双端队列），然后发现一个都用不上
  桶的话，又存不了那么大，数据范围10 ^ 9
  就在想有没有一些方式可以存储一段一段的桶
  还想到一些桶的延展的策略
  其实答案跟桶的延展有点像
  只不过延展的不是桶，而直接是答案
  答案是对每个数遍历，基于暴力的思路去优化的
  首先把数都存到集合里
  如果n存在，就找n + 1存不存在，这样就能知道以这个数为起点的连续序列长度
  但是这样会O(n ^ 2)
  如何优化呢，注意到我们会重复寻找以n + 1为起点的序列
  为了避免不必要的重复，如果n - 1存在，那么n就略过
  这样就能保证总体O(n)了
  至于为什么提交上去这么慢，是因为，set的查找效率理论上是O(n)，实际上会慢很多
  执行用时：324 ms, 在所有 Java 提交中击败了5.02%的用户
*/
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (int n : nums) {
            set.add(n);
        }

        int ret = 0;
        for (int n : nums) {
            if (set.contains(n - 1)) {
                continue;
            }
            int curLength = 1;
            while (set.contains(n + 1)) {
                curLength++;
                n++;
            }
            ret = Math.max(ret, curLength);
        }
        return ret;
    }
}
