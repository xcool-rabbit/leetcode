// 367. 有效的完全平方数
/*
  二分
  但是mid * mid会溢出
  如果在不使用高级函数的前提下，怎么避免溢出呢
  其实我上次不经意间看到评论，用除法就能避免溢出！就像在算mid的那样，善用减法！
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public boolean isPerfectSquare(int num) {
        int left = 1;
        int right = num;
        int mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (num / mid < mid) {
                right = mid - 1;
            } else if (num % mid == 0 && num / mid == mid) {
                return true;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}