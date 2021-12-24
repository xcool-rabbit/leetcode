// 46. 全排列
/*
  递归的思想，每次多一个元素，其实是在之前的所有情况中，每个位置都插入，就能产生全排列
  执行用时：1 ms, 在所有 Java 提交中击败了81.93%的用户
*/
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        LinkedList<List<Integer>> ret = new LinkedList<>();
        List<Integer> tmp = new LinkedList<>();
        tmp.add(nums[0]);
        ret.offer(tmp);
        while (ret.peek().size() < nums.length) {
            List<Integer> l = ret.poll();
            for (int j = 0; j <= l.size(); j++) {
                tmp = new LinkedList<>(l);
                tmp.add(j, nums[l.size()]);
                ret.offer(tmp);
            }
        }
        return ret;
    }
}
