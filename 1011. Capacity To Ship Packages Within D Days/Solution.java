// 1011. 在 D 天内送达包裹的能力
/*
  二分试答案的问题
  可以优化的点就是初始情况左右边界的范围
  另外我还想了一点，就是这个求和应该可以积累一些先验知识
  只是想想，因为现在已经很快了
  执行用时：9 ms, 在所有 Java 提交中击败了96.73%的用户
*/
class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int sum = 0;
        int max = 0;
        for (int i : weights) {
            sum += i;
            max = i > max ? i : max;
        }
        int left = sum % days == 0 ? sum / days : sum / days + 1;
        left = left > max ? left : max;
        int right = sum;
        int mid = (left + right) / 2;
        int answer = mid;
        while (left <= right) {
            int day = calcShipDays(weights, mid);
            if (day <= days) {
                right = mid - 1;
                answer = mid;
            } else {
                left = mid + 1;
            }
            mid = (left + right) / 2;
        }
        return answer;
    }

    public int calcShipDays(int[] weights, int total) {
        int cur = 0;
        int day = 0;
        for (int i : weights) {
            if (cur + i <= total) {
                cur += i;
            } else {
                cur = i;
                day++;
            }
        }
        return ++day;
    }
}
