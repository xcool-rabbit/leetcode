// 面试题 17.24. 最大子矩阵
/*
  看到这道题想到的首先是动态规划，但是想了半天也没想明白怎么在二维空间下动态
  结果一看题解，原来就是把一维的又调用了n ^2遍。。。
  这是偷懒的写法，每次重新计算一段的和，果不其然很慢
  执行用时：1342 ms, 在所有 Java 提交中击败了5.09%的用户
*/
class Solution {
    public int[] getMaxMatrix(int[][] matrix) {
        int max = matrix[0][0];
        int ret[] = new int[4];
        int r1, c1, r2, c2;
        int rowA = 0;
        while (rowA < matrix.length) {
            int rowB = rowA + 1;
            while (rowB <= matrix.length) {
                r1 = rowA;
                c1 = 0;
                r2 = rowB - 1;
                int[] arr = new int[matrix[rowA].length];
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = 0;
                    for (int j = rowA; j < rowB; j++) {
                        arr[i] += matrix[j][i];
                    }
                }
                int[] dp = new int[arr.length];
                for (int i = 0; i < dp.length; i++) {
                    if (i == 0) {
                        dp[i] = arr[i];
                    } else {
                        if (0 > dp[i - 1]) {
                            dp[i] = arr[i];
                            c1 = i;
                        } else {
                            dp[i] = dp[i - 1] + arr[i];
                        }
                    }
                    if (dp[i] > max) {
                        ret[0] = r1;
                        ret[1] = c1;
                        ret[2] = r2;
                        ret[3] = i;
                        max = dp[i];
                    }
                }
                rowB++;
            }
            rowA++;
        }
        return ret;
    }
}
/*
  优化版本，自然是利用了上次arr数组，再加一次就能给这次用了
  执行用时：71 ms, 在所有 Java 提交中击败了12.28%的用户
*/
class Solution {
    public int[] getMaxMatrix(int[][] matrix) {
        int max = matrix[0][0];
        int ret[] = new int[4];
        int r1, c1, r2, c2;
        int rowA = 0;
        while (rowA < matrix.length) {
            int rowB = rowA + 1;
            int[] arr = new int[matrix[rowA].length];
            while (rowB <= matrix.length) {
                r1 = rowA;
                c1 = 0;
                r2 = rowB - 1;
                for (int i = 0; i < arr.length; i++) {
                    arr[i] += matrix[rowB - 1][i];
                }
                int[] dp = new int[arr.length];
                for (int i = 0; i < dp.length; i++) {
                    if (i == 0) {
                        dp[i] = arr[i];
                    } else {
                        if (0 > dp[i - 1]) {
                            dp[i] = arr[i];
                            c1 = i;
                        } else {
                            dp[i] = dp[i - 1] + arr[i];
                        }
                    }
                    if (dp[i] > max) {
                        ret[0] = r1;
                        ret[1] = c1;
                        ret[2] = r2;
                        ret[3] = i;
                        max = dp[i];
                    }
                }
                rowB++;
            }
            rowA++;
        }
        return ret;
    }
}
/*
  我不理解为什么我的优化版本还是这么慢
  这时我想到，可以存一个sum矩阵，这样可以一次算出任意一个区间里，这些数的和
  但是我转念一想，这也没解决问题啊，我之前也是每个循环只需要算一次，那个是求和，这个是作差，没区别
  于是就，放弃了这个想法
*/
class Solution {
    public int[] getMaxMatrix(int[][] matrix) {
        int max = matrix[0][0];
        int ret[] = new int[4];
        int r1, c1, r2, c2;
        int rowA = 0;
        int sum[][] = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == 0) {
                    sum[i][j] = matrix[i][j];
                } else {
                    sum[i][j] = sum[i - 1][j] + matrix[i][j];
                }
            }
        }
        while (rowA < matrix.length) {
            int rowB = rowA + 1;
            int[] arr = new int[matrix[rowA].length];
            while (rowB <= matrix.length) {
                r1 = rowA;
                c1 = 0;
                r2 = rowB - 1;
                for (int i = 0; i < arr.length; i++) {
                    arr[i] += matrix[rowB - 1][i];
                }
                int[] dp = new int[arr.length];
                for (int i = 0; i < dp.length; i++) {
                    if (i == 0) {
                        dp[i] = arr[i];
                    } else {
                        if (0 > dp[i - 1]) {
                            dp[i] = arr[i];
                            c1 = i;
                        } else {
                            dp[i] = dp[i - 1] + arr[i];
                        }
                    }
                    if (dp[i] > max) {
                        ret[0] = r1;
                        ret[1] = c1;
                        ret[2] = r2;
                        ret[3] = i;
                        max = dp[i];
                    }
                }
                rowB++;
            }
            rowA++;
        }
        return ret;
    }
}