//56. 合并区间
/*
  双层循环嵌套，检查两两之间是否存在交集，如果存在则合并
  小技巧就是，把前面的区间合并到后面去
  然后将前面的结点删除，下次循环还从这个索引开始
  执行用时：7 ms 已经战胜 88.21 % 的 java 提交记录
*/
class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> input = new ArrayList<>(intervals.length);
        for (int[] interval : intervals) {
            input.add(interval);
        }
        for (int i = 0; i < input.size(); i++) {
            for (int j = i + 1; j < input.size(); j++) {
                if (isIntersection(input.get(i), input.get(j))) {
                    int[] newSet = {Math.min(input.get(i)[0], input.get(j)[0]), Math.max(input.get(i)[1], input.get(j)[1])};
                    input.set(j, newSet);
                    input.remove(i);
                    i--;
                    break;
                }
            }
        }
        int[][] result = new int[input.size()][2];
        for (int i = 0; i < result.length; i++) {
            result[i] = input.get(i);
        }
        return result;
    }
    
    private boolean isIntersection(int[] a, int[] b) {
        return (a[0] <= b[0] && b[0] <= a[1]) || (a[0] <= b[1] && b[1] <= a[1]) || (a[0] >= b[0] && a[1] <= b[1]) || (b[0] >= a[0] && b[1] <= a[1]);
    }
}
