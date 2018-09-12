//350. 两个数组的交集 II
/*
  利用HashMap将第一个数组进行记录，元素作为键，出现的次数作为值。
  遍历第二个数组，若该元素在map中存在键，且值大于0，则说明该元素为两个数组的交集。
  值的判断用来解决出现次数的问题。
  最终需要将List转化为数组。
  执行用时：4 ms 已经战胜 85.48 % 的 java 提交记录
*/
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        List<Integer> res = new ArrayList<Integer>();
        for (int i : nums1) {
            if (map.containsKey(i)) {
                map.put(i, (int)map.get(i) + 1);
            }
            else {
                map.put(i, 1);
            }
        }
        for (int i : nums2) {
            if (map.containsKey(i) && (int)map.get(i) > 0) {
                res.add(i);
                map.put(i, (int)map.get(i) - 1);
            }
        }
        int[] tmp = new int[res.size()];
        int index = 0;
        for (Integer i : res)
            tmp[index++] = (int)i;
        return tmp;
    }
}
