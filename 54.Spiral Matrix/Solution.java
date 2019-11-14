//54. 螺旋矩阵
/*
  题目很容易理解
  一开始想用坐标，然后限制已遍历的范围去做，想想都觉得麻烦（现在我在写题解的时候又想到一种维护另一个布尔数组来标注已遍历的方法，好写一些）
  后来想，干脆转化成list，然后遍历完了就删
  所以这个解法用的是list，当然效率是可想而知的慢一点点
*/
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
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

        return result;
    }
}
