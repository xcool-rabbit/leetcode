// 315. 计算右侧小于当前元素的个数
/*
  最近困难题看麻了
  这个题，我的方案不好的原因就在于
  我不知道除了前缀和以外的其他段操作
  树状数组
  可以实现O(logn)级别的数组update和O(logn)级别的区间求和
  前缀和呢，虽然求和很快O(1)，一但数组有修改，就需要O(n)重新维护，对于这道题来讲，就是O(n)
  树状数组的学习直接看https://www.cnblogs.com/xenny/p/9739600.html，讲的非常到位
  原理是任何一个数都可以用若干个2的幂之和来表示
  用到a和c两个数组，a是原数组，c是树状数组
  c里面存的是某些数的和，具体那些数，是根据树状数组的特性来的，具体看博客
  包含lowBit, getSum, update三个函数
  lowBit是取这个数的最后一位，非常的巧妙
  getSum是求和
  update是当a有更新的时候，相应的对c更新
  执行用时：59 ms, 在所有 Java 提交中击败了48.56%的用户
*/
class Solution {
    int[] c;
    public List<Integer> countSmaller(int[] nums) {
        LinkedList<Integer> ret = new LinkedList<>();
        Set<Integer> set = new HashSet<>(nums.length);
        Map<Integer, Integer> indexCache = new HashMap<>(set.size());
        for (int n : nums) {
            set.add(n);
        }
        int[] a = new int[set.size()];
        int index = 0;
        for (int n : set) {
            a[index++] = n;
        }
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++) {
            indexCache.put(a[i], i + 1);
        }

        c = new int[a.length + 1];
        for (int i = nums.length - 1; i >= 0; i--) {
            index = indexCache.get(nums[i]);
            ret.addFirst(getSum(index - 1));
            update(index, 1);
        }
        return ret;
    }

    public int lowBit(int index) {
        return index & (-index);
    }

    public int getSum(int index) {
        int sum = 0;
        while (index > 0) {
            sum += c[index];
            index -= lowBit(index);
        }
        return sum;
    }

    public void update(int index, int delta) {
        while (index < c.length) {
            c[index] += delta;
            index += lowBit(index);
        }
    }
}
/*
  我自己想到了一个办法，属于是暴力的优化
  首先肯定是倒序遍历nums
  另外维护一个数组叫做已经遍历过的
  这个数组要保持有序
  可以用TreeMap或者普通数组来实现
  TreeMap不行，因为没有办法统计前面有多少个数
  普通数组 + binarySearch可以很好满足，但是缺点就是插入的时候开销是O(n)
  如果插入这一步Java可以通过一些批量复制的方式来实现的话，那这个方法也是O(nlogn)
  不过从执行用时上来看，并没有那么理想
  那这个方法可能就是按照O(n)来执行的，跟暴力区别不大，也没必要迁移到LCOF51去了
  执行用时：741 ms, 在所有 Java 提交中击败了6.35%的用户
*/
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        LinkedList<Integer> ret = new LinkedList<>();
        ArrayList<Integer> visited = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            if (visited.size() == 0) {
                ret.add(0);
                visited.add(nums[i]);
                continue;
            }
            int index = binarySearch(visited, nums[i]);
            index++;
            ret.addFirst(index);
            visited.add(index, nums[i]);
        }
        return ret;
    }

    public int binarySearch(List<Integer> list, int target) {
        // return the first element < target
        if (list.get(0) >= target) {
            return -1;
        }
        int left = 0;
        int right = list.size() - 1;
        int mid;
        while (left < right - 1) {
            mid = left + (right - left) / 2;
            if (list.get(mid) >= target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        if (left < right) {
            if (list.get(right) < target) {
                return right;
            }
        }
        return left;
    }
}
/*
  归并，参考LCOF51
  不想实现了，跟LCOF51一模一样
  区别就是把value和index捆绑起来
  把ret +=改成去ret.get(index)里面+=
*/
