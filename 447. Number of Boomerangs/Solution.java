// 447. 回旋镖的数量
/*
  暴力解法，没有做缓存的时候，超时了
  做了缓存就能ac了
  但是正确做法肯定不是这样的
  执行用时：878 ms, 在所有 Java 提交中击败了5.87%的用户
*/
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int ret = 0;
        int[][] chart = new int[points.length][points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                chart[i][j] = i < j ? eulerDistance(points[i], points[j]) : chart[j][i];
            }
        }
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (j == i) {
                    continue;
                }
                int d1 = chart[i][j];
                for (int k = 0; k < points.length; k++) {
                    if (k == i || k == j) {
                        continue;
                    }
                    int d2 = chart[i][k];
                    if (d1 == d2) {
                        ret++;
                    }
                }
            }
        }
        return ret;
    }

    public int eulerDistance(int[] i, int[] j) {
        return (i[0] - j[0]) * (i[0] - j[0]) + (i[1] - j[1]) * (i[1] - j[1]);
    }
}
/*
  强化版，双层循环！之前是三层
  问题的关键在于，我们要对每一个i，算出其他点到它的距离
  然后找出相同的距离，并统计各个距离重复的次数
  接下来通过数学分析可知，n为重复次数，n * (n - 1)就是回旋镖的数量
  另外一个优化在于，第二层循环里面本应该是有两个并排的循环
  一个用来算距离，另一个用来遍历重复的次数进而计算回旋镖的数量
  但是这个循环其实可以融合到上面的循环中
  每次重复次数更新，就先减掉以前的，再加上新的
  这样就可以避免第二个循环了
  另外有一个小优化就是chart缓存，在i > j的时候，不需要计算，可以直接查表
  超过了大多数人，还是很骄傲的
  执行用时：111 ms, 在所有 Java 提交中击败了83.24%的用户
*/
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int ret = 0;
        int[][] chart = new int[points.length][points.length];
        for (int i = 0; i < points.length; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                chart[i][j] = i < j ? eulerDistance(points[i], points[j]) : chart[j][i];
                if (map.containsKey(chart[i][j])) {
                    map.put(chart[i][j], map.get(chart[i][j]) + 1);
                } else {
                    map.put(chart[i][j], 1);
                }
            }
            for (int n : map.values()) {
                ret += n * (n - 1);
            }
        }
        return ret;
    }

    public int eulerDistance(int[] i, int[] j) {
        return (i[0] - j[0]) * (i[0] - j[0]) + (i[1] - j[1]) * (i[1] - j[1]);
    }
}
