//125. 验证回文串
/*
  两个指针，指向头尾，不等就是false，等就向中间靠拢，全部遍历完成，就返回true。
  注意大小写的处理和字母数字都要考虑进去。
  执行用时：11 ms 已经战胜 53.23 % 的 java 提交记录
*/
class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        char[] a = s.toCharArray();
        int head = 0;
        int tail = a.length - 1;
        
        while (head < tail) {
            while (!(('a' <= a[head] && a[head] <= 'z') || ('0' <= a[head] && a[head] <= '9')) && head < tail)
                head++;
            while (!(('a' <= a[tail] && a[tail] <= 'z') || ('0' <= a[tail] && a[tail] <= '9')) && head < tail)
                tail--;
            if (head < tail) {
                if (a[head] != a[tail])
                    return false;
            }
            head++;
            tail--;
        }
        
        return true;
    }
}
