//119. 杨辉三角 II
/*
  由于限制了空间复杂度O(k)，第一想到的是，就依靠一个数组进行计算，不断的覆盖，只需要O(k + 1)的空间复杂度。
  后来我想能不能把那个1舍去呢？从数学原理上来讲，是可以的。
  一是可以在前一位被覆盖的情况下，计算出这一位的值，但是极其复杂，运算量较大，所以觉得得不偿失。
  另外还能根据杨辉三角的数学性质，用C(n - 1)(k)的方法可以计算出指定行的每一位。
  当然我选择的是第一种。
  另外要注意的是，leetcode对这道题行数的定义好像是基0的。不过问题不大。
  执行用时：2 ms 已经战胜 72.64 % 的 java 提交记录
*/
class Solution {
    public List<Integer> getRow(int rowIndex) {
        rowIndex++;
        List<Integer> answer = new ArrayList<>();
        if (rowIndex > 0) {
            answer.add(1);
            while (rowIndex != answer.size()) {
                int pre = 0;
                int now = 0;
                for (int i = 0; i < answer.size(); i++) {
                    now = answer.get(i);
                    answer.set(i, pre + now);
                    pre = now;
                }
                answer.add(1);
            }
        }
        return answer;
    }
}
