//202. 快乐数
/*
  针对无线循环的解决方案：
  用集合记录结果，如果集合在一次循环过后规模没有扩大，说明已经步入循环
  执行用时：1 ms 已经战胜 99.96 % 的 java 提交记录
*/
class Solution {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        int size = 0;
        while (n != 1) {
            set.add(n);
            if (size >= set.size()) {
                return false;
            }
            size = set.size();
            n = next(n);
        }
        return true;
    }
    
    private int next(int n) {
        int sum = 0;
        while (n != 0) {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        return sum;
    }
}
