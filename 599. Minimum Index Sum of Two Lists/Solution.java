//599. 两个列表的最小索引总和
/*
  不要用N ^ 2的遍历哦，用Map查找更快！
  感觉最近做的这些题，很多都很颠覆传统观念，hash永远滴神！
  空间换时间NB
  有一个可以优化的细节：把较长的那个做成Map，我没弄
  执行用时：9 ms 已经战胜 87.80 % 的 java 提交记录
*/
class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>(list1.length);
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        int sum = Integer.MAX_VALUE;
        List<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < list2.length; i++) {
            Integer tmp = map.get(list2[i]);
            if (tmp != null) {
                if (tmp + i < sum) {
                    indexList.clear();
                    sum = tmp + i;
                    indexList.add(tmp);
                } else if (tmp + i == sum) {
                    indexList.add(tmp);
                }
            }
        }
        String[] ans = new String[indexList.size()];
        int i = 0;
        for (int index : indexList) {
            ans[i++] = list1[index];
        }
        return ans;
    }
}
