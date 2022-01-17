// 15. 三数之和
/*
  利用map缩短查找第三个数的之间，算法基本上是O(n ^ 2)
  另外做了一个特殊优化，map里记录的数，最多记录3个索引，再多的也没有意义
  勉强过关
  执行用时：465 ms, 在所有 Java 提交中击败了7.89%的用户
*/
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> tmp;
            if (!map.containsKey(nums[i])) {
                tmp = new ArrayList<>();
            } else {
                tmp = map.get(nums[i]);
            }
            if (tmp.size() < 3) {
                tmp.add(i);
            }
            map.put(nums[i], tmp);
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (map.containsKey(-nums[i] - nums[j])) {
                    boolean match = false;
                    List<Integer> tmp = map.get(-nums[i] - nums[j]);
                    for (Integer n : tmp) {
                        if (n > j) {
                            match = true;
                            break;
                        }
                    }
                    if (match) {
                        tmp = new ArrayList<>(3);
                        tmp.add(nums[i]);
                        tmp.add(nums[j]);
                        tmp.add(-nums[i] - nums[j]);
                        Collections.sort(tmp);
                        set.add(tmp);
                    }
                }
            }
        }
        List<List<Integer>> ret = new ArrayList<>(set);
        return ret;
    }
}
/*
  看到了题解的一句话，启发很大：排序有助于去重
  所以排序可以给我们的优化带来什么好处呢？
  map记录不需要记录列表了，我们只需要知道最后一个索引
  双层循环可以快速跳过一样的数
  第三层循环（查找）也可以快速返回，就是当三者之和已经大于0的时候，j就没有往后遍历的必要了，直接break，寻找下一个i
  因为数组有序，所以循环遍历一遍不会遇到重复，根本不用去重
  但是郁闷的是，我的算法明明也是O(n ^ 2)，为什么还是没有题解快呢？只能理解为map的操作比我想象中的要更耗时间，并不能达到常数级别，出现了退化
  执行用时：95 ms, 在所有 Java 提交中击败了12.37%的用户
*/
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            if (i - 1 >= 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (j - 1 > i && nums[j] == nums[j - 1]) {
                    continue;
                }
                if (map.containsKey(-nums[i] - nums[j])) {
                    if (map.get(-nums[i] - nums[j]) > j) {
                        List<Integer> tmp = new ArrayList<>(3);
                        tmp.add(nums[i]);
                        tmp.add(nums[j]);
                        tmp.add(-nums[i] - nums[j]);
                        ret.add(tmp);
                    }
                }
            }
        }
        return ret;
    }
}
/*
  题解方法，纯纯的O(n ^ 2)
  妙就妙在，第二三层循环，它变成了一个双指针问题
  就因为排过序
  执行用时：20 ms, 在所有 Java 提交中击败了83.26%的用户
*/
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i - 1 >= 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j];
                if (sum + nums[j] > 0) {
                    break;
                }
                while (k >= 0 && sum + nums[k] > 0) {
                    k--;
                }
                if (j >= k) {
                    break;
                }
                if (sum + nums[k] == 0) {
                    List<Integer> tmp = new ArrayList<>(3);
                    tmp.add(nums[i]);
                    tmp.add(nums[j]);
                    tmp.add(nums[k]);
                    ret.add(tmp);
                }
                j++;
                while (j < nums.length && nums[j] == nums[j - 1]) {
                    j++;
                }
            }
        }
        return ret;
    }
}
