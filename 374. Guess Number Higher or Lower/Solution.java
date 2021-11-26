// 374. 猜数字大小
/*
  二分
  这个题是我接触信息熵这个概念的时候，见到的例子
  要注意一点，不能用(left + right) / 2来求中点，因为这样会超int
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
/** 
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is lower than the guess number
 *			      1 if num is higher than the guess number
 *               otherwise return 0
 * int guess(int num);
 */

public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int left = 1;
        int right = n;
        int mid;
        int cur;
        while (true) {
            mid = left + (right - left) / 2;
            cur = guess(mid);
            switch (cur) {
                case 0:
                    return mid;
                case 1:
                    left = mid + 1;
                    break;
                case -1:
                    right = mid - 1;
            }
        }
    }
}
