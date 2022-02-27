// 378. 有序矩阵中第 K 小的元素
/*
  我想到了这种方法，行与行之间做比较，每次选最小的一个
  但是题目上要求不用额外空间，我就没写，直接去看题解了
  题解二是我思想的优化版
  每次选最小的，这一步可以用堆，从O(n)降到O(logn)
  执行用时：18 ms, 在所有 Java 提交中击败了17.87%的用户
*/
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        Queue<int[]> queue = new PriorityQueue<>(
            (e1, e2) -> (e1[0] - e2[0])
        );
        for (int i = 0; i < matrix.length; i++) {
            queue.add(new int[]{matrix[i][0], i, 0});
        }
        while (k > 1) {
            int[] cur = queue.poll();
            if (cur[2] + 1 < matrix.length) {
                cur[0] = matrix[cur[1]][cur[2] + 1];
                cur[2]++;
                queue.add(cur);
            }
            k--;
        }
        return queue.peek()[0];
    }
}
/*
  题解还有一个说起来最厉害的做法但是，我觉得有局限性，不稳定，跟数据集强相关
  二分
  mid的单元格的值
  可以划线找到大于等于mid的区域
  求出mid是第几大
  二分就能锁定答案
  复杂度是O(nlogk)，k是最大值最小值的差，这个太不稳定了
  并且感觉也挺难写的，就不实现了
*/
