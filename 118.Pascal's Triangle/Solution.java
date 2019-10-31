//118. 杨辉三角
/*
  因为要返回所有层数的杨辉三角，所以每一次都得开一个空间
  也就不需要考虑什么节省的问题了
  下一层由上一层求得，没什么好讲的
  执行用时：1 ms 已经战胜 98.84 % 的 java 提交记录
*/
class Solution {
    public List<List<Integer>> generate(int numRows) {
        if (numRows < 0) {
            return null;
        }
        List<List<Integer>> result = new ArrayList<>(numRows);
        if (numRows >= 1) {
            List<Integer> tmp = new ArrayList<>(1);
            tmp.add(1);
            result.add(tmp);
        }
        if (numRows >= 2) {
            List<Integer> tmp = new ArrayList<>(2);
            tmp.add(1);
            tmp.add(1);
            result.add(tmp);
        }
        if (numRows > 2) {
            for (int i = 3; i <= numRows; i++) {
                List<Integer> tmp = new ArrayList<>(i);
                tmp.add(1);
                for (int j = 1; j < i - 1; j++) {
                    tmp.add(result.get(i - 2).get(j - 1) + result.get(i - 2).get(j));
                }
                tmp.add(1);
                result.add(tmp);
            }
        }
        return result;
    }
}
