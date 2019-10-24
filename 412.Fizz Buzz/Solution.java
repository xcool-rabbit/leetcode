//412. Fizz Buzz
/*
  太简单没什么好说的，并且测试用例也超级少
  我只是想到几个可以减少运算的方法
  用两个变量去记录能被3和5整除的情况，应该会比每次都去对这个数取模要快的多
  在字符串拼接的时候，用StringBuffer会比String快那么一丢丢应该，不过这个影响不大，因为一次循环才做一次拼接操作
  执行用时：3 ms 经战胜 74.72 % 的 java 提交记录
*/
class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>(n);
        int count3 = 1;
        int count5 = 1;
        for (int i = 1; i <= n; i++) {
            String tmp = "";
            if (count3 == 3) {
                tmp += "Fizz";
                count3 = 0;
            }
            if (count5 == 5) {
                tmp += "Buzz";
                count5 = 0;
            }
            if (count3 != 0 && count5 != 0) {
                tmp += i;
            }
            result.add(tmp);
            count3++;
            count5++;
        }
        return result;
    }
}
