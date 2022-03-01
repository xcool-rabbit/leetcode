// 218. 天际线问题
/*
  看的题解
  自己想了一些前缀和以及数组的方案，但是呢，由于数据范围过大，数组行不通，前缀和也不成立
  应该能想到暴力方案的，拿着纵坐标跟所有的建筑物去比对，找出这个点的最高值
  基于此进行优化
  用堆来维护最高值
  纵坐标排序
  堆的维护也是技巧，按需增删，没有必要一过期就删掉
  因为纵坐标排过序，所以，被删掉的building再也不会需要了，非常的巧妙
  执行用时：15 ms, 在所有 Java 提交中击败了88.83%的用户
*/
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<Integer> verticle = new ArrayList<>(buildings.length * 2);
        for (int[] building : buildings) {
            verticle.add(building[0]);
            verticle.add(building[1]);
        }
        Collections.sort(verticle);
        Queue<int[]> heap = new PriorityQueue<>((b1, b2) -> (b2[2] - b1[2]));
        int index = 0;
        List<List<Integer>> ret = new ArrayList<>();
        for (int v : verticle) {
            while (index < buildings.length && buildings[index][0] <= v) {
                heap.add(buildings[index]);
                index++;
            }
            while (!heap.isEmpty() && heap.peek()[1] <= v) {
                heap.poll();
            }
            int height;
            if (heap.isEmpty()) {
                height = 0;
            } else {
                height = heap.peek()[2];
            }
            if (ret.size() == 0 || ret.get(ret.size() - 1).get(1) != height) {
                List<Integer> tmp = new ArrayList<>(2);
                tmp.add(v);
                tmp.add(height);
                ret.add(tmp);
            }
        }
        return ret;
    }
}
