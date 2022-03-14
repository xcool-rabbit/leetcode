// 207. 课程表
/*
  拓扑排序！
  拓扑排序的本质是，排序好的顺序，指向关系不变
  比如u在v的前面，就表明，v是不可能指向u的
  因此，它可以用来解决依赖关系
  拓扑排序的实现有两种思路
  一种是入度，入度为0的，肯定排前面
  但是这种方法，从实战角度来讲，是相当难用的
  因为我们存储图，基本上是邻接表，邻接表是表示出度的
  那么第二种拓扑排序的思路是利用出度
  出度为0的，肯定排最后面
  同时我们的邻接表判断出度也非常的方便
  复杂度是O(n ^ 2)
  删除一个节点，同时要将其相关的边也删除
  边的查找需要O(n)，这还是优化过的，不然的话，要O(e)，e为边数
  执行用时：54 ms, 在所有 Java 提交中击败了5.04%的用户
*/
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Set<Integer>[] graph = new HashSet[numCourses];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new HashSet<>();
        }
        for (int[] prerequisite : prerequisites) {
            graph[prerequisite[0]].add(prerequisite[1]);
        }
        boolean[] deleted = new boolean[numCourses];
        int deleteCount = 0;
        while (deleteCount < numCourses) {
            int end = findEnd(graph, deleted);
            if (end == -1) {
                return false;
            }
            deleted[end] = true;
            deleteCount++;
            for (int i = 0; i < graph.length; i++) {
                if (deleted[i]) {
                    continue;
                }
                if (graph[i].contains(end)) {
                    graph[i].remove(end);
                }
            }
        }
        return true;
    }

    public int findEnd(Set<Integer>[] graph, boolean[] deleted) {
        for (int i = 0; i < graph.length; i++) {
            if (deleted[i]) {
                continue;
            }
            if (graph[i].size() == 0) {
                return i;
            }
        }
        return -1;
    }
}
/*
  并查集失败案例
  5
  [[1,4],[2,4],[3,1],[3,2]]
  失败原因是，并查集只能有一个爹
  上述例子，4有两个爹
  在无向图中，并不是非常在乎爹，爹只表示一种连通性
  如果是无向图，这个就用2 -> 1 -> 4连起来了
  但是有向图，他们并不是这样的关系，2跟1之间暂时没有关系
  所以，输出错误答案也就理所应当了
*/
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] parents = new int[numCourses];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
        for (int[] prerequisite : prerequisites) {
            int p1 = find(parents, prerequisite[0]);
            parents[prerequisite[0]] = p1;
            int p2 = find(parents, prerequisite[1]);
            parents[prerequisite[1]] = p2;
            if (p1 == p2) {
                return false;
            }
            union(parents, p1, p2);
        }
        return true;
    }

    public void union(int[] parents, int a, int b) {
        parents[b] = parents[a];
    }

    public int find(int[] parents, int index) {
        while (parents[index] != index) {
            index = parents[index];
        }
        return index;
    }
}
/*
  快是真的快
  拓扑排序请认准，深度遍历
  传统的拓扑排序需要频繁的删除，效率很低
  遍历其实隐含了删除的意义，过了就不会再访问
  第二点，是最精髓的，可谓是巧夺天工
  后序遍历，一个结点的所有子结点都遍历过了，然后自己其实就是出度为0了，就可以被当成排序结果输出了
  需要注意的地方是，这种遍历需要维护结点的状态
  结点一共有三种状态
  0 - 未被访问
  1 - 进了递归但还没输出
  2 - 已经输出
  其中，如果在遍历过程当中遇到了状态1的结点，那么说明，图里有环
  就是代码写的有点乱，很多地方为了快速返回，加了很多判断
  执行用时：2 ms, 在所有 Java 提交中击败了99.46%的用户
*/
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] prerequisite : prerequisites) {
            graph[prerequisite[0]].add(prerequisite[1]);
        }
        int[] visited = new int[numCourses];
        boolean ret = true;
        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == 0) {
                ret = dfs(graph, i, visited);
            }
            if (!ret) {
                return ret;
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
        return ret;
    }
}
