// 剑指 Offer 62. 圆圈中最后剩下的数字
/*
  废案，光想着节省删除的开销了，但是swap破坏了顺序
  WA
*/
class Solution {
    public int lastRemaining(int n, int m) {
        int[] circle = new int[n];
        for (int i = 0; i < circle.length; i++) {
            circle[i] = i;
        }
        int end = circle.length - 1;
        int cur = -1;
        while (end > 0) {
            cur = (cur + m) % (end + 1);
            swap(circle, cur, end--);
        }
        return circle[0];
    }

    public void swap(int[] circle, int a, int b) {
        int tmp = circle[a];
        circle[a] = circle[b];
        circle[b] = tmp;
    }
}
/*
  模拟，好慢
  还想到了一种优化，是用boolean数组记录
  但是影响应该不大，也是二次方级别的复杂度
  执行用时：1094 ms, 在所有 Java 提交中击败了6.73%的用户
*/
class Solution {
    public int lastRemaining(int n, int m) {
        List<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int cur = 0;
        while (list.size() > 1) {
            cur = (cur + m - 1) % list.size();
            list.remove(cur);
        }
        return list.get(0);
    }
}
/*
  原来这是一个很著名的问题啊
  有点动态规划那味
  下面的第几，都是1 index
  f(n, m)表示，留下的是第几个数
  又知，当我面对f(n, m)的时候，我第一个删除的数是n % m
  删除完liao，它就变成了一个n - 1规模的问题
  我如果知道f(n - 1, m)的时候，留下的是哪个数
  那么，也就是我第一次删除之后，再往后找，就能找到了
  执行用时：8 ms, 在所有 Java 提交中击败了32.46%的用户
*/
class Solution {
    public int lastRemaining(int n, int m) {
        return f(n, m);
    }

    public int f(int n, int m) {
        if (n == 1) {
            return 0;
        }
        return (m + f(n - 1, m)) % n;
    }
}
/*
  避免递归，改用dp
  执行用时：6 ms, 在所有 Java 提交中击败了44.71%的用户
*/
class Solution {
    public int lastRemaining(int n, int m) {
        int[] dp = new int[n + 1];
        dp[1] = 0;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = (m + dp[i - 1]) % i;
        }
        return dp[n];
    }
}
/*
  dp都不需要记录，因为只需要前面一个值
  执行用时：4 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int lastRemaining(int n, int m) {
        int ret = 0;
        for (int i = 2; i <= n; i++) {
            ret = (m + ret) % i;
        }
        return ret;
    }
}
