// 1486. 数组异或操作
/*
  照着题意写就可以了，注意只需要遍历一遍，并且数组都可以不声明
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int xorOperation(int n, int start) {
        int answer = 0;

        for (int i = 0; i < n; i++) {
            answer ^= start + 2 * i;
        }

        return answer;
    }
}
