// 剑指 Offer II 007. 数组中和为 0 的三个数
/*
  同主站15，但是发现时间并不及格
  后来吸取了一下经验，发现tmp那里可以优化，不需要添加三次
  直接用Arrays.asList()
  看了一下Arrays.asList()的参数，是可变参数的T，所以这里有个隐式的转换
  但是注意这个ArrayList是Arrays类里的内部类，不支持增删
  执行用时：20 ms, 在所有 Java 提交中击败了86.70%的用户

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
                    // List<Integer> tmp = new ArrayList<>(3);
                    // tmp.add(nums[i]);
                    // tmp.add(nums[j]);
                    // tmp.add(nums[k]);
                    // ret.add(tmp);
                    ret.add(Arrays.asList(nums[i], nums[j], nums[k]));
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
