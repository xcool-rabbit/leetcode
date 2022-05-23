// 剑指 Offer 49. 丑数
/*
  无论是用set判断，还是用除法判断
  他们都是在一个一个数的去判断
  这个题的规模，不允许他们这样做，这样一定会超时
  我也在想乘法的方法，但是不能保证大小
  LTE
*/
class Solution {
    public int nthUglyNumber(int n) {
        if (n == 1) {
            return 1;
        }
        int count = 1;
        int num = 2;
        Set<Integer> set = new HashSet<>(n);
        set.add(1);
        while (count < n) {
            if (num % 2 == 0 && set.contains(num / 2)) {
                set.add(num);
                count++;
                num++;
                continue;
            }
            if (num % 3 == 0 && set.contains(num / 3)) {
                set.add(num);
                count++;
                num++;
                continue;
            }
            if (num % 5 == 0 && set.contains(num / 5)) {
                set.add(num);
                count++;
                num++;
                continue;
            }
            num++;
        }
        return num - 1;
    }
}
/*
  用小顶堆
  堆顶元素取出，乘以2,3,5之后，放回去
  嗨呀，当我看到这个做法的时候，恍然大悟
  但是这个做法并不是最好的，就不实现了
*/
/*
  上一个堆的做法，严格来讲，还是有瑕疵的
  创建了很多没用的数，然后堆的效率是O(logn)
  我也想到了是不是可以用dp，但是我没办法保证数据大小的顺序啊
  仔细看看怎么做的，用了三个指针，就解决了这个问题！
  执行用时：3 ms, 在所有 Java 提交中击败了24%的用户
*/
class Solution {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int two = 0;
        int three = 0;
        int five = 0;
        for (int i = 1; i < n; i++) {
            int min = Math.min(dp[two] * 2, dp[three] * 3);
            min = Math.min(min, dp[five] * 5);
            dp[i] = min;
            if (dp[two] * 2 == min) {
                two++;
            }
            if (dp[three] * 3 == min) {
                three++;
            }
            if (dp[five] * 5 == min) {
                five++;
            }
        }
        return dp[dp.length - 1];
    }
}
/*
  上一个写的太呆了，有很多不必要的重复计算
  执行用时：2 ms, 在所有 Java 提交中击败了98.59%的用户
*/
class Solution {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int two = 0;
        int three = 0;
        int five = 0;
        int twotwo = dp[two] * 2;
        int threethree = dp[three] * 3;
        int fivefive = dp[five] * 5;
        for (int i = 1; i < n; i++) {
            int min = Math.min(twotwo, threethree);
            min = Math.min(min, fivefive);
            dp[i] = min;
            if (twotwo == min) {
                two++;
                twotwo = dp[two] * 2;
            }
            if (threethree == min) {
                three++;
                threethree = dp[three] * 3;
            }
            if (fivefive == min) {
                five++;
                fivefive = dp[five] * 5;
            }
        }
        return dp[dp.length - 1];
    }
}
