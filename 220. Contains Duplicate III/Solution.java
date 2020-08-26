//220. 存在重复元素 III
/*
  最简单的遍历，复杂度为O(kN)，可以看到很多人的提交记录跟我做的方法一样，在用时最慢的那一堆里
  执行用时：634 ms 已经战胜 15.51 % 的 java 提交记录
*/
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j <= i + k && j < nums.length; j++) {
                if (Math.abs((long)nums[i] - (long)nums[j]) <= (long)t) {
                    return true;
                }
            }
        }
        return false;
    }
}
/*
  官方解法分析的很有道理
  上一个解法的瓶颈出现在了每次都要对窗口进行查找
  可以将窗口维护成一个二叉搜索树，在Java中用TreeSet实现
  需要注意的是声明变量的时候仍然得用TreeSet，可不要用Set哦，用Set声明的话不会有TreeSet专有的方法
  二叉搜索树在查找添加和删除上的效率都是O(h), h = log(N)
  从而实现性能上的飞升
  执行用时: 21 ms 已经战胜 57.64 % 的 java 提交记录
*/
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<>();
        Integer big, small;
        long T = t;
        for (int i = 0; i < nums.length; i++) {
            big = set.ceiling(nums[i]);
            if (big != null && (long)big <= (long)nums[i] + T) {
                return true;
            }
            small = set.floor(nums[i]);
            if (small != null && (long)small >= (long)nums[i] - T) {
                return true;
            }
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
}
/*
  还有一种方法值得借鉴，按照间隔可以将数分成几个一组
  如果有本组或者相邻组的，那就是true
  判断的思想跟二叉搜索树是类似的，就不再写了
*/