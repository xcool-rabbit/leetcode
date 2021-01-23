//421. 数组中两个数的最大异或值
/*
  最简单的暴力解法，双重循环
  539 ms,在所有Java提交中击败了5.13%的用户
*/
class Solution {
    public int findMaximumXOR(int[] nums) {
        int max = 0;
        int cur;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                cur = nums[i] ^ nums[j];
                max = cur > max ? cur : max;
            }
        }
        return max;
    }
}
/*
  根据异或的特点，异或的最大值需要有一个最高位的参与，才有可能结果最大
  所以可以先找出具有最高位的数
  这样可以缩小双层遍历的范围
  63 ms,在所有Java提交中击败了45.73%的用户
*/
class Solution {
    public int findMaximumXOR(int[] nums) {
        int max = 0;
        int cur;
        List<Integer> maxs = findMaxs(nums);
        for (int n : maxs) {
            for (int i = 0; i < nums.length; i++) {
                cur = n ^ nums[i];
                max = cur > max ? cur : max;
            }
        }
        return max;
    }

    private List<Integer> findMaxs(int[] nums) {
        int power = 0;
        List<Integer> list = new ArrayList<>();
        for (int n : nums) {
            if (n >= Math.pow(2, power + 1)) {
                list.clear();
                list.add(n);
                power = log2(n);
            } else if (n >= Math.pow(2, power)) {
                list.add(n);
            }
        }
        return list;
    }

    private int log2(int n) {
        return (int)(Math.log(n) / Math.log(2));
    }
}
/*
  对指数操作做了个缓存
  57 ms,在所有Java提交中击败了49.41%的用户
*/
class Solution {
    public int findMaximumXOR(int[] nums) {
        int max = 0;
        int cur;
        List<Integer> maxs = findMaxs(nums);
        for (int n : maxs) {
            for (int i = 0; i < nums.length; i++) {
                cur = n ^ nums[i];
                max = cur > max ? cur : max;
            }
        }
        return max;
    }

    private List<Integer> findMaxs(int[] nums) {
        int power = 0;
        List<Integer> list = new ArrayList<>();
        for (int n : nums) {
            if (n >= Math.pow(2, power + 1)) {
                list.clear();
                list.add(n);
                power = log2(n);
            } else if (n >= Math.pow(2, power)) {
                list.add(n);
            }
        }
        return list;
    }

    private int log2(int n) {
        return (int)(Math.log(n) / Math.log(2));
    }
}
/*
  
*/