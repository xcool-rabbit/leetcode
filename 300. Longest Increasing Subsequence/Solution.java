// 300. 最长递增子序列
/*
  嘿嘿！开始能自己想出动态规划了
  状态转移方程是从右到左，寻找到符合条件的最大值，然后加一
  执行用时：77 ms, 在所有 Java 提交中击败了10.83%的用户
*/
class Solution {
    public int lengthOfLIS(int[] nums) {
        int ret = 0;
        int[] answer = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int max = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    max = Math.max(max, answer[j]);
                }
            }
            answer[i] = max + 1;
            ret = Math.max(ret, answer[i]);
        }
        return ret;
    }
}
/*
  谢邀，从435题来的。那道题的优化手段就是加一个TreeMap来避免在寻找最大值时耗费太多的时间
  应该是因为那道题的数据规模很大，所以用一个重型的数据结构会带来正收益
  这道题这样做反而会反向优化，所以说题解的nlogn还是有点说法的
*/
class Solution {
    public int lengthOfLIS(int[] nums) {
        int ret = 0;
        TreeMap<Integer, List<Integer>> answer = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i2 - i1;
            }
        });
        answer.put(0, new ArrayList<>(1));
        answer.get(0).add(nums.length - 1);
        for (int i = nums.length - 1; i >= 0; i--) {
            int max = 0;
            for (Map.Entry<Integer, List<Integer>> entry : answer.entrySet()) {
                for (int n : entry.getValue()) {
                    if (nums[i] < nums[n]) {
                        max = Math.max(max, entry.getKey());
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
            ret = Math.max(ret, max + 1);
        }
        return ret;
    }
}
/*
  巧妙的贪心
  维护一个数组list，数组的含义是，
  这个长度的递增子序列，结尾的值
  维护子序列的的策略：
  遍历数组
  当前值比数组尾部大，则添加到数组尾部，表示这个值可以放到递增子序列的最后，子序列变长！
  否则，通过二分查找，找到第一个比当前值大的位置，将这个位置的值替换成当前值，也表示这个长度的子序列的结尾的最小值
  执行用时：6 ms, 在所有 Java 提交中击败了77.95%的用户
*/
class Solution {
    public int lengthOfLIS(int[] nums) {
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        int index;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > list.get(list.size() - 1)) {
                list.add(nums[i]);
            } else {
                index = findIndex(list, nums[i]);
                list.set(index, nums[i]);
            }
        }
        return list.size();
    }

    public int findIndex(List<Integer> list, int target) {
        int start = 0;
        int end = list.size() - 1;
        int middle = start + (end - start) / 2;
        int ret = 0;
        while (start <= end) {
            if (list.get(middle) >= target) {
                end = middle - 1;
                ret = middle;
            } else {
                start = middle + 1;
            }
            middle = start + (end - start) / 2;
        }
        return ret;
    }
}