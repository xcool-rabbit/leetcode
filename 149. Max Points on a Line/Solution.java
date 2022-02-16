// 149. 直线上最多的点数
/*
  啊这。。。hard题，被我暴力就解出来啦？
  应该是数据集不够刁钻
  但是这个题给我了一个判断是否在同一条直线上的思路：比较斜率
  一个意外发现是关于分母0
  如果是double的话，是不会抛出异常的
  答案会是Infinity，并且还有正负
  利用这一点，可以处理竖线的情况
  我其实还储备了很多优化的点，比如说visited
  比如说有5个点在一条直线上，在我第一次把他们都找出来了以后
  后面再遇到这条直线上的其他点，就不需要进行遍历了，因为他们所在的直线之前已经统计过了
  另外有一个需要注意的问题就是，斜率的表示，最好还是用比值的形式而不是浮点数
  受限于浮点数的精度，并不能完美的表示所有斜率，判断上会出问题
  所以最好用比值的形式
  但是比值需要另一项处理，约分
  用gcd（最大公约数算法）找出最大公约数
  gcd是用除数除以余数
  执行用时：5 ms, 在所有 Java 提交中击败了84.12%的用户
*/
class Solution {
    public int maxPoints(int[][] points) {
        int ret = 1;
        for (int i = 0; i < points.length; i++) {
            boolean[] visited = new boolean[points.length];
            for (int j = i + 1; j < points.length; j++) {
                double k1 = getK(points[i], points[j]);
                int count = 2;
                for (int k = j + 1; k < points.length; k++) {
                    double k2 = getK(points[i], points[k]);
                    if (k1 == k2) {
                        count++;
                    }
                }
                ret = Math.max(ret, count);
            }
        }
        return ret;
    }

    public double getK(int[] i, int[] j) {
        return j[0] - i[0] == 0 ? (double)Math.abs(j[1] - i[1]) / 0 : (double)(j[1] - i[1]) / (j[0] - i[0]);
    }
}
