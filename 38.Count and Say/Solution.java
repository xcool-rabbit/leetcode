//38. 报数
/*
  执行用时：4 ms 已经战胜 87.58 % 的 java 提交记录
*/
class Solution {
    public String countAndSay(int n) {
        StringBuilder[] ans = new StringBuilder[n];
        for (int i = 0; i < n; i++) {
            ans[i] = new StringBuilder();
        }
        if (n == 1)
            return "1";
        ans[0].append("1");
        for (int i = 1; i < n; i++) {
            char[] pre = ans[i - 1].toString().toCharArray();
            for (int j = 0; j < pre.length; ) {
                int count = 1;
                char num = pre[j];
                j++;
                while (j < pre.length && pre[j] == num) {
                    j++;
                    count++;
                }
                ans[i].append(String.valueOf(count));
                ans[i].append(String.valueOf(num));
            }
        }
        
        return ans[n - 1].toString();
    }
}
