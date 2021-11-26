/*
  没有太get到这个题想考什么
  但是我先用普通的遍历走了一遍
  需要注意的就是控制下一步的走势
  一般往右走，不行了就换下一行，然后酌情向左调整位置
  可以想到的优化包括：
  通过这一行的开头两个数快速判断这一行还有没有推导的价值
  查找的时候可以用二分而不是逐个遍历
  但是鉴于在这个题已经best了，所以也没啥价值
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
  执行用时：5 ms, 在所有 Java 提交中击败了96.01%的用户
*/
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int i = 0;
        int j = 0;
        while (true) {
            if (matrix[i][j] < target) {
                if (j + 1 < matrix[i].length) {
                    j++;
                } else {
                    if (i + 1 < matrix.length) {
                        i++;
                    } else {
                        return false;
                    }
                    while (j > 0 && matrix[i][j] > target) {
                        j--;
                    }
                }
            } else if (matrix[i][j] == target){
                return true;
            } else {
                if (i + 1 < matrix.length) {
                    i++;
                } else {
                    return false;
                }
                while (j > 0 && matrix[i][j] > target) {
                    j--;
                }
            }
        }
    }
}
