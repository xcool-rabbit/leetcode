// 684. 冗余连接
/*
  拓扑排序版本
  想的时候并不知道这是拓扑排序
  比较容易想到的是，如果一个结点只出现了一次，那它肯定不会有环，就把它删掉
  删掉最后剩下啥了呢，剩下环
  用一个桶记录每个点出现的次数，然后去遍历整个list去找它在哪里
  O(n ^ 2)
  执行用时：19 ms, 在所有 Java 提交中击败了5.02%的用户
*/
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int[] points = new int[1 + Math.max(1000, edges.length * 2)];
        List<int[]> list = new LinkedList<>();
        for (int[] edge : edges) {
            list.add(edge);
            points[edge[0]]++;
            points[edge[1]]++;
        }
        int delete;
        while ((delete = findOne(points)) != -1) {
            Iterator<int[]> it = list.iterator();
            while (it.hasNext()) {
                int[] cur = it.next();
                if (cur[0] == delete)  {
                    it.remove();
                    points[cur[1]]--;
                } else if (cur[1] == delete) {
                    it.remove();
                    points[cur[0]]--;
                }
            }
        }
        return list.get(list.size() - 1);
    }

    public int findOne(int[] points) {
        for (int i = 1; i < points.length; i++) {
            if (points[i] == 1) {
                points[i] = 0;
                return i;
            }
        }
        return -1;
    }
}
/*
  并查集！
  主要操作是find和union
  find是找爹
  union是把两个爹合并
  parents表示该索引的爹
  并查集并不关心真正的树的结构，以及谁和谁连接，所以会很快O(nlogn)
  找爹要O(logn)，跟树的高度相关
  执行用时：1 ms, 在所有 Java 提交中击败了53.13%的用户
*/
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int[] parents = new int[1 + edges.length];
        for (int i = 1; i < parents.length; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < edges.length; i++) {
            int parent1 = find(parents, edges[i][0]);
            int parent2 = find(parents, edges[i][1]);
            if (parent1 != parent2) {
                union(parents, parent1, parent2);
            } else {
                return edges[i];
            }
        }
        return new int[0];
    }

    public int find(int[] parents, int i) {
        while (parents[i] != i) {
            i = parents[i];
        }
        return i;
    }

    public void union(int[] parents, int m, int n) {
        parents[m] = n;
    }
}
/*
  刚才写题解想到的优化方案
  parents是爹，索引可以在find之后，指向最高的爹
  这样一个爹有好多好多子树
  直接将树的高度坍缩了，连rank都不用了
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int[] parents = new int[1 + edges.length];
        for (int i = 1; i < parents.length; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < edges.length; i++) {
            int parent1 = find(parents, edges[i][0]);
            parents[edges[i][0]] = parent1;
            int parent2 = find(parents, edges[i][1]);
            parents[edges[i][1]] = parent2;
            if (parent1 != parent2) {
                union(parents, parent1, parent2);
            } else {
                return edges[i];
            }
        }
        return new int[0];
    }

    public int find(int[] parents, int i) {
        while (parents[i] != i) {
            i = parents[i];
        }
        return i;
    }

    public void union(int[] parents, int m, int n) {
        parents[m] = n;
    }
}
