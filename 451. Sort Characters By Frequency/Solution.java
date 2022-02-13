// 451. 根据字符出现频率排序
/*
  朴实无华的怎么要求怎么做
  统计出每个字符的出现次数，用Map存储
  然后把Entry拿出来排个序
  拼装成需要的字符串
  但是，还能怎么快呢？
  执行用时：21 ms, 在所有 Java 提交中击败了20.54%的用户
*/
class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            } else {
                map.put(s.charAt(i), 1);
            }
        }
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((e1, e2) -> e2.getValue() - e1.getValue());
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, Integer> e : list) {
            int n = e.getValue();
            while (n-- > 0) {
                sb.append(e.getKey());
            }
        }
        return sb.toString();
    }
}
/*
  我上面那个解法跟官方的解法一是一样的，看来并没有什么奇淫巧技
  官方的解法二是桶排序
  emmmm怎么说呢，一言难尽
  这题范围什么的也都给的不是很严谨，还用这种没有水平的桶排，算啦算啦，不写啦
*/