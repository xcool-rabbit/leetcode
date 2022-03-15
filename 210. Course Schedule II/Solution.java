// 210. 课程表 II
/*
  207题的变种，dfs的最后，将结点入队列即可
  因为我是按照出度建图的，第一个入队的是出度为零的，是应该最先学的
  执行用时：2 ms, 在所有 Java 提交中击败了99.39%的用户
*/
class Solution {
    int[] ret;
    int index;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ret = new int[numCourses];
        index = 0;
        List<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] prerequisite : prerequisites) {
            graph[prerequisite[0]].add(prerequisite[1]);
        }
        int[] visited = new int[numCourses];
        boolean noCircle = true;
        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == 0) {
                noCircle = dfs(graph, i, visited);
            }
            if (!noCircle) {
                return new int[0];
            }
        }
        return ret;
    }

    public boolean dfs(List<Integer>[] graph, int index, int[] visited) {
        List<Integer> node = graph[index];
        boolean ret = true;
        visited[index] = 1;
        for (int i : node) {
            if (visited[i] == 0) {
                ret = dfs(graph, i, visited);
                if (!ret) {
                    return ret;
                }
            } else if (visited[i] == 1) {
                return false;
            }
        }
        visited[index] = 2;
        this.ret[this.index++] = index;
        return ret;
    }
}
