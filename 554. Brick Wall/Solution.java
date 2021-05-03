// 554. 砖墙
/*
  遍历每一行，统计墙缝，记录墙缝数量
  注意：要用字典，因为数据范围不允许用数组
  执行用时：13 ms, 在所有 Java 提交中击败了94.28%的用户
*/
class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> chart = new HashMap<>();
        int curLength = 0;
        for (List<Integer> line : wall) {
            curLength = 0;
            for (int i = 0; i < line.size() - 1; i++) {
                curLength += line.get(i);
                if (chart.containsKey(curLength)) {
                    chart.put(curLength, chart.get(curLength) + 1);
                } else {
                    chart.put(curLength, 1);
                }
            }
        }
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : chart.entrySet()) {
            max = entry.getValue() > max ? entry.getValue() : max;
        }
        return wall.size() - max;
    }
}
