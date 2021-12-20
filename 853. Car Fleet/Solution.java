// 853. 车队
/*
  首先要将题目给出的信息转化为，每辆车的到达时间，这个才是对我们解题最有用的信息
  受限于起点位置，在后面的车，就算到达时间更快，也会被前面的车挡住
  所以只需要根据起点位置排序，然后倒序遍历，比较到达时间，就能区分出车队
  根据实际需求，选用了TreeMap作为存储结构，应该是合理的
  执行用时：89 ms, 在所有 Java 提交中击败了26.79%的用户
*/
class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        Map<Integer, Double> map = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < position.length; i++) {
            map.put(position[i], (double)(target - position[i]) / speed[i]);
        }
        int answer = 0;
        Double captain = 0.0;
        for (Double d : map.values()) {
            if (d > captain) {
                answer++;
                captain = d;
            }
        }
        return answer;
    }
}
/*
  可以看到上一个方案的时间并不理想，这时候我就开始怀疑，难道说TreeMap在遍历的时候有损耗？
  我还没有仔细思考，就写了一个List来存，放弃了Map结构
  因为我本来只用到了有序这一点，与其在添加的时候保持有序，不如全部添加之后重新排序，应该区别不大，并且还能在遍历的时候保持简洁
  结果。。。并没有
  这时要先回答第一个问题，TreeMap遍历到底有没有损耗？答案是没有
  TreeMap本质上是一个自平衡的二叉搜索树，遍历就是O(N)
  这时候我就怀疑人生了，为什么这么慢？然后我去看了题解，跟我的方法是一模一样的
  最近接连出现这种情况，方法一样但是耗时差距巨大，我怀疑是LeetCode壮大了自己的测试用例，导致我再提交，时间就远比不上前人
  执行用时：140 ms, 在所有 Java 提交中击败了5.02%的用户
*/
class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        List<List<Double>> list = new ArrayList<>(position.length);
        for (int i = 0; i < position.length; i++) {
            List<Double> tmp = new ArrayList<>(2);
            tmp.add((double)position[i]);
            tmp.add((double)(target - position[i]) / speed[i]);
            list.add(tmp);
        }
        Collections.sort(list, new Comparator<List<Double>>() {
            @Override
            public int compare(List<Double> o1, List<Double> o2) {
                return o2.get(0).intValue() - o1.get(0).intValue();
            }
        });
        int answer = 0;
        Double captain = 0.0;
        for (List<Double> l : list) {
            if (l.get(1) > captain) {
                answer++;
                captain = l.get(1);
            }
        }
        return answer;
    }
}
