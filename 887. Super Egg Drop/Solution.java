// 887. 鸡蛋掉落
/*
  递归
  我以为每次从中间开始丢，倒霉的一定是碎了的情况
  然后问题规模会缩减
  但事实证明我错了
  这个时候还不是很明白错在哪儿了
  WA
*/
class Solution {
    public int superEggDrop(int k, int n) {
        if (k == 1) {
            return n == 0 ? 1 : n;
        }
        if (n <= 1) {
            return 1;
        }
        return 1 + superEggDrop(k - 1, (n >> 1) - 1);
    }
}
/*
  递归升级版
  开始认识到这是一个二分问题，down和up都得考虑，不一定down就是最费次数的
  但是二分法大溃败！
  二分法确实能缩减问题规模，但是在后续的测试用例中，发现二分并不能保证剩下的两部分是最优的
  但是二分法已经给我指出了一条明路，这题确实是二分，但是并不是二等分
  问题规模的划分跟我之前想的一样，down会少一个蛋，up不少，但是都需要计算
  WA
*/
class Solution {
    public int superEggDrop(int k, int n) {
        if (k == 1) {
            return n;
        }
        if (n <= 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int down = superEggDrop(k - 1, ((n + 1) >> 1) - 1);
        int up = superEggDrop(k, n - ((n + 1) >> 1));
        return 1 + Math.max(down, up);
    }
}
/*
  正解原型机！
  dp公式写在注释里了
  分界点x是需要我们寻找的，能使dp[i][j]是最小值的
  RTE
*/
cclass Solution {
    public int superEggDrop(int k, int n) {
        if (k == 1) {
            return n;
        }
        if (n <= 2) {
            return n;
        }
        int[][] dp = new int[k + 1][n + 1];
        for (int i = 1; i < dp[1].length; i++) {
            dp[1][i] = i;
        }
        for (int i = 1; i < dp.length; i++) {
            dp[i][1] = 1;
            dp[i][2] = 2;
        }
        for (int i = 2; i < dp.length; i++) {
            for (int j = 3; j < dp[i].length; j++) {
                int min = Integer.MAX_VALUE;
                for (int x = 1; x < j - 1; x++) {
                    min = Math.min(min, Math.max(dp[i - 1][x], dp[i][j - 1 - x]));
                }
                dp[i][j] = min + 1;
            }
        }
        return dp[k][n];
        // dp[i][j] = dp[i - 1][x] + dp[i][y]; x + y + 1 == j;
        // dp[2][3] = dp[1][x] + dp[2][y]
    }
}
/*
  物理学的大厦已经建成，剩下的都是些修修补补的事了
  如何提速呢
  当然是查找x的时候
  x越往右越大，y越往左越小
  所以当x == y的时候，就没有必要往右遍历了
  并且，能保证x一定会等于y，因为x和y的梯度都不大于1
  另外，随着j增大，x肯定也越往右
  所以对于一个新的j，x也只需要从上次的位置继续往右找，省去了大量时间
  还有一个优化是，当需要尝试的次数小于等于鸡蛋数，无脑复制dp[i - 1][j]
  但是，为什么还是不及格呢，我不理解
  后来我还优化了两个地方，但是效果都不明显，都没有本质区别
  第一个是，最后两列的值，除了最后一行以外，其他行都不需要，但是这样改造之后，代码很难看，就，没粘贴过来
  第二个是，如果dp[i][n]的值已经小于等于i，那么dp[k][n]也是这个值
  看了眼题解，这是最基础的做法
  还有两种竞赛做法，瑟瑟发抖
  题解的写法没有用二维数组记录，递归算的，还借助了map不知道干什么
  然后写了巨多，也不好理解，就不看了
  执行用时：14 ms, 在所有 Java 提交中击败了57.70%的用户
*/
class Solution {
    public int superEggDrop(int k, int n) {
        if (k == 1) {
            return n;
        }
        if (n <= 2) {
            return n;
        }
        int[][] dp = new int[k + 1][n + 1];
        for (int i = 1; i < dp[1].length; i++) {
            dp[1][i] = i;
        }
        for (int i = 1; i < dp.length; i++) {
            dp[i][1] = 1;
            dp[i][2] = 2;
        }
        for (int i = 2; i < dp.length; i++) {
            int x = 1;
            for (int j = 3; j < dp[i].length; j++) {
                if (i - 1 >= dp[i - 1][j]) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }
                int min = Integer.MAX_VALUE;
                while (dp[i - 1][x] <= dp[i][j - 1 - x]) {
                    min = Math.min(min, dp[i][j - 1 - x]);
                    if (dp[i - 1][x] == dp[i][j - 1 - x]) {
                        break;
                    }
                    x++;
                }
                dp[i][j] = min + 1;
            }
        }
        return dp[k][n];
        // dp[i][j] = dp[i - 1][x] + dp[i][y]; x + y + 1 == j;
        // dp[2][3] = dp[1][x] + dp[2][y]
    }
}
