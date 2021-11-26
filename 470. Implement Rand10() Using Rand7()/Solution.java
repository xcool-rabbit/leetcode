// 470. 用 Rand7() 实现 Rand10()
/*
  虽然是rand了两次，但是并不是说让这两次的结果乘起来
  首先两次rand一共有49种可能，1-49种可能！而不是7 * 7 = 49
  按照顺序标1-10
  一共49个，标40个，最后9个丢掉
  这40个，有4个1,4个2，，，所有这些数出现的可能性是相等的
  如果取出的可能性落在了40之外，则重新rand
  通过这样取值来保证可能性相等
  优化：
  实际上丢掉的那个数，也是可以利用起来的
  比如49，最后9个数，可能性还是相等的
  这就相当于做了一次rand9
  拿着那个数，再rand7一次，就又等于是取了两个，又可能产生一次随机
  执行用时：7 ms, 在所有 Java 提交中击败了99.37%的用户
*/
/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */
class Solution extends SolBase {
    public int rand10() {
        int possible = 7;
        int first = rand7();
        int second;
        int index;
        int max;
        while (true) {
            second = rand7();
            index = (first - 1) * 7 + second;
            max = possible * 7;
            max -= max % 10;
            if (index > max) {
                possible = possible * 7 % 10;
                possible = possible == 1 ? 7 : possible;
                first = index % 10;
            } else {
                return index % 10 == 0 ? 10 : index % 10;
            }
        }
    }
}
