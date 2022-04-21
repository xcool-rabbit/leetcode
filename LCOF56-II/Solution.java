// 剑指 Offer 56 - II. 数组中数字出现的次数 II
/*
  用set是最呆的
  执行用时：10 ms, 在所有 Java 提交中击败了41.16%的用户
*/
class Solution {
    public int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        Set<Integer> ret = new HashSet<>(nums.length);
        for (int n : nums) {
            if (set.contains(n)) {
                ret.remove(n);
            } else {
                set.add(n);
                ret.add(n);
            }
        }
        return new ArrayList<>(ret).get(0);
    }
}
/*
  牛逼牛逼，这么一个不起眼的题，居然可以这么厉害
  下面两种优秀的方法，都是基于这个思路：
  一个数是32位，每一位是0或1
  一个数组有n个数，每一位出现的总次数是可以求的
  每一位的总次数，是3的倍数，那么就说明出现了3次
  那些次数不是3的倍数的位，就是那个数
*/
/*
  一种人类都能想得到的方法就是，遍历数组中的每个数的每一位
  统计每一位的出现总次数
  时间复杂度是O(32n)
  快是因为，即使计算32次，也比HashMap快
  执行用时：3 ms, 在所有 Java 提交中击败了83.20%的用户
*/
class Solution {
    public int singleNumber(int[] nums) {
        int[] count = new int[32];
        for (int n : nums) {
            for (int i = 31; i >- 0; i--) {
                count[i] += (n & 1);
                n = n >>> 1;
            }
        }
        int ret = 0;
        for (int i = 0; i < count.length; i++) {
            ret = ret << 1;
            ret += (count[i] % 3);
        }
        return ret;
    }
}

/*
  一种非人类的做法，觉得每个数都要算32次，太繁琐了
  有限状态自动机！
  对于一个数位来讲，次数的余数，只有0,1,2三种
  我们用00,01,10来表示这三种状态
  然后搞清楚状态转换的表示（太难了太难了，梦回数字逻辑，推不出来了）
*/
