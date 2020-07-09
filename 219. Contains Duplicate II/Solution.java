//219. 存在重复元素 II
/*
  双层循环嵌套，并且由于有k的约束，并不需要N ^ 2的复杂度，而是O(kN)
  执行用时：1739 ms 已经战胜 10.79 % 的 java 提交记录
*/
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length && j <= i + k; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
/*
  双层循环嵌套的问题多想一想Map
  记录每个数字的index，第二次遇见的话，在k的范围内就返回true，否则更新index
  O(N)即可判断
  执行用时：9 ms 已经战胜 92.10 % 的 java 提交记录
*/
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            Integer index = map.get(nums[i]);
            if (index == null) {
                map.put(nums[i], i);
            } else {
                if (i - index <= k) {
                    return true;
                } else {
                    map.put(nums[i], i);
                }
            }
        }
        return false;
    }
}
