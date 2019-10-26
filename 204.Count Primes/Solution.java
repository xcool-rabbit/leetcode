//204. 计数质数
/*
  第一想到的是暴力法，对于每一个数，都从2试到它的开方，复杂度是n ^ (3 / 2)
  之前偶然听到过室友说起，可以把已经判断出来的质数都存起来，然后只用试这些质数就可以了
  我第一次听到这种方法的时候简直是大吃一惊，每次循环的问题规模进一步缩小，并且几乎避免了所有多余的取余过程，已经是我心目中最快的方法了，直到我看到了解法二
  执行用时：233 ms 已经战胜 14.08 % 的 java 提交记录
*/
class Solution {
    public int countPrimes(int n) {
        List<Integer> primeList = new ArrayList<>();
        if (n > 2) {
            primeList.add(2);
        } else {
            return 0;
        }
        for (int i = 3; i < n; i += 2) {
            boolean isPrime = true;
            int stop = (int) Math.sqrt(i);
            for (int prime : primeList) {
                if (i % prime == 0) {
                    isPrime = false;
                    break;
                }
                if (prime > stop) {
                    break;
                }
            }
            if (isPrime) {
                primeList.add(i);
            }
        }
        return primeList.size();
    }
}
/*
  可以看到，上一个解法的时间排名并不理想，上网搜索了才知道这类问题涉及到筛法，其中埃拉托斯特尼筛法是用来筛选质数的方法
  打表，然后遇到质数将其所有的倍数全部标记。
  这种标记对于每一个质数，都需要进行n / p次，时间复杂度是nlog(logn)(我不会证明，但是推导过程就是这样的)
  那么上一种方法呢，对于n个数也需要进行n次循环，每次循环的问题规模是当前数值的平方根的质数的个数
  或者从另一个角度来想，对于每一个数，你要判断它是否是质数，比如那些2的倍数，用上一种方法你也是需要判断一次，对于3的倍数，你需要判断两次。
  相比本解法，2的倍数也是判断一次，3的倍数也是判断一次，只有像6的倍数才会被重复判断。从重复度上来看，本解法确实比上一种解法少了更多的重复。
  执行用时：15 ms 已经战胜 92.23 % 的 java 提交记录
*/
class Solution {
    public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < notPrime.length; i++) {
            if (!notPrime[i]) {
                count++;
                for (int j = 2; i * j < n; j++) {
                    notPrime[i * j] = true;
                }
            }
        }
        return count;
    }
}
