// 875. 爱吃香蕉的珂珂
/*
  二分查找的问题，这次我终于注意到了，没有按照正向思维去计算，而是直接试答案
  但是要注意数据域，求平均数的那里需要用long
  这次用的是循环二分，循环递归都要会！
  执行用时：18 ms, 在所有 Java 提交中击败了64.30%的用户
*/
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        long sum = 0;
        int max = 0;
        for (int n : piles) {
            sum += n;
            max = n > max ? n : max;
        }
        int left = (int)(sum % (long)h == 0 ? sum / (long)h : sum / (long)h + 1);
        int right = max;
        int mid = (left + right) / 2;
        int answer = mid;
        while (left <= right) {
            int eatTime = calcEatTime(piles, mid);
            if (eatTime <= h) {
                right = mid - 1;
                answer = mid;
            } else {
                left = mid + 1;
            }
            mid = (left + right) / 2;
        }
        return answer;
    }

    public int calcEatTime(int[] piles, int k) {
        int ret = 0;
        for (int n : piles) {
            ret = n % k == 0 ? ret + n / k : ret + n / k + 1;
        }
        return ret;
    }
}
