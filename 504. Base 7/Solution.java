// 504. 七进制数
/*
  昨天的每日一题，一看这么简单，我pa的一下，就做好了啊
  每日一题，一道给10分呢！！！真值钱，顶我10天的赚分效率
  太简单了，不想说了
  执行用时：1 ms, 在所有 Java 提交中击败了76.60%的用户
*/
class Solution {
    public String convertToBase7(int num) {
        if (num < 0) {
            return "-" + convertToBase7(-num);
        }
        if (num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.insert(0, num % 7);
            num /= 7;
        }
        return sb.toString();
    }
}
