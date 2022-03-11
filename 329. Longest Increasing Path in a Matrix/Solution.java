// 329. 矩阵中的最长递增路径
/*
  这个方法算是寄了，这种方法取巧躲避了一些单元格的递增路径的求解
  然后dfs没有返回值，导致并不知道从当前节点到尽头的深度
  distance维护的永远是从最外面到这里的深度
  所以整个方案很难用上全部单元格的缓存
  LTE
*/
class Solution {
    int distance = 1;
    int max;
    int ret;
    int[][] distanceCache;
    public int longestIncreasingPath(int[][] matrix) {
        distanceCache = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (hasSmallerNearBy(matrix, i, j)) {
                    continue;
                }
                // System.out.println("i " + i + " j " + j);
                boolean[][] visited = new boolean[matrix.length][matrix[0].length];
                dfs(matrix, visited, i, j);
                distanceCache[i][j] = max;
                ret = Math.max(ret, max);
            }
        }
        return ret;
    }

    public void dfs(int[][] matrix, boolean[][] visited, int i, int j) {
        if (distanceCache[i][j] != 0) {
            distance = distanceCache[i][j];
            return;
        }
        int cur = matrix[i][j];
        if (!outOfBounds(matrix, i - 1, j) && !visited[i - 1][j]) {
            int up = matrix[i - 1][j];
            if (up > cur) {
                visited[i][j] = true;
                distance++;
                dfs(matrix, visited, i - 1, j);
                visited[i][j] = false;
                distance--;
            }
        }
        if (!outOfBounds(matrix, i + 1, j) && !visited[i + 1][j]) {
            int down = matrix[i + 1][j];
            if (down > cur) {
                visited[i][j] = true;
                distance++;
                dfs(matrix, visited, i + 1, j);
                visited[i][j] = false;
                distance--;
            }
        }
        if (!outOfBounds(matrix, i, j - 1) && !visited[i][j - 1]) {
            int left = matrix[i][j - 1];
            if (left > cur) {
                visited[i][j] = true;
                distance++;
                dfs(matrix, visited, i, j - 1);
                visited[i][j] = false;
                distance--;
            }
        }
        if (!outOfBounds(matrix, i, j + 1) && !visited[i][j + 1]) {
            int up = matrix[i][j + 1];
            if (up > cur) {
                visited[i][j] = true;
                distance++;
                dfs(matrix, visited, i, j + 1);
                visited[i][j] = false;
                distance--;
            }
        }
        max = Math.max(max, distance);
    }

    public boolean hasSmallerNearBy(int[][] matrix, int i, int j) { // cache
        int cur = matrix[i][j];
        if (!outOfBounds(matrix, i - 1, j)) {
            int up = matrix[i - 1][j];
            if (up < cur) {
                return true;
            }
        }
        if (!outOfBounds(matrix, i + 1, j)) {
            int down = matrix[i + 1][j];
            if (down < cur) {
                return true;
            }
        }
        if (!outOfBounds(matrix, i, j - 1)) {
            int left = matrix[i][j - 1];
            if (left < cur) {
                return true;
            }
        }
        if (!outOfBounds(matrix, i, j + 1)) {
            int right = matrix[i][j + 1];
            if (right < cur) {
                return true;
            }
        }
        return false;
    }

    public boolean outOfBounds(int[][] matrix, int i, int j) {
        return i < 0 || i >= matrix.length || j < 0 || j >= matrix[i].length;
    }
}
/*
  将dfs的返回值改成了当前位置的distance
  现在可以存储遍历过的位置的distance了，避免了很多重复的distance求值
  执行用时：295 ms, 在所有 Java 提交中击败了5.01%的用户
*/
class Solution {
    int ret;
    int[][] distanceCache;
    public int longestIncreasingPath(int[][] matrix) {
        distanceCache = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                boolean[][] visited = new boolean[matrix.length][matrix[0].length];
                ret = Math.max(ret, dfs(matrix, visited, i, j));
            }
        }
        return ret;
    }

    public int dfs(int[][] matrix, boolean[][] visited, int i, int j) {
        int max = 1;
        int distance = 1;
        if (distanceCache[i][j] != 0) {
            distance = distanceCache[i][j];
            return distance;
        }
        int cur = matrix[i][j];
        if (!outOfBounds(matrix, i - 1, j) && !visited[i - 1][j]) {
            int up = matrix[i - 1][j];
            if (up > cur) {
                visited[i][j] = true;
                distance =  1 + dfs(matrix, visited, i - 1, j);
                max = Math.max(max, distance);
                visited[i][j] = false;
            }
        }
        if (!outOfBounds(matrix, i + 1, j) && !visited[i + 1][j]) {
            int down = matrix[i + 1][j];
            if (down > cur) {
                visited[i][j] = true;
                distance =  1 + dfs(matrix, visited, i + 1, j);
                max = Math.max(max, distance);
                visited[i][j] = false;
            }
        }
        if (!outOfBounds(matrix, i, j - 1) && !visited[i][j - 1]) {
            int left = matrix[i][j - 1];
            if (left > cur) {
                visited[i][j] = true;
                distance =  1 + dfs(matrix, visited, i, j - 1);
                max = Math.max(max, distance);
                visited[i][j] = false;
            }
        }
        if (!outOfBounds(matrix, i, j + 1) && !visited[i][j + 1]) {
            int up = matrix[i][j + 1];
            if (up > cur) {
                visited[i][j] = true;
                distance =  1 + dfs(matrix, visited, i, j + 1);
                max = Math.max(max, distance);
                visited[i][j] = false;
            }
        }
        distanceCache[i][j] = max;
        return max;
    }

    public boolean outOfBounds(int[][] matrix, int i, int j) {
        return i < 0 || i >= matrix.length || j < 0 || j >= matrix[i].length;
    }
}
/*
  有了上面两个思路的铺垫，接下来我们将它融合起来
  我感觉这个方法已经优化到尽头了
  不愧是hard题，又有什么奇淫巧技呢
  执行用时：58 ms, 在所有 Java 提交中击败了5.68%的用户
*/
class Solution {
    int ret;
    int[][] distanceCache;
    public int longestIncreasingPath(int[][] matrix) {
        distanceCache = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (hasSmallerNearBy(matrix, i, j)) {
                    continue;
                }
                boolean[][] visited = new boolean[matrix.length][matrix[0].length];
                ret = Math.max(ret, dfs(matrix, visited, i, j));
            }
        }
        return ret;
    }

    public int dfs(int[][] matrix, boolean[][] visited, int i, int j) {
        int max = 1;
        int distance = 1;
        if (distanceCache[i][j] != 0) {
            distance = distanceCache[i][j];
            return distance;
        }
        int cur = matrix[i][j];
        if (!outOfBounds(matrix, i - 1, j) && !visited[i - 1][j]) {
            int up = matrix[i - 1][j];
            if (up > cur) {
                visited[i][j] = true;
                distance =  1 + dfs(matrix, visited, i - 1, j);
                max = Math.max(max, distance);
                visited[i][j] = false;
            }
        }
        if (!outOfBounds(matrix, i + 1, j) && !visited[i + 1][j]) {
            int down = matrix[i + 1][j];
            if (down > cur) {
                visited[i][j] = true;
                distance =  1 + dfs(matrix, visited, i + 1, j);
                max = Math.max(max, distance);
                visited[i][j] = false;
            }
        }
        if (!outOfBounds(matrix, i, j - 1) && !visited[i][j - 1]) {
            int left = matrix[i][j - 1];
            if (left > cur) {
                visited[i][j] = true;
                distance =  1 + dfs(matrix, visited, i, j - 1);
                max = Math.max(max, distance);
                visited[i][j] = false;
            }
        }
        if (!outOfBounds(matrix, i, j + 1) && !visited[i][j + 1]) {
            int up = matrix[i][j + 1];
            if (up > cur) {
                visited[i][j] = true;
                distance =  1 + dfs(matrix, visited, i, j + 1);
                max = Math.max(max, distance);
                visited[i][j] = false;
            }
        }
        distanceCache[i][j] = max;
        return max;
    }

    public boolean hasSmallerNearBy(int[][] matrix, int i, int j) { // cache
        int cur = matrix[i][j];
        if (!outOfBounds(matrix, i - 1, j)) {
            int up = matrix[i - 1][j];
            if (up < cur) {
                return true;
            }
        }
        if (!outOfBounds(matrix, i + 1, j)) {
            int down = matrix[i + 1][j];
            if (down < cur) {
                return true;
            }
        }
        if (!outOfBounds(matrix, i, j - 1)) {
            int left = matrix[i][j - 1];
            if (left < cur) {
                return true;
            }
        }
        if (!outOfBounds(matrix, i, j + 1)) {
            int right = matrix[i][j + 1];
            if (right < cur) {
                return true;
            }
        }
        return false;
    }

    public boolean outOfBounds(int[][] matrix, int i, int j) {
        return i < 0 || i >= matrix.length || j < 0 || j >= matrix[i].length;
    }
}
/*
  看了下题解，反思：
  因为有大小的限制，所以其实根本不需要维护visited
  其实后来想了一下，如果，一直在用一个visited，应该也不会这么耗时间
  执行用时：9 ms, 在所有 Java 提交中击败了64.48%的用户
*/
class Solution {
    int ret;
    int[][] distanceCache;
    public int longestIncreasingPath(int[][] matrix) {
        distanceCache = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (hasSmallerNearBy(matrix, i, j)) {
                    continue;
                }
                ret = Math.max(ret, dfs(matrix, i, j));
            }
        }
        return ret;
    }

    public int dfs(int[][] matrix, int i, int j) {
        int max = 1;
        int distance = 1;
        if (distanceCache[i][j] != 0) {
            distance = distanceCache[i][j];
            return distance;
        }
        int cur = matrix[i][j];
        if (!outOfBounds(matrix, i - 1, j)) {
            int up = matrix[i - 1][j];
            if (up > cur) {
                distance =  1 + dfs(matrix, i - 1, j);
                max = Math.max(max, distance);
            }
        }
        if (!outOfBounds(matrix, i + 1, j)) {
            int down = matrix[i + 1][j];
            if (down > cur) {
                distance =  1 + dfs(matrix, i + 1, j);
                max = Math.max(max, distance);
            }
        }
        if (!outOfBounds(matrix, i, j - 1)) {
            int left = matrix[i][j - 1];
            if (left > cur) {
                distance =  1 + dfs(matrix, i, j - 1);
                max = Math.max(max, distance);
            }
        }
        if (!outOfBounds(matrix, i, j + 1)) {
            int up = matrix[i][j + 1];
            if (up > cur) {
                distance =  1 + dfs(matrix, i, j + 1);
                max = Math.max(max, distance);
            }
        }
        distanceCache[i][j] = max;
        return max;
    }

    public boolean hasSmallerNearBy(int[][] matrix, int i, int j) { // cache
        int cur = matrix[i][j];
        if (!outOfBounds(matrix, i - 1, j)) {
            int up = matrix[i - 1][j];
            if (up < cur) {
                return true;
            }
        }
        if (!outOfBounds(matrix, i + 1, j)) {
            int down = matrix[i + 1][j];
            if (down < cur) {
                return true;
            }
        }
        if (!outOfBounds(matrix, i, j - 1)) {
            int left = matrix[i][j - 1];
            if (left < cur) {
                return true;
            }
        }
        if (!outOfBounds(matrix, i, j + 1)) {
            int right = matrix[i][j + 1];
            if (right < cur) {
                return true;
            }
        }
        return false;
    }

    public boolean outOfBounds(int[][] matrix, int i, int j) {
        return i < 0 || i >= matrix.length || j < 0 || j >= matrix[i].length;
    }
}
