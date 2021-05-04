// 797. 所有可能的路径
/*
  需要记录轨迹的递归，印象中类似的题做过，但是这次做的这个，好久没做了，有点生疏了，写了好久
  需要注意的是，记录轨迹的数组，深拷贝浅拷贝那里
  执行用时：3 ms, 在所有 Java 提交中击败了93.01%的用户
*/
class Solution {
    public int[][] graph;
    public int n;
    public List<List<Integer>> answer;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        this.graph = graph;
        this.n = graph.length - 1;
        this.answer = new ArrayList<>();

        for (int i : graph[0]) {
            List<Integer> track = new ArrayList<>();
            track.add(0);
            dfs(i, track);
        }
        return answer;
    }

    public void dfs(int i, List<Integer> track) {
        track.add(i);

        if (i == n) {
            answer.add(track);
            return;
        }

        for (int m : graph[i]) {
            dfs(m, new ArrayList<>(track));
        }
    }
}
/*
  这是跟ybb一起刷的第二道题，受到了她的回溯启发，把深拷贝环节放到了后面做，通过回溯操作，避免了很多深拷贝操作，实践证明更快
  执行用时：2 ms, 在所有 Java 提交中击败了99.90%的用户
*/
class Solution {
    public int[][] graph;
    public int n;
    public List<List<Integer>> answer;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        this.graph = graph;
        this.n = graph.length - 1;
        this.answer = new ArrayList<>();

        List<Integer> track = new ArrayList<>();
        track.add(0);
        for (int i : graph[0]) {
            dfs(i, track);
        }
        return answer;
    }

    public void dfs(int i, List<Integer> track) {
        track.add(i);

        if (i == n) {
            answer.add(new ArrayList<>(track));
            track.remove(track.size() - 1);
            return;
        }

        for (int m : graph[i]) {
            dfs(m, track);
        }

        track.remove(track.size() - 1);
    }
}
