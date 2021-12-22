// 78. 子集
/*
  递归的思想，但是没有用递归的写法
  也有一点dp的增长的思想
  另外要注意的就是关于List的问题，不要单纯的复制成引用了，要new一个新的对象
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>((int)Math.pow(2, nums.length));
        List<Integer> tmp = new ArrayList<>(0);
        result.add(tmp);
        for (int i = 0; i < nums.length; i++) {
            int iterLength = result.size();
            for (int j = 0; j < iterLength; j++) {
                tmp = new ArrayList<>(result.get(j));
                tmp.add(nums[i]);
                result.add(tmp);
            }
        }
        return result;
    }
}
