// 18. 四数之和
/*
  跟三数之和一样的思路
  排序是为了更方便的去重
  原本四层递归，最后两层用双向指针
  执行用时：13 ms, 在所有 Java 提交中击败了69.71%的用户
*/
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i - 1 >= 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int m = j + 1;
                int n = nums.length - 1;
                // System.out.println(i + " " + j + " " + m + " " + n);
                while (m < n) {
                    int sum = nums[i] + nums[j] + nums[m];
                    if (sum + nums[m] > target) {
                        break;
                    }
                    while (n >= 0 && sum + nums[n] > target) {
                        n--;
                    }
                    if (m >= n) {
                        break;
                    }
                    if (sum + nums[n] == target) {
                        List<Integer> tmp = new ArrayList<>(4);
                        tmp.add(nums[i]);
                        tmp.add(nums[j]);
                        tmp.add(nums[m]);
                        tmp.add(nums[n]);
                        ret.add(tmp);
                    }
                    m++;
                    while (m < nums.length && nums[m] == nums[m - 1]) {
                        m++;
                    }
                }
            }
        }
        return ret;
    }
}