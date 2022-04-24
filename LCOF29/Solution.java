// 剑指 Offer 29. 顺时针打印矩阵
/*
  同主站54题
  那个要求返回值是list
  这个是数组
  懒得转了
  执行用时：6 ms, 在所有 Java 提交中击败了5.44%的用户
*/
class Solution {
    public int[] spiralOrder(int[][] matrix) {
        List<List<Integer>> list = new ArrayList<>(matrix.length);
        for (int i = 0; i < matrix.length; i++) {
            List<Integer> tmp = new ArrayList<>(matrix[i].length);
            for (int j = 0; j < matrix[i].length; j++) {
                tmp.add(matrix[i][j]);
            }
            list.add(tmp);
        }

        while (!list.isEmpty()) {
            result.addAll(list.remove(0));
            if (list.isEmpty()) {
                break;
            }
            Iterator<List<Integer>> it = list.iterator();
            while (it.hasNext()) {
                List<Integer> tmp = it.next();
                result.add(tmp.remove(tmp.size() - 1));
                if (tmp.isEmpty()) {
                    it.remove();
                }
            }
            if (list.isEmpty()) {
                break;
            }
            Collections.reverse(list.get(list.size() - 1));
            result.addAll(list.remove(list.size() - 1));
            if (list.isEmpty()) {
                break;
            }
            for (int i = list.size() - 1; i >= 0; i--) {
                result.add(list.get(i).remove(0));
                if (list.get(i).isEmpty()) {
                    list.remove(i);
                }
            }
        }
        int[] ret = new int[result.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = result.get(i);
        }
        return ret;
    }
}
