//217. 存在重复元素
/*
  利用Java自带的Set结构
  最初用的是从数组第一个元素开始，跟之后的所有数组进行比较，这样会超时的。。。
  话又说回来，set是怎么做到的，回头查查；查完回来了，有丶麻烦，还是等以后再说吧
  执行用时：18 ms 已经战胜 54.76 % 的 java 提交记录
*/
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i]))
                return true;
        }
        
        return false;
    }
}
