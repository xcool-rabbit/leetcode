// LCS 02. 完成一半题目
/*
  因为questions里的元素有大小的限制，最多1000，所以可以用一个数组记录
  如果范围再大一些的话，可以用HashMap存储，然后自己实现一下根据值排序
  执行用时：4 ms, 在所有 Java 提交中击败了96.79%的用户
*/
class Solution {
    public int halfQuestions(int[] questions) {
        int[] occurTimes = new int[1001];
        for (int n : questions) {
            occurTimes[n]++;
        }
        Arrays.sort(occurTimes);
        int n = questions.length / 2;
        int answer = 0;
        for (int i = occurTimes.length - 1; i >= 0; i--) {
            n -= occurTimes[i];
            answer++;
            if (n <= 0) {
                return answer;
            }
        }
        return 0;
    }
}
