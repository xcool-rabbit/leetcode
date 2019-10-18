//278. 第一个错误的版本
/*
  好题！
  初见，这么弱智的题？顺序遍历不就完事儿了？
  Run！
  LTE
  思索，O(n)的遍历实在太捞了，那可以用二分啊！
  Run！
  我还用的是非递归的BinarySearch
  LTE
  郁闷，这可咋整啊。一看评论，middle = start + (end - start) / 2
  心里一盘算，这不一样的吗。后来再一研究，原来是超int了
  start + end是会超int的，而评论里的方法就不会
  执行用时：13 ms 已经战胜 94.38 % 的 java 提交记录
*/
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int start = 1;
        int end = n;
        int middle = start + (end - start) / 2;
        while (true) {
            if (isBadVersion(middle)) {
                end = middle;
            } else {
                start = middle + 1;
            }
            middle = start + (end - start) / 2;
            if (start == end) {
                return start;
            }
        }
    }
}
