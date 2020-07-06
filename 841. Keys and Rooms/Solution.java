//841. 钥匙和房间
/*
  广度遍历
  更快的方法有用递归DFS的，不争辩了，都行
  我觉得这题用BFS很自然，DFS的话有点强行了
  执行用时：3 ms 已经战胜 40.18 % 的 java 提交记录
*/
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>(rooms.size());
        visited.add(0);
        queue.offer(0);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            List<Integer> roomList = rooms.get(cur);
            for (Integer i : roomList) {
                if (!visited.contains(i)) {
                    visited.add(i);
                    queue.offer(i);
                }
            }
        }
        return visited.size() == rooms.size();
    }
}
