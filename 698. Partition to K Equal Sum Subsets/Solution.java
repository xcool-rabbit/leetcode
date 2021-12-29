// 698. 划分为k个相等的子集
/*
  遍历，回溯
  还是有点大循环套小循环的意思，一开始有点没想清楚，还以为写不了
  if (target == 0)就是下一次大循环的开始（寻找下一个子集）
  for循环是在找子集内的元素
  可谓是妙蛙，一个函数里面能嵌套两个递归
  不过这个递归写的还是有点丑陋的，为了偷懒少传参，设置了很多全局变量
  尤其是在快速返回ret的时候，显得很狼狈
  这个需要优化，倒序排列，从大数开始凑，更容易满足
  执行用时：1 ms, 在所有 Java 提交中击败了90.22%的用户
*/
class Solution {
    int[] nums;
    Map<Integer, List<Integer>> map;
    boolean[] b;
    int avg;
    int remain;
    boolean ret;
    public boolean canPartitionKSubsets(int[] nums, int k) {
        this.nums = nums;
        int max = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            sum += nums[i];
        }
        avg = sum / k;
        if (sum % k != 0 || max > avg) {
            return false;
        }
        Arrays.sort(nums);
        b = new boolean[nums.length];
        remain = k;
        findTarget(avg);
        return ret;
    }

    public void findTarget(int target) {
        if (remain == 0) {
            ret = true;
            return;
        }
        if (target == 0) {
            remain--;
            findTarget(avg);
            if (ret) {
                return;
            }
            remain++;
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if (b[i]) {
                continue;
            }
            if (nums[i] <= target) {
                b[i] = true;
                findTarget(target - nums[i]);
                if (ret) {
                    return;
                }
                b[i] = false;
            }
        }
        return;
    }
}
/*
  其实上面的写法是存在一些问题的
  如果大循环findTarget失败一次，还会走进for循环，这样应该会导致重复计算
  所以大循环的findTarget，无论成功与否，都应该恢复remain并return
  因为
  如果成功了，ret早就置为true了，remain再怎么改也没影响
  如果失败了，是需要恢复remain的
  执行用时：1 ms, 在所有 Java 提交中击败了90.22%的用户
*/
class Solution {
    int[] nums;
    boolean[] b;
    int avg;
    int remain;
    boolean ret;
    public boolean canPartitionKSubsets(int[] nums, int k) {
        this.nums = nums;
        int max = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            sum += nums[i];
        }
        avg = sum / k;
        if (sum % k != 0 || max > avg) {
            return false;
        }
        Arrays.sort(nums);
        b = new boolean[nums.length];
        remain = k;
        return findTarget(avg);
    }

    public boolean findTarget(int target) {
        if (remain == 0) {
            return true;
        }
        if (target == 0) {
            remain--;
            if (findTarget(avg)) {
                return true;
            } else {
                remain++;
                return false;
            }
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if (b[i]) {
                continue;
            }
            if (nums[i] <= target) {
                b[i] = true;
                if (findTarget(target - nums[i])) {
                    return true;
                }
                b[i] = false;
            }
        }
        return false;
    }
}
/*
  终极版本，还是觉得上面的写法不够优雅
  这是递归带返回值的版本
  写递归的时候写成void是为了让自己能更好的思考逻辑，不要太在意传参的设计
  等到逻辑写好了，再回来考虑参数和返回值是否合理
  这个递归，不关注分出来的子集都有谁，只关心能不能分，所以可以把返回值设成boolean
  通过返回值就能传递信息，不需要依靠在每次递归之后去判断全局变量ret来决定是否要终止遍历，有点难懂，但是更加优雅
  执行用时：1 ms, 在所有 Java 提交中击败了90.22%的用户
*/
class Solution {
    int[] nums;
    boolean[] b;
    int avg;
    int remain;
    boolean ret;
    public boolean canPartitionKSubsets(int[] nums, int k) {
        this.nums = nums;
        int max = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            sum += nums[i];
        }
        avg = sum / k;
        if (sum % k != 0 || max > avg) {
            return false;
        }
        Arrays.sort(nums);
        b = new boolean[nums.length];
        remain = k;
        return findTarget(avg);
    }

    public boolean findTarget(int target) {
        if (remain == 0) {
            return true;
        }
        if (target == 0) {
            remain--;
            if (findTarget(avg)) {
                return true;
            } else {
                remain++;
                return false;
            }
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if (b[i]) {
                continue;
            }
            if (nums[i] <= target) {
                b[i] = true;
                if (findTarget(target - nums[i])) {
                    return true;
                }
                b[i] = false;
            }
        }
        return false;
    }
}
