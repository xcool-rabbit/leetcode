//771. 宝石与石头
/*
  理论上来讲用Set效率是很高的，线性时间复杂度
  但是由于这个题的规模，N很小，最多只有50
  导致这个题N ^ 2比我线性还快
  无所谓了，不改了，谁都会
  执行用时：2 ms 已经战胜 47.58 % 的 java 提交记录
*/
class Solution {
    public int numJewelsInStones(String J, String S) {
        Set<Character> set = new HashSet<Character>();
        for (int i = 0; i < J.length(); i++) {
            set.add(J.charAt(i));
        }
        int count = 0;
        for (int i = 0; i < S.length(); i++) {
            if (set.contains(S.charAt(i))) {
                count++;
            }
        }
        return count;
    }
}
